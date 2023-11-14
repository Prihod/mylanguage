// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.example.mylanguage.lang.psi;

import com.example.mylanguage.lang.MyLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MyElement extends IElementType {

    public MyElement(@NotNull @NonNls String debugName) {
        super(debugName, MyLanguage.INSTANCE);
    }

}
