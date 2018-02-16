package com.walterjwhite.logging;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.ArgumentUtil;
import com.walterjwhite.logging.util.LoggingUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

public class LoggerInstance {

  public static final String CALL_TEMPLATE = "{}({})";
  public static final String RESULT_TEMPLATE = "{}({}) => {} in {}ms";

  protected final ProceedingJoinPoint proceedingJoinPoint;
  protected final Logger logger;
  protected final LogLevel level;

  protected final String callTemplate;
  protected final String resultTemplate;
  protected final int numberOfArguments;
  protected final long start = System.currentTimeMillis();

  public LoggerInstance(ProceedingJoinPoint proceedingJoinPoint, LogLevel defaultLevel) {
    this.proceedingJoinPoint = proceedingJoinPoint;

    this.numberOfArguments =
        LoggingUtil.getNumberOfArguments(
            proceedingJoinPoint, ArgumentUtil.NUMBER_OF_ARGUMENTS_TO_LOG);
    this.level = LoggingUtil.getLevel(proceedingJoinPoint, defaultLevel);
    this.logger = LoggingUtil.getLogger(proceedingJoinPoint);

    this.callTemplate = CALL_TEMPLATE;
    this.resultTemplate = RESULT_TEMPLATE;
  }

  public Object doAround() throws Throwable {
    logCall();

    try {
      Object result = proceedingJoinPoint.proceed();
      logResult(result);

      return result;
    } catch (Exception e) {
      logger.error("unexpected exception", e);
      throw (e);
    }
  }

  /** Logs the call before the actual invocation. */
  protected void logCall() {
    level.log(
        logger,
        callTemplate,
        getMethodName(),
        ArgumentUtil.getArguments(proceedingJoinPoint.getArgs(), numberOfArguments));
  }

  /** Logs the result of the actual invocation (including runtime). */
  protected void logResult(final Object result) {
    level.log(
        logger,
        resultTemplate,
        getMethodName(),
        ArgumentUtil.getArguments(proceedingJoinPoint.getArgs(), numberOfArguments),
        /*ArgumentUtil.getArgument(result, numberOfArguments),*/
        result,
        getRuntime());
  }

  protected long getRuntime() {
    return System.currentTimeMillis() - start;
  }

  protected String getMethodName() {
    return MethodSignature.class.cast(proceedingJoinPoint.getSignature()).getMethod().getName();
  }
}
