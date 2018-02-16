package com.walterjwhite.logging.util;

import com.walterjwhite.logging.ArgumentType;
import java.util.Arrays;

public class ArgumentUtil {
  public static final int NUMBER_OF_ARGUMENTS_TO_LOG = 3;

  private ArgumentUtil() {}

  public static Object getArgument(Object argument, final int numberOfArguments) {
    if (argument == null) return null;

    for (final ArgumentType argumentType : ArgumentType.values()) {
      if (argumentType.getArgumentType().isAssignableFrom(argument.getClass())) {
        return argumentType.format(argument, numberOfArguments);
      }
    }

    throw (new IllegalArgumentException(argument.getClass() + " is not supported"));
  }

  public static Object[] getArguments(
      final Object[] arguments, final Object[] contextual, int numberOfArguments) {
    int argumentLength = getArgumentLength(arguments, numberOfArguments);
    final Object[] data = Arrays.copyOf(arguments, argumentLength + contextual.length);
    for (int i = 0; i < argumentLength; i++) data[i] = getArgument(arguments[i], numberOfArguments);

    for (int i = 0; i < contextual.length; i++) {
      data[argumentLength + i] = contextual[i];
    }

    return (data);
  }

  public static Object[] getArguments(final Object[] arguments, int numberOfArguments) {
    if (arguments == null || arguments.length == 0) return null;

    int argumentLength = getArgumentLength(arguments, numberOfArguments);

    final Object[] data = new Object[argumentLength];
    for (int i = 0; i < argumentLength; i++) data[i] = getArgument(arguments[i], numberOfArguments);

    return (data);
  }

  public static int getArgumentLength(final Object[] arguments, int numberOfArguments) {
    if (arguments == null || arguments.length == 0) return 0;

    if (arguments.length > numberOfArguments) return numberOfArguments;

    return arguments.length;
  }

  public static String[] trim(final String[] in, final int lastIndex, final int numberOfArguments) {
    if (lastIndex < numberOfArguments - 1) return Arrays.copyOf(in, lastIndex + 1);

    return in;
  }

  public static Object formatOutput(final String[] arguments, final boolean hasMore) {
    if (hasMore) {
      return "first " + arguments.length + "(" + String.join(",", arguments) + ")";
    }

    return "(" + String.join(",", arguments) + ")";
  }
}
