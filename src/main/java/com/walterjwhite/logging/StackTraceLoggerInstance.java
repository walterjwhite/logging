package com.walterjwhite.logging;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.LoggingUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

public class StackTraceLoggerInstance {
  public static final String EXCEPTION_TEMPLATE = "{}\n{}";

  protected final JoinPoint joinPoint;

  protected final Logger logger;
  protected final LogLevel level;
  protected final Throwable throwable;

  public StackTraceLoggerInstance(JoinPoint joinPoint) {

    this.joinPoint = joinPoint;
    this.throwable = (Throwable) joinPoint.getArgs()[0];
    this.level = LoggingUtil.getLevelForException(joinPoint, LogLevel.ERROR);
    this.logger = LoggingUtil.getLogger(joinPoint);
  }

  public void logException() {
    level.log(logger, EXCEPTION_TEMPLATE, getMethodName(), getStackTrace(throwable));
  }

  protected String getMethodName() {
    return MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
  }

  private static String getStackTrace(final Throwable throwable) {
    final StringBuilder buffer = new StringBuilder();

    buffer.append(throwable.getMessage());
    buffer.append("\n");
    for (final StackTraceElement stackTraceElement : throwable.getStackTrace()) {
      buffer.append("\t" + stackTraceElement.toString() + "\n");
    }

    return buffer.toString();
  }
}
