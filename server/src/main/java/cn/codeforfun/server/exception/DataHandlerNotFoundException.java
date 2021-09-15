package cn.codeforfun.server.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class DataHandlerNotFoundException extends RuntimeException {

    private String message;

    public DataHandlerNotFoundException() {
        super();
    }

    public DataHandlerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
