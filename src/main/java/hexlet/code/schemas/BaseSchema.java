package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<Checks, Predicate<Object>> checks = new HashMap<>();

    public abstract BaseSchema required();

    public final boolean isValid(Object objectToValidate) {
        try {
            return toRunChecks(objectToValidate);
        } catch (ClassCastException | NullPointerException ignored) {
            if (checks.containsKey(Checks.REQUIRED)) {
                return false;
            }
        }

        return true;
    }

    protected final void addCheck(Checks check, Predicate<Object> predicate) {
        checks.put(check, predicate);
    }

    private boolean toRunChecks(Object objectToValidate) throws NullPointerException, ClassCastException {
        for (Map.Entry<Checks, Predicate<Object>> check : checks.entrySet()) {
            if (!check.getValue().test(objectToValidate)) {
                return false;
            }
        }

        return true;
    }

}
