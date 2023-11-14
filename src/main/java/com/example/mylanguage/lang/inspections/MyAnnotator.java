package com.example.mylanguage.lang.inspections;

import com.example.mylanguage.lang.psi.elements.MyTagElement;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import org.jetbrains.annotations.NotNull;

public class MyAnnotator implements Annotator {

    private static final Logger LOG = Logger.getInstance(MyAnnotator.class);

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiErrorElement && element.getParent() instanceof MyTagElement) {
            MyTagElement tag = (MyTagElement) element.getParent();
            if (tag.getTagClose() == null) {
                createErrorAnnotation(holder,"Expected Close \"]]\"");
            }
        }
    }

    private void createErrorAnnotation(final @NotNull AnnotationHolder holder, final @NotNull String message) {
        holder.newAnnotation(HighlightSeverity.ERROR, message)
                //    .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create();
    }

    private void createErrorAnnotation(
            final @NotNull AnnotationHolder holder,
            final @NotNull PsiElement element,
            final @NotNull String message
    ) {
        holder.newAnnotation(HighlightSeverity.ERROR, message).range(element).create();
    }
}
