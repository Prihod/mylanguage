package com.example.mylanguage.lang.lexer;

import com.example.mylanguage.lang.MyLexer;
import com.intellij.lexer.FlexAdapter;

public class MyFlexAdapter extends FlexAdapter {

    public MyFlexAdapter() {
        super(new MyLexer(null));
    }
}
