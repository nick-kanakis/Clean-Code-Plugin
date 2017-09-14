package com.intellij.cleanCodeInspection;

import com.intellij.codeInspection.InspectionToolProvider;

/**
 * Created by nkanakis on 9/14/2017.
 */
public class MethodLevelInspectionsProvider implements InspectionToolProvider {
    @Override
    public Class[] getInspectionClasses() {
        return new Class[]{
                TooManyArgumentsInspection.class
        };
    }
}
