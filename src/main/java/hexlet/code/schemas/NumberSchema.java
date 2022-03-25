package hexlet.code.schemas;

import hexlet.code.NumberSchemaValidator;

import java.util.ArrayList;
import java.util.List;

public final class NumberSchema {
    private final List<NumberSchemaChecks> checkList = new ArrayList<>();
    private final int[] thresholdValues = new int[2];
    private final NumberSchemaValidator validator; //

    public NumberSchema() {
        this.validator = new NumberSchemaValidator();
    }

    public boolean isValid(Object numberToValidate) {
        return validator.isValid(checkList, thresholdValues, numberToValidate);
    }

    public void required() {
        checkList.add(NumberSchemaChecks.required);
    }

    public NumberSchema positive() {
        checkList.add(NumberSchemaChecks.required);
        checkList.add(NumberSchemaChecks.positive);
        return this;
    }

    public NumberSchema range(int lowerThreshold, int upperThreshold) {
        checkList.add(NumberSchemaChecks.required);
        checkList.add(NumberSchemaChecks.range);
        this.thresholdValues[0] = lowerThreshold;
        this.thresholdValues[1] = upperThreshold;
        return this;
    }


}
