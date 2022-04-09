package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<Checks, Predicate<Object>> checkList = new HashMap<>();

    public final boolean isValid(Object objectToValidate) {

        try {

            for (Map.Entry<Checks, Predicate<Object>> check : checkList.entrySet()) {
                if (!check.getValue().test(objectToValidate)) {
                    return false;
                }
            }

        } catch (ClassCastException | IllegalArgumentException | NullPointerException ignored) {
        }

        return true;
    }

    protected final void addCheck(Checks check, Predicate<Object> predicate) {
        checkList.put(check, predicate);
    }


}
