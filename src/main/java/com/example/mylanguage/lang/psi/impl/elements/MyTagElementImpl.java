package com.example.mylanguage.lang.psi.impl.elements;

import com.example.mylanguage.lang.psi.elements.MyTagElement;
import com.example.mylanguage.lang.psi.impl.MyReferencedElementImpl;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class MyTagElementImpl extends MyReferencedElementImpl implements MyTagElement {
    
    public MyTagElementImpl(@NotNull ASTNode node) {
        super(node);
    }


}
