package com.epam.javase.t07;

import java.lang.annotation.*;

/**
 * Created by aivanov on 2/28/2017.
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassDefenition {
    String author();
    String created();
    int version();
    String modified() default "N/A";
}

