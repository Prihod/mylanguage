package com.example.mylanguage.lang.psi;

import com.example.mylanguage.lang.MyLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MyTokenType extends IElementType {

    public MyTokenType(@NotNull @NonNls String debugName) {
        super(debugName, MyLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "MyTokenType." + super.toString();
    }

}
