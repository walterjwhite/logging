package com.walterjwhite.logging.util;

public class SensitiveUtil {
  // TODO: do not hard-code this and do not depend on external libraries
  public static final int OUTPUT_LENGTH = 80;

  public static String format(final String input, final boolean isSensitive) {
    if (input == null || input.length() == 0) return input;
    /*
    int end = OUTPUT_LENGTH;
    if (end > input.length()) end = input.length();

    if (!isSensitive) return input.substring(0, end);
    */
    if (!isSensitive) return input;

    // TODO: do not hard-code this and do not depend on external libraries
    return input.substring(input.length() - 4);
  }
}
