package com.walterjwhite.logging;

import com.walterjwhite.logging.formatter.*;
import com.walterjwhite.logging.formatter.primitive.*;
import java.util.Collection;
import java.util.Map;

public enum ArgumentType {
  ByteArrayArgument(ByteArrayFormatter.class, byte[].class),
  IntArrayArgument(IntArrayFormatter.class, int[].class),
  DoubleArrayArgument(DoubleArrayFormatter.class, double[].class),
  FloatArrayArgument(FloatArrayFormatter.class, float[].class),
  CharArrayArgument(CharArrayFormatter.class, char[].class),
  LongArrayArgument(LongArrayFormatter.class, long[].class),
  ShortArrayArgument(ShortArrayFormatter.class, short[].class),
  BooleanArrayArgument(BooleanArrayFormatter.class, boolean[].class),
  CollectionArgument(CollectionFormatter.class, Collection.class),
  MapArgument(MapFormatter.class, Map.class),
  ObjectArrayArgument(ObjectArrayFormatter.class, Object[].class),
  ObjectArgument(ObjectFormatter.class, Object.class);

  private final Class[] argumentTypes;
  private final Class<? extends ArgumentFormatter> argumentFormatterClass;

  public boolean supports(final Class argumentClass) {
    for (final Class argumentType : argumentTypes)
      if (argumentType.isAssignableFrom(argumentClass)) return true;

    return false;
  }

  ArgumentType(
      final Class<? extends ArgumentFormatter> argumentFormatterClass,
      final Class... argumentTypes) {
    this.argumentTypes = argumentTypes;
    this.argumentFormatterClass = argumentFormatterClass;
  }

  public Object format(final boolean isSensitive, final Object input, final int numberOfArguments) {
    try {
      final ArgumentFormatter argumentFormatter = argumentFormatterClass.newInstance();

      return argumentFormatter.format(input, numberOfArguments, isSensitive);
    } catch (InstantiationException | IllegalAccessException e) {
      throw new FormatterConfigurationException(e);
    }
  }
}
