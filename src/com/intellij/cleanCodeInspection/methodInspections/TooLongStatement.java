package com.intellij.cleanCodeInspection.methodInspections;

import com.intellij.codeInsight.daemon.GroupNames;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

/**
 * Created by nkanakis on 9/15/2017.
 */
public class TooLongStatement extends BaseJavaLocalInspectionTool {

    private static final int MAX_CHARS_PER_STATEMENT = 500 ;
    private static final String ERROR_MESSAGE ="Statement is too large";

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    public String getShortName() {
        return "TooLongStatement";
    }

    @NotNull
    public String getGroupDisplayName() {
        return GroupNames.BUGS_GROUP_NAME;
    }

    @NotNull
    public String getDisplayName() {
        return "Statement should not have more than "+ ERROR_MESSAGE +" characters";    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, final boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitStatement(PsiStatement statement) {
                checkStatementLength(statement, holder);
            }
        };
    }

    private void checkStatementLength(PsiStatement statement, ProblemsHolder holder) {
        if(statement == null)
            return;
        if(statement.getTextLength() > MAX_CHARS_PER_STATEMENT)
            registerProblem(statement, holder);
    }

    private void registerProblem(PsiElement body, ProblemsHolder holder) {
        holder.registerProblem(body, ERROR_MESSAGE);
    }
}
