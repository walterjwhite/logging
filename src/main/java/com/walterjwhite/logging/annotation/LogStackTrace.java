package com.walterjwhite.logging.annotation;

import com.walterjwhite.logging.enumeration.LogLevel;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogStackTrace {
  /**
   * The level to log at.
   *
   * @return the level to log at, by default, error as it was an exception.
   */
  LogLevel level() default LogLevel.ERROR;
}
