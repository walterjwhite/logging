package com.walterjwhite.logging;

import com.walterjwhite.logging.util.ArgumentUtil;
import java.util.Collection;
import java.util.Iterator;

public enum ArgumentType {
  CollectionArgument(Collection.class) {
    @Override
    public Object format(Object input, int numberOfArguments) {
      final String[] out = new String[numberOfArguments];
      final Iterator<Object> iterator = ((Collection) input).iterator();

      int i = 0;
      for (; i < numberOfArguments && iterator.hasNext(); i++) {
        out[i] = iterator.next().toString();
      }

      return ArgumentUtil.formatOutput(
          ArgumentUtil.trim(out, i, numberOfArguments), iterator.hasNext());
    }
  },
  ObjectArrayArgument(Object[].class) {
    @Override
    public Object format(Object input, int numberOfArguments) {
      final Object[] inputArray = (Object[]) input;

      String[] out = new String[numberOfArguments];

      for (int i = 0; i < numberOfArguments && i < inputArray.length; i++) {
        out[i] = inputArray[i].toString();
      }

      return ArgumentUtil.formatOutput(out, inputArray.length > numberOfArguments);
    }
  },
  ObjectArgument(Object.class) {
    @Override
    public Object format(Object input, int numberOfArguments) {
      return input;
    }
  };

  private final Class argumentType;

  public Class getArgumentType() {
    return argumentType;
  }

  ArgumentType(Class argumentType) {
    this.argumentType = argumentType;
  }

  public abstract Object format(final Object input, final int numberOfArguments);
}
