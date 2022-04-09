package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<Checks, Predicate<Object>> checkList = new HashMap<>();

    public final boolean isValid(Object objectToValidate) {
        if (!checkList.containsKey(Checks.REQUIRED)) {
            if (objectToValidate == null) {
                return true;
            }
        } else if (!checkList.get(Checks.REQUIRED).test(objectToValidate)) {
            return false;
        }

        return toRunOtherChecks(objectToValidate);
    }

    protected final void addCheck(Checks check, Predicate<Object> predicate) {
        checkList.put(check, predicate);
    }

    private boolean toRunOtherChecks(Object objectToValidate) {
        try {
            for (Map.Entry<Checks, Predicate<Object>> check : checkList.entrySet()) {
                if (!check.getValue().test(objectToValidate)) {
                    return false;
                }
            }

        } catch (ClassCastException | IllegalArgumentException ignored) {
        }

        return true;
    }

}
