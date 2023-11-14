package com.example.mylanguage.lang.braces;

import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.codeInsight.highlighting.BraceMatcher;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class MyBraceMatcher implements BraceMatcher {

    private static final Set<IElementType> LEFT_BRACES = new HashSet<>();
    private static final Set<IElementType> RIGHT_BRACES = new HashSet<>();

    static {
        LEFT_BRACES.add(MyTypes.TAG_OPEN);
        RIGHT_BRACES.add(MyTypes.TAG_CLOSE);
    }


    @Override
    public boolean isPairBraces(@NotNull IElementType tokenType1, @NotNull IElementType tokenType2) {
        return LEFT_BRACES.contains(tokenType1) && RIGHT_BRACES.contains(tokenType2)
                || RIGHT_BRACES.contains(tokenType1) && LEFT_BRACES.contains(tokenType2);
    }

    @Override
    public boolean isLBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText, @NotNull FileType fileType) {
        return LEFT_BRACES.contains(iterator.getTokenType());
    }

    @Override
    public boolean isRBraceToken(@NotNull HighlighterIterator iterator, @NotNull CharSequence fileText, @NotNull FileType fileType) {
        if (!RIGHT_BRACES.contains(iterator.getTokenType())) {
            return false;
        }

        boolean isRBraceToken = false;
        int iteratorRetreatCount = 0;
        while (true) {
            iterator.retreat();
            iteratorRetreatCount++;

            if (iterator.atEnd()) {
                break;
            }

            if (iterator.getTokenType() == MyTypes.TAG_OPEN
                  /*  || iterator.getTokenType() == MyTypes.BACKTICK_OPEN*/) {
                // the first open token we encountered was a simple opener (i.e. didn't start a block)
                // or the close brace of a close block 'stache for some open block.  Definitely a right brace.
                isRBraceToken = true;
            }
        }

        // reset the given iterator before returning
        while (iteratorRetreatCount-- > 0) {
            iterator.advance();
        }

        return isRBraceToken;
    }

    @Override
    public int getBraceTokenGroupId(@NotNull IElementType tokenType) {
        return 1;
    }

    @Override
    public boolean isStructuralBrace(@NotNull HighlighterIterator iterator, @NotNull CharSequence text, @NotNull FileType fileType) {
        return false;
    }

    @Nullable
    @Override
    public IElementType getOppositeBraceTokenType(@NotNull IElementType type) {
        return null;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(@NotNull PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
