package com.example.mylanguage.lang;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;
import com.example.mylanguage.lang.psi.MyTypes;

%%

%public
%class MyLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%{

    private Stack<Integer> stack = new Stack<>();

    public void yypushState(int newState) {
      stack.push(yystate());
      yybegin(newState);
    }

    public int yypopState() {
        int currState =  stack.pop();
        yybegin(currState);
        return currState;
    }

    public int getSecondState() {
        int secondTop  = 0;
        if (!stack.isEmpty()) {
            int top = stack.pop();
            if (!stack.isEmpty()) {
                secondTop = stack.peek();
            }
            stack.push(top);
        }
        return secondTop;
    }

%}

CRLF=\R
WHITESPACE_CHAR = [ \t]
LINE_TERMINATOR = \r|\n|\r\n
WHITESPACE = {LINE_TERMINATOR} | [ \t\f]
COMMENT = "[[-" ~"]]"
TAG_OPEN= "[["
TAG_CLOSE= "]]"
SIGN_PLS=[\+]
SIGN_LINK=[\~]
IDENTIFIER=[a-zA-Z0-9_\.]
ID=[\d]+

%state LINK
%state My
%%

<YYINITIAL> {

~"[[" {
          // backtrack over any stache characters at the end of this string
          while (yylength() > 0 && yytext().subSequence(yylength() - 1, yylength()).toString().equals("[")) {
            yypushback(1);
          }
          yypushState(My);
          if (!yytext().toString().equals("")) {
              if (yytext().toString().trim().length() == 0) {
                  return TokenType.WHITE_SPACE;
              } else {
                  return MyTypes.CONTENT;
              }
          }
        }
  {COMMENT}        {return MyTypes.COMMENT; }
  !([^]*"[["[^]*)  { return MyTypes.CONTENT; }
}

<My> {
    {COMMENT}        {return MyTypes.COMMENT; }
    {TAG_OPEN}   {  return MyTypes.TAG_OPEN; }
    {SIGN_PLS} { return MyTypes.SIGN_PLS;}
    {SIGN_LINK} {  yypushState(LINK); return MyTypes.SIGN_LINK;}
    {IDENTIFIER}+ { return MyTypes.IDENTIFIER; }
    {TAG_CLOSE}/[^"]]"] {  yypopState();  return MyTypes.TAG_CLOSE; }
    {TAG_CLOSE}/["]]"] {  return MyTypes.TAG_CLOSE; }
    {WHITESPACE} {  return TokenType.WHITE_SPACE; }
    [^]  {return TokenType.BAD_CHARACTER;}
 }

<LINK> {
  {ID}  {  yypopState();  return MyTypes.ID; }
  {TAG_OPEN}  {  yypopState();  return MyTypes.TAG_OPEN; }
   [^]     {  yypopState(); return TokenType.BAD_CHARACTER; }
}

{WHITESPACE}+ { return TokenType.WHITE_SPACE; }
