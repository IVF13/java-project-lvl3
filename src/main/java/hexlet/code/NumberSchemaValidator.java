package hexlet.code;

import hexlet.code.schemas.NumberSchemaChecks;

import java.util.List;

public final class NumberSchemaValidator {

    public boolean isValid(List<NumberSchemaChecks> checkList,
                           int lowerThreshold, int upperThreshold, Object numberToValidate) {
        boolean isValid = true;

        if (checkList.contains(NumberSchemaChecks.required)) {
            if (!(numberToValidate instanceof Integer)) {
                isValid = false;
            }
        }

        if (checkList.contains(NumberSchemaChecks.positive)) {
            if (numberToValidate instanceof Integer && (Integer) numberToValidate <= 0) {
                isValid = false;
            }
        }

        if (checkList.contains(NumberSchemaChecks.range)) {
            if (numberToValidate instanceof Integer && !((Integer) numberToValidate >= lowerThreshold
                    && (Integer) numberToValidate <= upperThreshold)) {
                isValid = false;
            }
        }

        return isValid;
    }

}
