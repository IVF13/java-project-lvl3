package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private final int[] thresholdValues = new int[2];

    public NumberSchema required() {
        checkList.add(SchemasChecks.REQUIRED);
        return this;
    }

    public NumberSchema positive() {
        checkList.add(SchemasChecks.POSITIVE);
        return this;
    }

    public NumberSchema range(int lowerThreshold, int upperThreshold) {
        checkList.add(SchemasChecks.RANGE);
        this.thresholdValues[0] = lowerThreshold;
        this.thresholdValues[1] = upperThreshold;
        return this;
    }

    @Override
    public boolean isPassesOtherTests(Object numberToValidate, boolean isValid) {

        isValid = isPositive(numberToValidate, isValid);

        isValid = isInRange(numberToValidate, isValid);

        return isValid;
    }

    @Override
    public boolean isRequired(Object numberToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.REQUIRED) && !(numberToValidate instanceof Integer)) {
            return false;
        }
        return isValid;
    }

    private boolean isPositive(Object numberToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.POSITIVE) && numberToValidate instanceof Integer) {
            if ((Integer) numberToValidate <= 0) {
                return false;
            }
        }

        return isValid;
    }

    private boolean isInRange(Object numberToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.RANGE) && numberToValidate instanceof Integer) {
            if (!((Integer) numberToValidate >= getThresholdValues()[0]
                    && (Integer) numberToValidate <= getThresholdValues()[1])) {
                return false;
            }
        }

        return isValid;
    }

    public int[] getThresholdValues() {
        return thresholdValues;
    }

}
