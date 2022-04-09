package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        Predicate<Object> isMap = x -> x instanceof Map;
        addCheck(Checks.REQUIRED, isMap);
        return this;
    }

    public MapSchema sizeof(int size) throws IllegalArgumentException, NullPointerException {
        Predicate<Object> isMapSizeEqualTo = x -> convertObjectToMap(x).size() == size;
        addCheck(Checks.SIZE_OF, isMapSizeEqualTo);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) throws IllegalArgumentException, NullPointerException {
        Predicate<Object> isMapMatchTheShape = x -> {
            Map<String, Object> map = convertObjectToMap(x);
            for (String key : map.keySet()) {
                if (!schemas.get(key).isValid(map.get(key))) {
                    return false;
                }
            }
            return true;
        };

        addCheck(Checks.SHAPE, isMapMatchTheShape);
        return this;
    }

    private Map<String, Object> convertObjectToMap(Object object) throws IllegalArgumentException, NullPointerException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(object, Map.class);
    }

}
