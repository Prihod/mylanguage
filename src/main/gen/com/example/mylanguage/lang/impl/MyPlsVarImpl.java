// This is a generated file. Not intended for manual editing.
package com.example.mylanguage.lang.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.example.mylanguage.lang.psi.MyTypes.*;
import com.example.mylanguage.lang.psi.impl.elements.MyTagElementImpl;
import com.example.mylanguage.lang.psi.*;
import com.example.mylanguage.lang.psi.impl.MyPsiImplUtil;

public class MyPlsVarImpl extends MyTagElementImpl implements MyPlsVar {

  public MyPlsVarImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MyVisitor visitor) {
    visitor.visitPlsVar(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MyVisitor) accept((MyVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public @Nullable PsiElement getTagOpen() {
    return MyPsiImplUtil.getTagOpen(this);
  }

  @Override
  public @Nullable PsiElement getTagClose() {
    return MyPsiImplUtil.getTagClose(this);
  }

  @Override
  public String getName() {
    return MyPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return MyPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return MyPsiImplUtil.getNameIdentifier(this);
  }

}
