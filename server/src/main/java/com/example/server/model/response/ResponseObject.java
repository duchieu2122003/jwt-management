package com.example.server.model.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseObject {

    boolean isSusses = false;

    Object data;

    String message;

    public <T> ResponseObject(T obj) {
        processResponse(obj);
    }

    public void processResponse(Object obj) {
        if (obj != null) {
            this.isSusses = true;
            this.data = obj;
        }
    }
}
