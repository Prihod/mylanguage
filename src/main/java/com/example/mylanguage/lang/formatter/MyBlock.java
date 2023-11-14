package com.example.mylanguage.lang.formatter;

import com.example.mylanguage.lang.MyLanguage;
import com.example.mylanguage.lang.psi.MyTokenSets;
import com.example.mylanguage.lang.psi.MyTypes;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.xml.XmlFormattingPolicy;
import com.intellij.psi.tree.IElementType;
import com.intellij.xml.template.formatter.AbstractXmlTemplateFormattingModelBuilder;
import com.intellij.xml.template.formatter.TemplateLanguageBlock;
import com.jetbrains.php.lang.psi.PhpPsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyBlock extends TemplateLanguageBlock {
    private static final Logger LOG = Logger.getInstance(MyBlock.class);

    final private boolean isPair;

    protected MyBlock(AbstractXmlTemplateFormattingModelBuilder builder, @NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, CodeStyleSettings settings, XmlFormattingPolicy xmlFormattingPolicy, @Nullable Indent indent) {
        super(builder, node, wrap, alignment, settings, xmlFormattingPolicy, indent);
        if (getNode().getFirstChildNode() == null) {
            isPair = false;
        } else {
            IElementType lastType = getNode().getLastChildNode().getElementType();
            IElementType firstType = getNode().getFirstChildNode().getElementType();
            isPair = firstType == MyTypes.TAG_OPEN && lastType == MyTypes.TAG_CLOSE;
        }
    }

    @Override
    protected @NotNull Indent getChildIndent(@NotNull ASTNode node) {

        if (node.getPsi().getLanguage() == MyLanguage.INSTANCE) {
            if (!isPair || isOpening(node) || isClosing(node)) {
                return Indent.getNoneIndent();
            }
        }
        return Indent.getNormalIndent();
    }

    @Override
    protected Spacing getSpacing(TemplateLanguageBlock adjacentBlock) {
        return null;
    }

    @Override
    public @Nullable Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {

        return new SpacingBuilder(getSettings(), MyLanguage.INSTANCE)

                .after(MyTypes.TAG_OPEN)
                .none()

                .before(MyTypes.TAG_CLOSE)
                .none()

                .around(MyTokenSets.SIGNS)
                .none()

                .around(MyTokenSets.IDENTIFIERS)
                .none()

                .around(MyTypes.ID)
                .none()

                .getSpacing(this, child1, child2);
    }

    @Override
    public @Nullable Wrap getWrap() {
        return super.getWrap();
    }

    private boolean isOpening(ASTNode node) {
        return isBellowType(node, MyTypes.TAG_OPEN);
    }

    private boolean isClosing(ASTNode node) {
        return isBellowType(node, MyTypes.TAG_CLOSE);
    }

    private boolean isBellowType(ASTNode node, IElementType type) {
        do {
            if (node.getElementType() == type) {
                return true;
            }
            if (node == getNode()) {
                return false;
            }
            node = node.getTreeParent();
        } while (node != null);
        return false;
    }

    private List<ASTNode> getChildrenOfType(ASTNode node, IElementType type) {
        List<ASTNode> childrenOfType = new ArrayList<>();
        ASTNode child = node.getFirstChildNode();

        while (child != null) {
            if (child.getElementType() == type) {
                childrenOfType.add(child);
            }
            childrenOfType.addAll(getChildrenOfType(child, type));
            child = child.getTreeNext();
        }
        return childrenOfType;
    }


    private boolean isInsideHtmlQuotes(@Nullable ASTNode node) {

        if (node == null) {
            return false;
        }

        ASTNode prevNode = node.getTreePrev();
        if (!isOuterElement(prevNode)) {
            return false;
        }

        if (!hasTextQuote(prevNode.getText(), "last")) {
            return false;
        }

        ASTNode nextNode = node.getTreeNext();
        if (!isOuterElement(nextNode)) {
            return false;
        }

        if (!hasTextQuote(nextNode.getText(), "first")) {
            return false;
        }

        return true;
    }

    private boolean hasTextQuote(String text, String position) {
        if (text.length() > 0) {
            char ch;
            if (position == "first") {
                ch = text.charAt(0);
            } else {
                ch = text.charAt(text.length() - 1);
            }
            return ch == '"' || ch == '\'';
        }
        return false;
    }

    private boolean isOuterElement(@Nullable ASTNode node) {
        return node != null && PhpPsiUtil.isOfType(node, MyTypes.CONTENT);
    }


}
