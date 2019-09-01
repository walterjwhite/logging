package com.walterjwhite.logging.util;

import com.walterjwhite.logging.annotation.ContextualLoggable;
import com.walterjwhite.logging.annotation.ContextualLoggableField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextualDataUtil {
  public static Object[] getContextual(final Object target, final Class targetClass) {
    if (target != null) {
      final ContextualLoggable contextualLoggable =
          (ContextualLoggable) targetClass.getAnnotation(ContextualLoggable.class);

      if (contextualLoggable != null) {
        return getContextual(target, targetClass, contextualLoggable);
      }
    }

    return null;
  }

  /**
   * Helper for getting the contextual information. TODO: make this recursive, by default, try 3
   * levels.
   */
  private static Object[] getContextual(
      final Object object, final Class targetClass, final ContextualLoggable contextualLoggable) {
    final List<Object> data = new ArrayList<>();

    for (final Field field : targetClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(ContextualLoggableField.class)) {
        try {
          final boolean wasAccessible = field.isAccessible();
          field.setAccessible(true);
          final Object value = field.get(object);

          if (value != null) {
            data.add(value);
          }

          if (!wasAccessible) {
            field.setAccessible(wasAccessible);
          }
        } catch (Exception e) {
          // pass
        }
      }
    }

    return (data.toArray(new Object[data.size()]));
  }

  public static String getContextualTemplate(final Object[] data) {
    final StringBuilder buffer = new StringBuilder();
    for (int i = 0; i < data.length; i++) {
      buffer.append("{}");

      if (i + 1 < data.length) {
        buffer.append(".");
      }
    }

    return (buffer.toString());
  }
}
