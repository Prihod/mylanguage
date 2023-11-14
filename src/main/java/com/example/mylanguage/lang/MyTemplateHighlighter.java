package com.example.mylanguage.lang;

import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyTemplateHighlighter extends LayeredLexerEditorHighlighter {
    public MyTemplateHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        // create main highlighter
        super(new MyHighlighter(), colors);

        // highlighter for outer lang
        FileType type = null;
        if (project == null || virtualFile == null) {
            type = FileTypes.PLAIN_TEXT;
        } else {
            Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(virtualFile);
            if (language != null) type = language.getAssociatedFileType();
            if (type == null) type = MyLanguage.getDefaultTemplateLang();
        }

        SyntaxHighlighter outerHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(type, project, virtualFile);

        registerLayer(MyTypes.CONTENT, new LayerDescriptor(outerHighlighter, ""));
        //registerLayer(MyTypes.STRING_LITERAL, new LayerDescriptor(outerHighlighter, ""));
    }
}