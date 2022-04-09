package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema required() throws ClassCastException, NullPointerException {
        Predicate<Object> isNonEmptyString = x -> x instanceof String && !((String) x).isEmpty();
        addCheck(Checks.REQUIRED, isNonEmptyString);
        return this;
    }

    public StringSchema minLength(int length) throws ClassCastException, NullPointerException {
        Predicate<Object> isLongEnough = x -> ((String) x).length() >= length;
        addCheck(Checks.MIN_LENGTH, isLongEnough);
        return this;
    }

    public StringSchema contains(String content) throws ClassCastException, NullPointerException {
        Predicate<Object> isContains = x -> ((String) x).contains(content);
        addCheck(Checks.CONTAINS, isContains);
        return this;
    }

}
