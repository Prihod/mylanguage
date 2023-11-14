package com.example.mylanguage.lang.inspections;

import com.example.mylanguage.lang.MyLanguage;
import com.example.mylanguage.lang.psi.elements.MyTagElement;
import com.intellij.codeInsight.highlighting.HighlightErrorFilter;
import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.psi.xml.XmlElement;
import org.jetbrains.annotations.NotNull;

public class MyErrorFilter extends HighlightErrorFilter {
    private static final Logger LOG = Logger.getInstance(MyErrorFilter.class);

    public MyErrorFilter() {

    }

    @Override
    public boolean shouldHighlightErrorElement(@NotNull PsiErrorElement element) {
        PsiFile templateLanguageFile = PsiUtilCore.getTemplateLanguageFile(element.getContainingFile());
        if (templateLanguageFile == null) {
            return true;
        }
        Language language = templateLanguageFile.getLanguage();
        if (language != MyLanguage.INSTANCE) {
            return true;
        }

        if (element.getParent() instanceof XmlElement) {
            return false;
        }

        if (element.getParent().getLanguage() == MyLanguage.INSTANCE) {
            if (element.getParent() instanceof MyTagElement) {
                MyTagElement MyTag = (MyTagElement) element.getParent();
                if (MyTag.getTagClose() == null) {
                    return false;
                }
            }
        }
        return true;
    }

}



