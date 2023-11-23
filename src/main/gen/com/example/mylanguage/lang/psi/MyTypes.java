// This is a generated file. Not intended for manual editing.
package com.example.mylanguage.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.example.mylanguage.lang.impl.*;

public interface MyTypes {

  IElementType LINK_VAR = new MyElement("LINK_VAR");
  IElementType PLS_VAR = new MyElement("PLS_VAR");

  IElementType COMMENT = new MyTokenType("COMMENT");
  IElementType CONTENT = new MyTokenType("CONTENT");
  IElementType ID = new MyTokenType("ID");
  IElementType IDENTIFIER = new MyTokenType("IDENTIFIER");
  IElementType SIGN_LINK = new MyTokenType("~");
  IElementType SIGN_PLS = new MyTokenType("+");
  IElementType TAG_CLOSE = new MyTokenType("]]");
  IElementType TAG_OPEN = new MyTokenType("[[");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == LINK_VAR) {
        return new MyLinkVarImpl(node);
      }
      else if (type == PLS_VAR) {
        return new MyPlsVarImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
