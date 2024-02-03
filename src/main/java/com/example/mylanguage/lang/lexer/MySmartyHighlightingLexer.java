package com.example.mylanguage.lang.lexer;

import com.example.mylanguage.lang.MyTemplateHighlighter;
import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class MySmartyHighlightingLexer extends LookAheadLexer {
    private static final Logger LOG = Logger.getInstance(MySmartyHighlightingLexer.class);

    public MySmartyHighlightingLexer(@NotNull Lexer baseLexer) {
        super(baseLexer,1);
    }

    @Override
    protected void lookAhead(Lexer baseLexer) {
        IElementType currentToken = baseLexer.getTokenType();

        if (currentToken == MyTypes.CONTENT) {
            advanceLexer(baseLexer);
           // replaceCachedType(0, MyTypes.CONTENT);

        } else {
            super.lookAhead(baseLexer);
        }
    }
}
