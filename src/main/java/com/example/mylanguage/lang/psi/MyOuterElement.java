package com.example.mylanguage.lang.psi;

import com.example.mylanguage.lang.MyLanguage;
import com.intellij.lang.ASTNode;
import com.intellij.psi.templateLanguages.OuterLanguageElementImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILeafElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MyOuterElement extends IElementType implements ILeafElementType {
    public MyOuterElement(@NotNull @NonNls String debugName) {
        super(debugName, MyLanguage.INSTANCE);
    }

    public @NotNull
    ASTNode createLeafNode(@NotNull CharSequence charSequence) {
        return new OuterLanguageElementImpl(this, charSequence);
    }
}
