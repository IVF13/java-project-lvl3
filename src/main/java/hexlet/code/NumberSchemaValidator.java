package hexlet.code;

import hexlet.code.schemas.NumberSchemaChecks;

import java.util.List;

public final class NumberSchemaValidator {

    public boolean isValid(List<NumberSchemaChecks> checkList,
                           int lowerThreshold, int upperThreshold, Object numberToValidate) {
        boolean isValid = true;

        isValid = isRequired(checkList, numberToValidate, isValid);

        isValid = isPositive(checkList, numberToValidate, isValid);

        isValid = isInRange(checkList, lowerThreshold, upperThreshold, numberToValidate, isValid);

        return isValid;
    }

    private boolean isRequired(List<NumberSchemaChecks> checkList, Object numberToValidate, boolean isValid) {
        if (checkList.contains(NumberSchemaChecks.required)) {
            if (!(numberToValidate instanceof Integer)) {
                return false;
            }
        }

        return isValid;
    }

    private boolean isPositive(List<NumberSchemaChecks> checkList, Object numberToValidate, boolean isValid) {
        if (checkList.contains(NumberSchemaChecks.positive)) {
            if (numberToValidate instanceof Integer && (Integer) numberToValidate <= 0) {
                return false;
            }
        }

        return isValid;
    }

    private boolean isInRange(List<NumberSchemaChecks> checkList,
                              int lowerThreshold, int upperThreshold, Object numberToValidate, boolean isValid) {
        if (checkList.contains(NumberSchemaChecks.range)) {
            if (numberToValidate instanceof Integer && !((Integer) numberToValidate >= lowerThreshold
                    && (Integer) numberToValidate <= upperThreshold)) {
                return false;
            }
        }

        return isValid;
    }

}


