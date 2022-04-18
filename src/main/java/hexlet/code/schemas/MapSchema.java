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

    public MapSchema sizeof(int size) {
        Predicate<Object> isMapSizeEqualTo = x -> !(x instanceof Map) || ((Map<String, Object>) x).size() == size;
        addCheck(Checks.SIZE_OF, isMapSizeEqualTo);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> isMapMatchTheShape = x -> !(x instanceof Map)
                || ((Map<?, ?>) x).entrySet()
                .stream()
                .allMatch(value -> schemas.get(value.getKey()).isValid(value.getValue()));

        addCheck(Checks.SHAPE, isMapMatchTheShape);
        return this;
    }

}
