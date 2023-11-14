package com.example.mylanguage.lang.completion;

import com.example.mylanguage.lang.psi.MyPsiFile;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.editorActions.CompletionAutoPopupHandler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyCompletionAutoPopupHandler extends CompletionAutoPopupHandler {
    private static final Logger LOG = Logger.getInstance(MyCompletionAutoPopupHandler.class);

    final private Map<Character, Character> allowedPairs = new HashMap<Character, Character>() {{
        put('+', '+');
    }};

    final private Set<Character> allowedCharacters = new HashSet<Character>() {{
        add('=');
        add('$');
        add('&');
        add(':');
    }};

    @Override
    public @NotNull Result checkAutoPopup(char charTyped, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if (!(file instanceof MyPsiFile)) {
            return Result.DEFAULT;
        }

       // final PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());

        if (allowedCharacters.contains(charTyped)) {
            AutoPopupController.getInstance(project).scheduleAutoPopup(editor);
            return Result.STOP;

        } else if (allowedPairs.containsKey(charTyped)) {
            int offset = editor.getCaretModel().getOffset();
            String text = editor.getDocument().getText();
            Character pairChar = allowedPairs.get(charTyped);
            if (offset > 0 && text.length() - 1 >= offset && text.charAt(offset - 1) == pairChar) {
                AutoPopupController.getInstance(project).scheduleAutoPopup(editor);
                return Result.STOP;
            }
            return Result.CONTINUE;
        }
        return super.checkAutoPopup(charTyped, project, editor, file);
    }
}
