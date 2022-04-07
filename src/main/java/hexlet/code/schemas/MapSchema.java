package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> schemasStorage;
    private int sizeOf;

    public MapSchema required() {
        checkList.add(SchemasChecks.REQUIRED);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeOf = size;
        checkList.add(SchemasChecks.SIZE_OF);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.schemasStorage = schemas;
        checkList.add(SchemasChecks.SHAPE);
        return this;
    }

    @Override
    public boolean isRequired(Object mapToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.REQUIRED) && !(mapToValidate instanceof Map<?, ?>)) {
            return false;
        }
        return isValid;
    }

    @Override
    public boolean isPassesOtherTests(Object mapToValidate, boolean isValid) {
        isValid = isSizeOf(mapToValidate, isValid);

        isValid = isMatchTheShape(mapToValidate, isValid);

        return isValid;
    }

    private boolean isSizeOf(Object mapToValidate, boolean isValid) {
        Map<String, Object> map = convertObjectToMap(mapToValidate);

        if (checkList.contains(SchemasChecks.SIZE_OF) && map != null) {
            if (map.size() != this.sizeOf) {
                return false;
            }
        }
        return isValid;
    }

    private boolean isMatchTheShape(Object mapToValidate, boolean isValid) {
        Map<String, Object> map = convertObjectToMap(mapToValidate);

        if (checkList.contains(SchemasChecks.SHAPE) && map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                if (schemasStorage.containsKey(key) && !schemasStorage.get(key).isValid(value)) {
                    return false;
                }

            }
        }
        return isValid;
    }

    private Map<String, Object> convertObjectToMap(Object object) {
        if (object instanceof Map) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(object, Map.class);
        } else {
            return null;
        }
    }

}
