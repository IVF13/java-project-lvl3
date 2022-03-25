package hexlet.code.schemas;

import java.util.List;

public final class NumberSchema extends BaseSchema {
    private final int[] thresholdValues = new int[2];

    public int[] getThresholdValues() {
        return thresholdValues;
    }

    public NumberSchema positive() {
        getCheckList().add(SchemaChecks.required);
        getCheckList().add(SchemaChecks.positive);
        return this;
    }

    public NumberSchema range(int lowerThreshold, int upperThreshold) {
        getCheckList().add(SchemaChecks.required);
        getCheckList().add(SchemaChecks.range);
        this.thresholdValues[0] = lowerThreshold;
        this.thresholdValues[1] = upperThreshold;
        return this;
    }

    static boolean isValid(List<SchemaChecks> checkList,
                           int[] thresholdValues, Object numberToValidate) {
        boolean isValid = true;

        isValid = isRequired(checkList, numberToValidate, isValid);

        isValid = isPositive(checkList, numberToValidate, isValid);

        isValid = isInRange(checkList, thresholdValues, numberToValidate, isValid);

        return isValid;
    }

    private static boolean isRequired(List<SchemaChecks> checkList, Object numberToValidate, boolean isValid) {
        if (checkList.contains(SchemaChecks.required)) {
            if (!(numberToValidate instanceof Integer)) {
                return false;
            }
        }

        return isValid;
    }

    private static boolean isPositive(List<SchemaChecks> checkList, Object numberToValidate, boolean isValid) {
        if (checkList.contains(SchemaChecks.positive)) {
            if (numberToValidate instanceof Integer && (Integer) numberToValidate <= 0) {
                return false;
            }
        }

        return isValid;
    }

    private static boolean isInRange(List<SchemaChecks> checkList,
                                     int[] thresholdValues, Object numberToValidate, boolean isValid) {
        if (checkList.contains(SchemaChecks.range)) {
            if (numberToValidate instanceof Integer && !((Integer) numberToValidate >= thresholdValues[0]
                    && (Integer) numberToValidate <= thresholdValues[1])) {
                return false;
            }
        }

        return isValid;
    }

}
