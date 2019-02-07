package com.walterjwhite.logging;

import com.google.common.util.concurrent.RateLimiter;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.ArgumentUtil;
import com.walterjwhite.logging.util.LoggingUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

public class LoggerInstance {

  public static final String VOID_TEMPLATE = "VOID";

  public static final String CALL_TEMPLATE = "{}({})";
  public static final String RESULT_TEMPLATE = "{}({}) => {} in {}ms";

  protected final ProceedingJoinPoint proceedingJoinPoint;
  protected final Logger logger;
  protected final LogLevel level;

  protected final String callTemplate;
  protected final String resultTemplate;
  protected final int numberOfArguments;
  protected final long start = System.nanoTime();
  protected final RateLimiter rateLimiter;

  protected final boolean isVoidReturn;
  protected final boolean isVoidArgument;
  protected final boolean isSensitive;

  protected transient Object argument;

  public LoggerInstance(ProceedingJoinPoint proceedingJoinPoint, LogLevel defaultLevel) {
    this.proceedingJoinPoint = proceedingJoinPoint;

    this.numberOfArguments =
        LoggingUtil.getNumberOfArguments(
            proceedingJoinPoint, ArgumentUtil.NUMBER_OF_ARGUMENTS_TO_LOG);
    this.level = LoggingUtil.getLevel(proceedingJoinPoint, defaultLevel);
    this.logger = LoggingUtil.getLogger(proceedingJoinPoint);

    this.callTemplate = CALL_TEMPLATE;
    this.resultTemplate = RESULT_TEMPLATE;
    this.rateLimiter =
        LoggerRegistry.get(
            MethodSignature.class.cast(proceedingJoinPoint.getSignature()).getMethod());

    this.isVoidArgument =
        MethodSignature.class
                .cast(proceedingJoinPoint.getSignature())
                .getMethod()
                .getParameterCount()
            == 0;
    this.isVoidReturn =
        Void.TYPE.equals(
            MethodSignature.class
                .cast(proceedingJoinPoint.getSignature())
                .getMethod()
                .getReturnType());
    this.isSensitive =
        MethodSignature.class
            .cast(proceedingJoinPoint.getSignature())
            .getMethod()
            .isAnnotationPresent(Sensitive.class);
  }

  public Object doAround() throws Throwable {
    // bypass if we have exceeded our logging rate
    if (isRateLimited()) return proceedingJoinPoint.proceed();

    logCall();

    try {
      Object result = proceedingJoinPoint.proceed();
      logResult(result);

      return result;
    } catch (Throwable t) {
      logger.error("unexpected exception", t);
      throw (t);
    }
  }

  protected boolean isRateLimited() {
    if (rateLimiter == null) return false;

    return !rateLimiter.tryAcquire();
  }

  /** Logs the call before the actual invocation. */
  protected void logCall() {
    if (isVoidArgument) argument = VOID_TEMPLATE;
    else
      argument =
          ArgumentUtil.getArguments(isSensitive, proceedingJoinPoint.getArgs(), numberOfArguments);

    level.log(logger, callTemplate, getMethodName(), argument);
  }

  /** Logs the result of the actual invocation (including runtime). */
  protected void logResult(final Object result) {
    final Object formattedResult;
    if (isVoidReturn) formattedResult = VOID_TEMPLATE;
    else formattedResult = ArgumentUtil.getArgument(isSensitive, result, numberOfArguments);

    level.log(logger, resultTemplate, getMethodName(), argument, formattedResult, getRuntime());
  }

  protected long getRuntime() {
    return Math.round((System.nanoTime() - start) / 1000000.0);
  }

  protected String getMethodName() {
    return MethodSignature.class.cast(proceedingJoinPoint.getSignature()).getMethod().getName();
  }
}
