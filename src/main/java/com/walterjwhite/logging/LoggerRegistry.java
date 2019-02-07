package com.walterjwhite.logging;

import com.google.common.collect.MapMaker;
import com.google.common.util.concurrent.RateLimiter;
import com.walterjwhite.logging.enumeration.LogRate;
import java.lang.reflect.Method;
import java.util.Map;

public class LoggerRegistry {
  private static final Map<Method, RateLimiter> EXECUTION_METHOD_MAP =
      new MapMaker().weakKeys().makeMap();

  public static RateLimiter get(final Method method) {
    if (!EXECUTION_METHOD_MAP.containsKey(method)) {
      final RateLimiter rateLimiter = setup(method);
      if (rateLimiter != null) EXECUTION_METHOD_MAP.put(method, rateLimiter);
    }

    return EXECUTION_METHOD_MAP.get(method);
  }

  private static RateLimiter setup(final Method method) {
    final double logRate = getLogRate(method);
    if (logRate > 0) return RateLimiter.create(logRate);

    return null;
  }

  private static double getLogRate(final Method method) {
    if (method.isAnnotationPresent(com.walterjwhite.logging.annotation.LogRate.class))
      return method.getAnnotation(com.walterjwhite.logging.annotation.LogRate.class).value();

    return LogRate.Default;
  }
}
