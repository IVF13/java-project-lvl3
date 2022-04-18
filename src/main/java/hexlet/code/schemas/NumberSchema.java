package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    @Override
    public NumberSchema required() {
        Predicate<Object> isNumber = x -> x instanceof Integer;
        addCheck(Checks.REQUIRED, isNumber);
        return this;
    }

    public NumberSchema positive() throws ClassCastException {
        Predicate<Object> isPositive = x -> !(x instanceof Integer) || ((Integer) x) > 0;
        addCheck(Checks.POSITIVE, isPositive);
        return this;
    }

    public NumberSchema range(int lowerThreshold, int upperThreshold) throws ClassCastException {
        Predicate<Object> isInRange = x -> !(x instanceof Integer)
                || (Integer) x >= lowerThreshold && (Integer) x <= upperThreshold;
        addCheck(Checks.RANGE, isInRange);
        return this;
    }

}
