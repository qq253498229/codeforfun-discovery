package cn.codeforfun.client.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ServiceNameNotFoundException extends RuntimeException {
    private String message;

    public ServiceNameNotFoundException() {
        super();
    }

    public ServiceNameNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
