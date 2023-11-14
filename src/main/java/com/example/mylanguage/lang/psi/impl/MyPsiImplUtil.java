package com.example.mylanguage.lang.psi.impl;

import com.example.mylanguage.lang.psi.MyElementFactory;
import com.example.mylanguage.lang.psi.MyTypes;
import com.example.mylanguage.lang.psi.elements.MyTagElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyPsiImplUtil {
    private static final Logger LOG = Logger.getInstance(MyPsiImplUtil.class);

    @Nullable
    public static PsiElement getTagOpen(@NotNull PsiElement element) {
        ASTNode tag = element.getNode().findChildByType(MyTypes.TAG_OPEN);
        if (tag != null) {
            return tag.getPsi();
        } else {
            return null;
        }
    }

    @Nullable
    public static PsiElement getTagClose(@NotNull PsiElement element) {
        ASTNode tag = element.getNode().findChildByType(MyTypes.TAG_CLOSE);
        if (tag != null) {
            return tag.getPsi();
        } else {
            return null;
        }
    }

    public static String getName(PsiElement element) {
        ASTNode node = getNodeIdentifier(element);
        if (node != null) {
            return node.getText();
        } else {
            return null;
        }
    }

    public static PsiElement setName(PsiElement element, String newName) {
        ASTNode node = getNodeIdentifier(element);
        if (node != null) {
            MyTagElement tag = MyElementFactory.createTag(element, newName);
            ASTNode newKeyNode = tag.getFirstChild().getNode();
            element.getNode().replaceChild(node, newKeyNode);
        }
        return element;
    }

    @Nullable
    public static ASTNode getNodeIdentifier(PsiElement element) {
        ASTNode node;
        if (PhpPsiUtil.isOfType(element, MyTypes.LINK_VAR)) {
            node = element.getNode().findChildByType(MyTypes.ID);
        } else {
            node = element.getNode().findChildByType(MyTypes.IDENTIFIER);
        }
        return node;
    }

    public static PsiElement getNameIdentifier(PsiElement element) {
        ASTNode node = getNodeIdentifier(element);
        if (node != null) {
            return node.getPsi();
        } else {
            return null;
        }
    }
}
