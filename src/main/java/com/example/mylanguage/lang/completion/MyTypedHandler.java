package com.example.mylanguage.lang.completion;

import com.example.mylanguage.lang.MyLanguage;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import org.jetbrains.annotations.NotNull;

public class MyTypedHandler extends TypedHandlerDelegate {
    private static final Logger LOG = Logger.getInstance(MyTypedHandler.class);
    private static boolean isAutocompleteTagEnabled = true;

    @NotNull
    @Override
    public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        FileViewProvider provider = file.getViewProvider();

        if (!provider.getBaseLanguage().isKindOf(MyLanguage.INSTANCE)) {
            return Result.CONTINUE;
        }

        if (offset < 2 || offset > editor.getDocument().getTextLength()) {
            return Result.CONTINUE;
        }

        String previousChar = editor.getDocument().getText(new TextRange(offset - 2, offset - 1));

        if (file.getLanguage() instanceof MyLanguage) {
            if (isAutocompleteTagEnabled && c == ']' && !previousChar.equals("]")) {
                PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
                PsiElement elementAt = provider.findElementAt(offset - 1, provider.getBaseLanguage());
                if (PhpPsiUtil.isOfType(elementAt, TokenType.BAD_CHARACTER)) {
                    String braceCompleter = "]";
                    editor.getDocument().insertString(offset, braceCompleter);
                    offset += braceCompleter.length();
                    editor.getCaretModel().moveToOffset(offset);
                }
            }
        }
        return Result.CONTINUE;
    }
}
