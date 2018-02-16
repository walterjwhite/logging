package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.ContextualLoggerInstance;
import com.walterjwhite.logging.LoggerInstance;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/** Logs the matching invocations at INFO. */
@Aspect
public class InfoMethodLoggerAspect {
  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) && !call(*.new(..)) && !within(@ContextualLoggable *) && !within(@NonLoggable *) && !execution(void set*(..)) && !execution(!void get*()) && !execution(boolean is*())")
  public Object doPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.INFO).doAround());
  }

  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) && !call(*.new(..)) && within(@ContextualLoggable *) && !within(@NonLoggable *) && !execution(void set*(..)) && !execution(!void get*()) && !execution(boolean is*())")
  public Object doContextualPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.INFO).doAround());
  }
}
