package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.ContextualLoggerInstance;
import com.walterjwhite.logging.LoggerInstance;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/** Logs the matching invocations at TRACE. */
@Aspect
public class TraceMethodLoggerAspect {
  // log all private invocations at trace level, exclude (this framework)
  @Around(
      "execution(private * *(..)) && !within(com.walterjwhite.logging..*) && !within(@ContextualLoggable *) && !within(@NonLoggable *)")
  public Object doPrivateAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.TRACE).doAround());
  }

  @Around(
      "execution(private * *(..)) && !within(com.walterjwhite.logging..*) && !within(@NonLoggable *) && within(@ContextualLoggable *)")
  public Object doContextualPrivateAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.TRACE).doAround());
  }

  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) && call(*.new(..))&& !within(@ContextualLoggable *) && !within(@NonLoggable *) && (execution(void set*(..)) || execution(!void get*()) && !execution(boolean is*()))")
  public Object doPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.TRACE).doAround());
  }

  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) && call(*.new(..)) && within(@ContextualLoggable *) && !within(@NonLoggable *) && (execution(void set*(..)) || execution(!void get*()) && !execution(boolean is*()))")
  public Object doContextualPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.TRACE).doAround());
  }
}
