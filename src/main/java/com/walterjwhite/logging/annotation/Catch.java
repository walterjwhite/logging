// package com.walterjwhite.logging.annotation;
//
// import com.walterjwhite.logging.enumeration.LogLevel;
// import com.walterjwhite.logging.util.ArgumentUtil;
//
// import java.lang.annotation.*;
//
// @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
// @Retention(RetentionPolicy.RUNTIME)
// @Documented
// public @interface Catch {
//  Class<? extends Exception>[] value();
//
//  LogLevel level() default LogLevel.ERROR;
// }
