package com.example.mylanguage.lang.formatter;

import com.example.mylanguage.lang.MyFileViewProvider;
import com.example.mylanguage.lang.psi.MyPsiFile;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.xml.XmlFormattingPolicy;
import com.intellij.xml.template.formatter.AbstractXmlTemplateFormattingModelBuilder;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import org.jetbrains.annotations.Nullable;


public class MyFormattingModelBuilder extends AbstractXmlTemplateFormattingModelBuilder {

    private static final Logger LOG = Logger.getInstance(MyFormattingModelBuilder.class);

    @Override
    protected boolean isTemplateFile(PsiFile file) {
        return file instanceof MyPsiFile;
    }

    @Override
    public boolean isOuterLanguageElement(PsiElement element) {
        return PhpPsiUtil.isOfType(element, MyFileViewProvider.OUTER_ELEMENT_TYPE);
    }

    @Override
    public boolean isMarkupLanguageElement(PsiElement element) {
        return PhpPsiUtil.isOfType(element,  MyFileViewProvider.OUTER_ELEMENT_TYPE);
    }

    @Override
    protected Block createTemplateLanguageBlock(ASTNode node, CodeStyleSettings settings, XmlFormattingPolicy xmlFormattingPolicy, Indent indent, @Nullable Alignment alignment, @Nullable Wrap wrap) {
        return new MyBlock(this, node, wrap, alignment, settings, xmlFormattingPolicy, indent);
    }

}
