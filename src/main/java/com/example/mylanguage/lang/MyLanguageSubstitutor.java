package com.example.mylanguage.lang;

import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.LanguageSubstitutor;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.smarty.SmartyFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyLanguageSubstitutor extends LanguageSubstitutor {
    @Override
    public @Nullable Language getLanguage(@NotNull VirtualFile file, @NotNull Project project) {
        if (file instanceof LightVirtualFile) {
            return null;
        }
        if (
                FileTypeRegistry.getInstance().isFileOfType(file, HtmlFileType.INSTANCE) ||
                        FileTypeRegistry.getInstance().isFileOfType(file, SmartyFileType.INSTANCE)
        ) {
            return MyLanguage.INSTANCE;
        }
        return null;
    }
}
