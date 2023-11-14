package com.example.mylanguage.lang.psi.elements;

import com.example.mylanguage.lang.psi.MyNamedElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface MyTagElement extends MyNamedElement {

    public @Nullable PsiElement getTagOpen();

    public @Nullable PsiElement getTagClose();

}
