package com.example.mylanguage.lang.parser;

import com.example.mylanguage.lang.MyLanguage;
import com.example.mylanguage.lang.lexer.MyFlexAdapter;
import com.example.mylanguage.lang.psi.MyPsiFile;
import com.example.mylanguage.lang.psi.MyTokenSets;
import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class MyParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE_ELEMENT_TYPE = new IStubFileElementType<>("My", MyLanguage.INSTANCE);

    @Override
    @NotNull
    public Lexer createLexer(Project project) {
        return new MyFlexAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new MyParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE_ELEMENT_TYPE;
    }

    @Override
    @NotNull
    public TokenSet getWhitespaceTokens() {
        return MyTokenSets.WHITESPACES;
    }

    @Override
    @NotNull
    public TokenSet getCommentTokens() {
        return MyTokenSets.COMMENTS;
    }

    @Override
    @NotNull
    public TokenSet getStringLiteralElements() {
        return MyTokenSets.STRINGS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return MyTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new MyPsiFile(viewProvider);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}