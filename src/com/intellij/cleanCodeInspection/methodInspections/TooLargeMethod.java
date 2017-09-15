package com.intellij.cleanCodeInspection.methodInspections;

import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

/**
 * Created by nkanakis on 9/15/2017.
 */
public class TooLargeMethod extends BaseJavaLocalInspectionTool {

    private static final int MAX_STATEMENTS = 30 ;
    private static final String ERROR_MESSAGE ="Method is too large";

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    public String getShortName() {
        return "TooLargeMethod";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, final boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitMethod(PsiMethod method) {
               checkNumberOfStatements(method.getBody(), holder);
            }
        };
    }

    private void checkNumberOfStatements(PsiCodeBlock body, ProblemsHolder holder) {
        if(body == null)
            return;

        if(body.getStatements().length> MAX_STATEMENTS){
            registerProblem(body, holder);
        }
    }

    private void registerProblem(PsiElement body, ProblemsHolder holder) {
        holder.registerProblem(body, ERROR_MESSAGE);
    }
}
