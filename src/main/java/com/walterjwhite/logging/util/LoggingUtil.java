package com.walterjwhite.logging.util;

import com.walterjwhite.logging.annotation.LogStackTrace;
import com.walterjwhite.logging.annotation.Loggable;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtil {
  private LoggingUtil() {}

  public static Logger getLogger(JoinPoint joinPoint) {
    return LoggerFactory.getLogger(getLoggerClass(joinPoint));
  }

  private static Class getLoggerClass(JoinPoint joinPoint) {
    if (joinPoint.getTarget() != null) {
      return joinPoint.getTarget().getClass();
    }

    return joinPoint.getSignature().getDeclaringType();
  }

  public static LogLevel getLevel(JoinPoint joinPoint, LogLevel defaultLevel) {
    // override default level
    if (MethodSignature.class
        .cast(joinPoint.getSignature())
        .getMethod()
        .isAnnotationPresent(Loggable.class)) {
      final Loggable loggable =
          MethodSignature.class
              .cast(joinPoint.getSignature())
              .getMethod()
              .getAnnotation(Loggable.class);
      return loggable.value();
    }

    return defaultLevel;
  }

  public static LogLevel getLevelForException(JoinPoint joinPoint, LogLevel defaultLevel) {
    // override default level
    if (MethodSignature.class
        .cast(joinPoint.getSignature())
        .getMethod()
        .isAnnotationPresent(LogStackTrace.class)) {
      final LogStackTrace loggable =
          MethodSignature.class
              .cast(joinPoint.getSignature())
              .getMethod()
              .getAnnotation(LogStackTrace.class);
      return loggable.level();
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
