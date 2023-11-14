package com.example.mylanguage.lang.psi.impl;

import com.example.mylanguage.lang.psi.MyNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class MyNamedElementImpl extends ASTWrapperPsiElement implements MyNamedElement {
    MyNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
