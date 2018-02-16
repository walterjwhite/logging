package com.walterjwhite.logging.util;

import com.walterjwhite.logging.annotation.Loggable;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtil {
  private LoggingUtil() {}

  public static Logger getLogger(ProceedingJoinPoint proceedingJoinPoint) {
    return LoggerFactory.getLogger(getLoggerClass(proceedingJoinPoint));
  }

  private static Class getLoggerClass(ProceedingJoinPoint proceedingJoinPoint) {
    if (proceedingJoinPoint.getTarget() != null) {
      return proceedingJoinPoint.getTarget().getClass();
    }

    return proceedingJoinPoint.getSignature().getDeclaringType();
  }

  public static LogLevel getLevel(ProceedingJoinPoint proceedingJoinPoint, LogLevel defaultLevel) {
    // override default level
    if (MethodSignature.class
        .cast(proceedingJoinPoint.getSignature())
        .getMethod()
        .isAnnotationPresent(Loggable.class)) {
      final Loggable loggable =
          MethodSignature.class
              .cast(proceedingJoinPoint.getSignature())
              .getMethod()
              .getAnnotation(Loggable.class);
      return loggable.value();
    }

    return defaultLevel;
  }

  public static final int getNumberOfArguments(
      ProceedingJoinPoint proceedingJoinPoint, int defaultNumberOfArguments) {
    // override default level
    if (MethodSignature.class
        .cast(proceedingJoinPoint.getSignature())
        .getMethod()
        .isAnnotationPresent(Loggable.class)) {
      final Loggable loggable =
          MethodSignature.class
              .cast(proceedingJoinPoint.getSignature())
              .getMethod()
              .getAnnotation(Loggable.class);
      return loggable.numberOfArguments();
    }

    return defaultNumberOfArguments;
  }
}
