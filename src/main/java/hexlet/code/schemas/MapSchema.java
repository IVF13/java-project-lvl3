package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    @Override
    public MapSchema required() {
        Predicate<Object> isMap = x -> x instanceof Map;
        addCheck(Checks.REQUIRED, isMap);
        return this;
    }

    public MapSchema sizeof(int size) throws ClassCastException {
        Predicate<Object> isMapSizeEqualTo = x -> ((Map<String, Object>) x).size() == size;
        addCheck(Checks.SIZE_OF, isMapSizeEqualTo);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) throws ClassCastException {
        Predicate<Object> isMapMatchTheShape = x -> {
            for (String key : ((Map<String, Object>) x).keySet()) {
                if (!schemas.get(key).isValid(((Map<String, Object>) x).get(key))) {
                    return false;
                }
            }
            return true;
        };

        addCheck(Checks.SHAPE, isMapMatchTheShape);
        return this;
    }

}
