package cn.codeforfun.client.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceClient {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";
}
