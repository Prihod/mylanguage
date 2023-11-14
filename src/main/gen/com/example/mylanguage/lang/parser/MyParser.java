// This is a generated file. Not intended for manual editing.
package com.example.mylanguage.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.example.mylanguage.lang.psi.MyTypes.*;
import static com.example.mylanguage.lang.parser.MyParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MyParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // tag_open  SIGN_LINK ID tag_close
  public static boolean link_var(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "link_var")) return false;
    if (!nextTokenIs(b, TAG_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LINK_VAR, null);
    r = tag_open(b, l + 1);
    r = r && consumeTokens(b, 1, SIGN_LINK, ID);
    p = r; // pin = 2
    r = r && tag_close(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // tag_open SIGN_PLS IDENTIFIER tag_close
  public static boolean pls_var(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pls_var")) return false;
    if (!nextTokenIs(b, TAG_OPEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PLS_VAR, null);
    r = tag_open(b, l + 1);
    r = r && consumeTokens(b, 1, SIGN_PLS, IDENTIFIER);
    p = r; // pin = 2
    r = r && tag_close(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // !(CONTENT|COMMENT)
  static boolean recover_parser(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_parser")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recover_parser_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CONTENT|COMMENT
  private static boolean recover_parser_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_parser_0")) return false;
    boolean r;
    r = consumeToken(b, CONTENT);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // statement*
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (pls_var|link_var|COMMENT|CONTENT) {
  //  // recoverWhile=recover_parser
  // }
  static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statement_0(b, l + 1);
    r = r && statement_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // pls_var|link_var|COMMENT|CONTENT
  private static boolean statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_0")) return false;
    boolean r;
    r = pls_var(b, l + 1);
    if (!r) r = link_var(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CONTENT);
    return r;
  }

  // {
  //  // recoverWhile=recover_parser
  // }
  private static boolean statement_1(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // TAG_CLOSE
  static boolean tag_close(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_close")) return false;
    if (!nextTokenIs(b, "<]]>", TAG_CLOSE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, null, "<]]>");
    r = consumeToken(b, TAG_CLOSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TAG_OPEN
  static boolean tag_open(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_open")) return false;
    if (!nextTokenIs(b, "<[[>", TAG_OPEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, null, "<[[>");
    r = consumeToken(b, TAG_OPEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
