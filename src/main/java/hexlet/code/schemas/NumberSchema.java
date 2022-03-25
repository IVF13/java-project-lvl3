package hexlet.code.schemas;

import hexlet.code.NumberSchemaValidator;

import java.util.ArrayList;
import java.util.List;

public final class NumberSchema {
    private final List<NumberSchemaChecks> checkList = new ArrayList<>();
    private int lowerThreshold;
    private int upperThreshold;
    private final NumberSchemaValidator validator; //

    public NumberSchema() {
        this.validator = new NumberSchemaValidator();
    }

    public boolean isValid(Object numberToValidate) {
        return validator.isValid(checkList, lowerThreshold, upperThreshold, numberToValidate);
    }

    public void required() {
        checkList.add(NumberSchemaChecks.required);
    }

    public NumberSchema positive() {
        checkList.add(NumberSchemaChecks.required);
        checkList.add(NumberSchemaChecks.positive);
        return this;
    }

    public NumberSchema range(int lowerNum, int upperNum) {
        checkList.add(NumberSchemaChecks.required);
        checkList.add(NumberSchemaChecks.range);
        this.lowerThreshold = lowerNum;
        this.upperThreshold = upperNum;
        return this;
    }


}
