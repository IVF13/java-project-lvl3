package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    @Override
    public StringSchema required() {
        Predicate<Object> isNonEmptyString = x -> x instanceof String && !((String) x).isEmpty();
        addCheck(Checks.REQUIRED, isNonEmptyString);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> isLongEnough = x -> !(x instanceof String) || ((String) x).length() >= length;
        addCheck(Checks.MIN_LENGTH, isLongEnough);
        return this;
    }

    public StringSchema contains(String content) {
        Predicate<Object> isContains = x -> !(x instanceof String) || ((String) x).contains(content);
        addCheck(Checks.CONTAINS, isContains);
        return this;
    }

}
