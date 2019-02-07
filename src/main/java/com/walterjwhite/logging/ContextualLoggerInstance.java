package com.walterjwhite.logging;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.ArgumentUtil;
import com.walterjwhite.logging.util.ContextualDataUtil;
import org.aspectj.lang.ProceedingJoinPoint;

public class ContextualLoggerInstance extends LoggerInstance {
  protected final Object[] contextualData;
  protected final String contextualTemplate;
  protected final Object target;
  protected final Class targetClass;

  public ContextualLoggerInstance(ProceedingJoinPoint proceedingJoinPoint, LogLevel defaultLevel) {
    super(proceedingJoinPoint, defaultLevel);

    if (proceedingJoinPoint.getTarget() != null) {
      target = proceedingJoinPoint.getTarget();
      targetClass = target.getClass();
    } else {
      target = null;
      targetClass = proceedingJoinPoint.getSignature().getDeclaringType();
    }

    this.contextualData = ContextualDataUtil.getContextual(target, targetClass);
    this.contextualTemplate = " - " + ContextualDataUtil.getContextualTemplate(contextualData);
  }

  protected void logCall() {

    level.log(
        logger,
        callTemplate + contextualTemplate,
        getMethodName(),
        ArgumentUtil.getArguments(isSensitive, proceedingJoinPoint.getArgs(), numberOfArguments),
        contextualData);
  }

  protected void logResult(final Object result) {
    level.log(
        logger,
        resultTemplate + contextualTemplate,
        getMethodName(),
        ArgumentUtil.getArguments(isSensitive, proceedingJoinPoint.getArgs(), numberOfArguments),
        ArgumentUtil.getArgument(isSensitive, result, numberOfArguments),
        getRuntime(),
        contextualData);
  }
}
