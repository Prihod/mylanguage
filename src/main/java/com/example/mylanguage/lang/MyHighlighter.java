package com.example.mylanguage.lang;

import com.example.mylanguage.lang.lexer.MyFlexAdapter;
import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MyHighlighter extends SyntaxHighlighterBase {

    @Override
    @NotNull
    public Lexer getHighlightingLexer() {
        return new MyFlexAdapter();
    }

    private static final TextAttributesKey SEPARATOR = TextAttributesKey.createTextAttributesKey(
            "My_SEPARATOR",
            DefaultLanguageHighlighterColors.OPERATION_SIGN

    ); private static final TextAttributesKey METHOD = TextAttributesKey.createTextAttributesKey(
            "My_METHOD",
            DefaultLanguageHighlighterColors.INSTANCE_METHOD
    );

    private static final TextAttributesKey TAG = TextAttributesKey.createTextAttributesKey(
            "My_TAG",
            DefaultLanguageHighlighterColors.BRACES
    );

    private static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "My_IDENTIFIER",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
          //  DefaultLanguageHighlighterColors.KEYWORD
    );

    private static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(
            "My_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT
    );

    private static final TextAttributesKey OPERATOR = TextAttributesKey.createTextAttributesKey(
            "My_OPERATOR",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
    );
    private static final TextAttributesKey VAR = TextAttributesKey.createTextAttributesKey(
            "My_VAR",
            DefaultLanguageHighlighterColors.INSTANCE_FIELD
    );

    private static final TextAttributesKey VALUE = TextAttributesKey.createTextAttributesKey(
            "My_VALUES",
            DefaultLanguageHighlighterColors.NUMBER
    );

    private static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
            "My_STRING",
            DefaultLanguageHighlighterColors.STRING
    );

    private static final TextAttributesKey ESCAPE = TextAttributesKey.createTextAttributesKey(
            "My_ESCAPE",
            DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE
    );
    private static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
            "My_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
    );

    public static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
            "My_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] METHOD_KEYS = new TextAttributesKey[]{METHOD};
    private static final TextAttributesKey[] VAR_KEYS = new TextAttributesKey[]{VAR};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] TAG_KEYS = new TextAttributesKey[]{TAG};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] ESCAPE_KEYS = new TextAttributesKey[]{ESCAPE};

    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    private static final HashSet<IElementType> TAGS = new HashSet<IElementType>(Arrays.asList(
            MyTypes.TAG_OPEN,
            MyTypes.TAG_CLOSE
    ));

    private static final HashSet<IElementType> SIGNS = new HashSet<IElementType>(Arrays.asList(
            MyTypes.SIGN_PLS,
            MyTypes.SIGN_LINK
    ));
    private static final HashSet<IElementType> VALUES = new HashSet<IElementType>(Arrays.asList(
            MyTypes.ID
    ));

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (TAGS.contains(tokenType)) {
            return TAG_KEYS;
        } else if (VALUES.contains(tokenType)) {
            return VALUE_KEYS;
        } else if (SIGNS.contains(tokenType)) {
            return OPERATOR_KEYS;
        } else if (tokenType.equals(MyTypes.IDENTIFIER)) {
            return VAR_KEYS;
        } else if (tokenType.equals(MyTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}