package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.StackTraceLoggerInstance;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/** Logs the exception right when it is thrown. */
@Aspect
public class AfterThrowingAspect {
  // log all private invocations at trace level, exclude (this framework)
  @AfterThrowing(
      value = "(execution(* *.*(..)) && !within(com.walterjwhite.logging..*))",
      throwing = "throwable")
  public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
    new StackTraceLoggerInstance(joinPoint, throwable).logException();
  }
}
