package com.example.mylanguage.lang;

import com.example.mylanguage.lang.psi.MyOuterElement;
import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.LanguageSubstitutors;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.templateLanguages.ConfigurableTemplateLanguageFileViewProvider;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.smarty.SmartyLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements ConfigurableTemplateLanguageFileViewProvider {

    private static final Logger LOG = Logger.getInstance(MyFileViewProvider.class);

    private final Language myBaseLanguage;
    private final Language myTemplateLanguage;

    public static MyOuterElement OUTER_ELEMENT_TYPE = new MyOuterElement("OUTER_ELEMENT_TYPE");

    private static final ConcurrentMap<String, TemplateDataElementType> TEMPLATE_DATA_TO_LANG = new ConcurrentHashMap<>();

    private static TemplateDataElementType getTemplateDataElementType(Language lang) {
        TemplateDataElementType result = TEMPLATE_DATA_TO_LANG.get(lang.getID());

        if (result != null) return result;
        TemplateDataElementType created = new TemplateDataElementType("My_TEMPLATE_DATA", lang, MyTypes.CONTENT, OUTER_ELEMENT_TYPE);
        TemplateDataElementType prevValue = TEMPLATE_DATA_TO_LANG.putIfAbsent(lang.getID(), created);

        return prevValue == null ? created : prevValue;
    }


    public MyFileViewProvider(PsiManager manager, VirtualFile file, boolean physical, Language baseLanguage) {
        this(manager, file, physical, baseLanguage, getTemplateDataLanguage(manager, file));
    }

    public MyFileViewProvider(PsiManager manager, VirtualFile file, boolean physical, Language baseLanguage, Language templateLanguage) {
        super(manager, file, physical);
        myBaseLanguage = baseLanguage;
        myTemplateLanguage = templateLanguage;
    }

    @Override
    public boolean supportsIncrementalReparse(@NotNull Language rootLanguage) {
        return false;
    }

    private static @NotNull Language getTemplateDataLanguage(PsiManager manager, VirtualFile file) {
        Language dataLang = TemplateDataLanguageMappings.getInstance(manager.getProject()).getMapping(file);
        if (dataLang == null) {
            dataLang = MyLanguage.getDefaultTemplateLang().getLanguage();
        }

        Language substituteLang = LanguageSubstitutors.getInstance().substituteLanguage(dataLang, file, manager.getProject());

        // only use a substituted language if it's templateable
        if (TemplateDataLanguageMappings.getTemplateableLanguages().contains(substituteLang)) {
            dataLang = substituteLang;
        }
        return dataLang;
    }

    @Override
    public @NotNull Language getBaseLanguage() {
        return myBaseLanguage;
    }

    @Override
    public @NotNull Language getTemplateDataLanguage() {
        return myTemplateLanguage;
    }

    @Override
    public @NotNull Set<Language> getLanguages() {
        return Set.of(myBaseLanguage, getTemplateDataLanguage());
    }

    @Override
    protected @NotNull MultiplePsiFilesPerDocumentFileViewProvider cloneInner(@NotNull VirtualFile virtualFile) {
        return new MyFileViewProvider(getManager(), virtualFile, false, myBaseLanguage, myTemplateLanguage);
    }

    @Override
    protected PsiFile createFile(@NotNull Language lang) {
        ParserDefinition parserDefinition = getDefinition(lang);
        if (parserDefinition == null) {
            return null;
        }

        if (lang.is(getTemplateDataLanguage())) {
            PsiFile file = parserDefinition.createFile(this);
            IElementType type = getContentElementType(lang);
            if (type != null) {
                ((PsiFileImpl) file).setContentElementType(type);
            }
            return file;
        } else if (lang.isKindOf(getBaseLanguage())) {
            return parserDefinition.createFile(this);
        }
        return null;
    }

   /* @Nullable
    @Override
    protected PsiFile createFile(@NotNull Language lang) {
        if (lang == getBaseLanguage()) {
            return createFileInner(lang);
        }
        if (lang == getTemplateDataLanguage()) {
            final PsiFileImpl file = (PsiFileImpl) createFileInner(lang);
            IElementType type = getContentElementType(lang);
            if (type != null) {
                file.setContentElementType(type);
            }

          //  file.setContentElementType(TwigTemplateTokens.TEMPLATE_DATA);
            return file;
        }
        return null;
    }

    private PsiFile createFileInner(Language lang) {
        return LanguageParserDefinitions.INSTANCE.forLanguage(lang).createFile(this);
    }
*/
    @Override
    public @Nullable IElementType getContentElementType(@NotNull Language language) {
        if (language.is(getTemplateDataLanguage())) {
            return getTemplateDataElementType(getBaseLanguage());
        }
        return null;
    }

    private ParserDefinition getDefinition(Language lang) {
        ParserDefinition parserDefinition;
        if (lang.isKindOf(getBaseLanguage())) {
            parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(lang.is(getBaseLanguage()) ? lang : getBaseLanguage());
        } else {
            parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(lang);
        }
        return parserDefinition;
    }
}
