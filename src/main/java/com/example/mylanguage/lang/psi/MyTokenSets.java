// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package com.example.mylanguage.lang.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

public interface MyTokenSets {

    TokenSet IDENTIFIERS = TokenSet.create(MyTypes.IDENTIFIER);
    TokenSet COMMENTS = TokenSet.create(MyTypes.COMMENT);
    TokenSet STRINGS = TokenSet.create(MyTypes.CONTENT);
    TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);
    TokenSet TAGS = TokenSet.create(MyTypes.TAG_OPEN, MyTypes.TAG_CLOSE);
    TokenSet TAG_TYPES = TokenSet.create(
            MyTypes.LINK_VAR,
            MyTypes.PLS_VAR
    );
    TokenSet TAG_SIGNS = TokenSet.create(
            MyTypes.SIGN_LINK,
            MyTypes.SIGN_PLS

    );
    TokenSet SIGNS = TokenSet.create(
            MyTypes.SIGN_LINK,
            MyTypes.SIGN_PLS
    );

}
