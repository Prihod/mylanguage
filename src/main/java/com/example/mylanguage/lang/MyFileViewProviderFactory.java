package com.example.mylanguage.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

public class MyFileViewProviderFactory implements FileViewProviderFactory {

    @NotNull
    @Override
    public FileViewProvider createFileViewProvider(@NotNull VirtualFile virtualFile,
                                                   Language language,
                                                   @NotNull PsiManager psiManager,
                                                   boolean eventSystemEnabled) {
        assert language.isKindOf(MyLanguage.INSTANCE);
        return new MyFileViewProvider(psiManager, virtualFile, eventSystemEnabled, language);
    }
}
