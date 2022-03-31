package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private final int[] thresholdValues = new int[2];

    public NumberSchema required() {
        getCheckList().add(SchemasChecks.required);
        return this;
    }

    public NumberSchema positive() {
        this.required();
        getCheckList().add(SchemasChecks.positive);
        return this;
    }

    public NumberSchema range(int lowerThreshold, int upperThreshold) {
        this.required();
        getCheckList().add(SchemasChecks.range);
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
        if (this.getCheckList().contains(SchemasChecks.required)) {
            if (!(numberToValidate instanceof Integer)) {
                return false;
            }
        }

        return isValid;
    }

    private boolean isPositive(Object numberToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.positive)) {
            if ((Integer) numberToValidate <= 0) {
                return false;
            }
        }

        return isValid;
    }

    private boolean isInRange(Object numberToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.range)) {
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
