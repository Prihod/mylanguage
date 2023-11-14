// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.example.mylanguage.lang.psi;

import com.example.mylanguage.lang.MyFileType;
import com.example.mylanguage.lang.MyLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class MyPsiFile extends PsiFileBase {

    public MyPsiFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MyLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return MyFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "MyFile:" + getName();
    }
}
