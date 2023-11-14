package com.example.mylanguage.lang.psi;

import com.example.mylanguage.lang.MyFileType;
import com.example.mylanguage.lang.psi.elements.MyTagElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.tree.IElementType;

public class MyElementFactory {

    public static MyTagElement createTag(PsiElement element, String name) {
        ASTNode node = element.getNode();
        return createTag(element.getProject(), name, node.getElementType());
    }

    public static MyTagElement createTag(Project project, String name, IElementType tagType) {
        String tag;
       if (tagType.equals(MyTypes.PLS_VAR)) {
            tag = "[[+" + name + "]]";
        } else if (tagType.equals(MyTypes.LINK_VAR)) {
            tag = "[[~" + name + "]]";
        }  else {
            tag = "[[]]";
        }
        final MyPsiFile file = createFile(project, tag);
        return (MyTagElement) file.getFirstChild();
    }

    public static MyPsiFile createFile(Project project, String text) {
        String name = "dummy.My";
        return (MyPsiFile) PsiFileFactory.getInstance(project).createFileFromText(name, MyFileType.INSTANCE, text);
    }

    public static PsiElement createCRLF(Project project) {
        final MyPsiFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

}
