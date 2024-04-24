package com.example.server.infrastructure.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duchieu212
 */
public class ValidationChain<T> {

    private List<ValidationSteps<T>> listSteps = new ArrayList<>();

    public void addStep(ValidationSteps<T> step) {
        this.listSteps.add(step);
    }

    public void validatedAll(T record) {
        for (ValidationSteps<T> step : listSteps) {
            step.validated(record);
        }
    }
}
