package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<Checks, Predicate<Object>> checks = new HashMap<>();

    public abstract BaseSchema required();

    public final boolean isValid(Object objectToValidate) {
        if (!checks.containsKey(Checks.REQUIRED)) {
            if (objectToValidate == null) {
                return true;
            }
        } else if (!checks.get(Checks.REQUIRED).test(objectToValidate)) {
            return false;
        }

        try {
            for (Map.Entry<Checks, Predicate<Object>> check : checks.entrySet()) {
                if (!check.getValue().test(objectToValidate)) {
                    return false;
                }
            }

        } catch (ClassCastException ignored) {
        }

        return true;
    }

    protected final void addCheck(Checks check, Predicate<Object> predicate) {
        checks.put(check, predicate);
    }

}
