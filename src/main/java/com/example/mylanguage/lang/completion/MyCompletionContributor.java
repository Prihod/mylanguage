package com.example.mylanguage.lang.completion;

import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

// See https://raw.githubusercontent.com/antlr/jetbrains/master/doc/plugin-dev-notes.md
public class MyCompletionContributor extends CompletionContributor {
    private static final Logger LOG = Logger.getInstance(MyCompletionContributor.class);

    public MyCompletionContributor() {

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(MyTypes.ID), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement element = parameters.getPosition();
                PsiElement parent = element.getParent();
                PsiElement prevSibling = element.getPrevSibling();
                PsiElement nextSibling = element.getNextSibling();

                LOG.warn("---------------ID------------------");
                LOG.warn("position=" + element);
                LOG.warn("original position=" +  parameters.getOriginalPosition());
                LOG.warn("parent=" + parent);
                if (prevSibling != null) {
                    LOG.warn("prevSibling=" + prevSibling);
                }
                if (nextSibling != null) {
                    LOG.warn("nextSibling=" + nextSibling);
                }
            }
        });

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(MyTypes.IDENTIFIER), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement element = parameters.getPosition();
                PsiElement parent = element.getParent();
                PsiElement prevSibling = element.getPrevSibling();
                PsiElement nextSibling = element.getNextSibling();

                LOG.warn("---------------IDENTIFIER------------------");
                LOG.warn("position=" + element);
                LOG.warn("original position=" +  parameters.getOriginalPosition());
                LOG.warn("parent=" + parent);
                if (prevSibling != null) {
                    LOG.warn("prevSibling=" + prevSibling);
                }
                if (nextSibling != null) {
                    LOG.warn("nextSibling=" + nextSibling);
                }
            }
        });

       /* extend(CompletionType.BASIC, PlatformPatterns.psiElement(), new CompletionProvider<CompletionParameters>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiElement element = parameters.getPosition();
                PsiElement parent = element.getParent();
                PsiElement prevSibling = element.getPrevSibling();
                PsiElement nextSibling = element.getNextSibling();

                LOG.warn("---------------ALL------------------");
                LOG.warn("position=" + element);
                LOG.warn("original position=" +  parameters.getOriginalPosition());
                LOG.warn("parent=" + parent);
                if (prevSibling != null) {
                    LOG.warn("prevSibling=" + prevSibling);
                }
                if (nextSibling != null) {
                    LOG.warn("nextSibling=" + nextSibling);
                }
            }
        });*/

    }

}
