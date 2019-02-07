package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.ContextualLoggerInstance;
import com.walterjwhite.logging.LoggerInstance;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/** Logs the matching invocations at Debug. */
@Aspect
public class DebugMethodLoggerAspect {
  // log all protected invocations at debug level, exclude (this framework, constructors)
  @Around(
      "execution(protected * *(..)) && !within(com.walterjwhite.logging..*) && !call(*.new(..)) && !within(@ContextualLoggable *) && !within(@NonLoggable *) && !within(*..*$AjcClosure*) && !execution(* *lambda$*(..))")
  public Object doProtectedAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.DEBUG).doAround());
  }

  @Around(
      "execution(protected * *(..)) && !within(com.walterjwhite.logging..*) && !call(*.new(..)) && within(@ContextualLoggable *) && !within(@NonLoggable *) && !within(*..*$AjcClosure*) && !execution(* *lambda$*(..))")
  public Object doContextualProtectedAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.DEBUG).doAround());
  }
}
