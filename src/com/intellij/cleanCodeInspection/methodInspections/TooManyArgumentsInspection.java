package com.intellij.cleanCodeInspection.methodInspections;

import com.intellij.codeInsight.daemon.GroupNames;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

/**
 * Created by nkanakis on 9/14/2017.
 */
public class TooManyArgumentsInspection extends BaseJavaLocalInspectionTool {

    private static final int MAX_PARAMETERS = 3;
    private static final String ERROR_MESSAGE ="Too many parameters.";

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    public String getShortName() {
        return "TooManyArguments";
    }

    @NotNull
    public String getGroupDisplayName() {
        return GroupNames.BUGS_GROUP_NAME;
    }

    @NotNull
    public String getDisplayName() {
        return "Method should not have more than "+ MAX_PARAMETERS +" arguments";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, final boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitMethod(PsiMethod method) {
                checkParameters(method.getParameterList(), holder);
            }
        };
    }

    private void checkParameters(PsiParameterList parameterList, ProblemsHolder holder) {
        if(parameterList == null)
            return;
        if(parameterList.getParametersCount()> MAX_PARAMETERS){
            registerProblem(parameterList, holder);
        }
    }

    private void registerProblem(PsiElement parameterList, ProblemsHolder holder) {
        holder.registerProblem(parameterList, ERROR_MESSAGE);
    }
}
