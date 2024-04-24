package com.example.server.infrastructure.validation.property;

import com.example.server.infrastructure.constant.EntityProperties;
import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.infrastructure.validation.ValidationSteps;

import java.lang.reflect.Field;

/**
 * @author duchieu212
 */
public class FieldMaxSizeStep implements ValidationSteps {

    @Override
    public void validated(Object record) {
        Class<?> classRecord = record.getClass();
        Field[] fields = classRecord.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(record);
                if ((value instanceof String && !((String) value).isEmpty()) &&
                    ((String) value).length() > EntityProperties.LENGTH_NAME) {
                    throw new RestApiException(field.getName() + " " + Message.SIZE_MAX_INVALID.getMessage());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
