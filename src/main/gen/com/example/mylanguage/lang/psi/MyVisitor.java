// This is a generated file. Not intended for manual editing.
package com.example.mylanguage.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.example.mylanguage.lang.psi.elements.MyTagElement;

public class MyVisitor extends PsiElementVisitor {

  public void visitLinkVar(@NotNull MyLinkVar o) {
    visitTagElement(o);
  }

  public void visitPlsVar(@NotNull MyPlsVar o) {
    visitTagElement(o);
  }

  public void visitTagElement(@NotNull MyTagElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
