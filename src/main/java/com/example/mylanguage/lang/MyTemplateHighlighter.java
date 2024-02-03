package com.example.mylanguage.lang;

import com.example.mylanguage.lang.lexer.MySmartyHighlightingLexer;
import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.lang.Language;
import com.intellij.lexer.HtmlHighlightingLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import com.jetbrains.smarty.SmartyFileType;
import com.jetbrains.smarty.SmartyFileViewProvider;
import com.jetbrains.smarty.SmartyHighlighter;
import com.jetbrains.smarty.lang.SmartyElementTypes;
import com.jetbrains.smarty.lang.SmartyTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyTemplateHighlighter extends LayeredLexerEditorHighlighter {
    private static final Logger LOG = Logger.getInstance(MyTemplateHighlighter.class);

    public MyTemplateHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        // create main highlighter
       // super(new MyHighlighter(), colors);

        super(new MyHighlighter() {
            @Override
            public @NotNull Lexer getHighlightingLexer() {
                return new MySmartyHighlightingLexer(super.getHighlightingLexer());
            }
        }, colors);


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
        SyntaxHighlighter htmlHighlighter =  SyntaxHighlighterFactory.getSyntaxHighlighter(HtmlFileType.INSTANCE, project, virtualFile);
        SmartyHighlighter smartyHighlighter  = new  SmartyHighlighter(project,virtualFile,colors);
        registerLayer(MyTypes.CONTENT, new LayerDescriptor(smartyHighlighter.getSyntaxHighlighter(), ""));
    }

    @NotNull
    private static SyntaxHighlighter getHighlighter(Project project, VirtualFile virtualFile) {
        LanguageFileType fileType = SmartyFileType.INSTANCE;
        SyntaxHighlighter highlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(fileType, project, virtualFile);
        assert highlighter != null;

        return highlighter;
    }
}