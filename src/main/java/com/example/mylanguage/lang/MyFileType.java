package com.example.mylanguage.lang;

import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.openapi.fileTypes.CharsetUtil;
import com.intellij.openapi.fileTypes.LanguageFileType;

import javax.swing.Icon;

import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.Charset;

public class MyFileType extends XmlLikeFileType implements TemplateLanguageFileType {

    @NonNls
    public static final String DEFAULT_EXTENSION = "my";

    public static final MyFileType INSTANCE = new MyFileType();

    private MyFileType() {
        super(MyLanguage.INSTANCE);
    }

    protected MyFileType(Language lang) {
        super(lang);
    }

    @NotNull
    @Override
    public String getName() {
        return "My";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "My language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public Charset extractCharsetFromFileContent(@Nullable final Project project, @Nullable final VirtualFile file, @NotNull final CharSequence content) {
        LanguageFileType associatedFileType = getAssociatedFileType(file, project);

        if (associatedFileType == null) {
            return null;
        }

        return CharsetUtil.extractCharsetFromFileContent(project, file, associatedFileType, content);
    }

    private static LanguageFileType getAssociatedFileType(VirtualFile file, Project project) {
        if (project == null) {
            return null;
        }
        Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(file);

        LanguageFileType associatedFileType = null;
        if (language != null) {
            associatedFileType = language.getAssociatedFileType();
        }

        if (language == null || associatedFileType == null) {
            associatedFileType = MyLanguage.getDefaultTemplateLang();
        }
        return associatedFileType;
    }
}
