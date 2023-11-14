// This is a generated file. Not intended for manual editing.
package com.example.mylanguage.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.example.mylanguage.lang.psi.elements.MyTagElement;

public interface MyPlsVar extends MyTagElement {

  @Nullable PsiElement getTagOpen();

  @Nullable PsiElement getTagClose();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
