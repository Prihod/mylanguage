package com.example.mylanguage.lang;

import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.lang.InjectableLanguage;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import com.jetbrains.smarty.SmartyFileType;
import com.jetbrains.smarty.SmartyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class MyLanguage extends Language implements TemplateLanguage, InjectableLanguage {

    public static final MyLanguage INSTANCE = new MyLanguage();

    @SuppressWarnings("SameReturnValue") // ideally this would be public static, but the static inits in the tests get cranky when we do that
    public static LanguageFileType getDefaultTemplateLang() {
        return SmartyFileType.INSTANCE;
      //  return HtmlFileType.INSTANCE;
    }

    public MyLanguage() {
        super("My");
    }

    public MyLanguage(@Nullable Language baseLanguage, @NotNull @NonNls final String ID, @NonNls final String @NotNull ... mimeTypes) {
        super(baseLanguage, ID, mimeTypes);
    }

}
