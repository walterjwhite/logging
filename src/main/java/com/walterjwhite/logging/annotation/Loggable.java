package com.walterjwhite.logging.annotation;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.ArgumentUtil;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {
  /**
   * The level to log at.
   *
   * @return the level to log at, by default, trace.
   */
  LogLevel value() default LogLevel.TRACE;

  /**
   * By default, only support the first 3 arguments + any contextual fields.
   *
   * @return the number of arguments printed
   */
  int numberOfArguments() default ArgumentUtil.NUMBER_OF_ARGUMENTS_TO_LOG;
}
