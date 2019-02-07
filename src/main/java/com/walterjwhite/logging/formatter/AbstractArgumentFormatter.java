package com.walterjwhite.logging.formatter;

import com.walterjwhite.logging.util.ArgumentUtil;
import com.walterjwhite.logging.util.SensitiveUtil;

public abstract class AbstractArgumentFormatter<ArgumentType>
    implements ArgumentFormatter<ArgumentType[]> {

  public Object format(
      ArgumentType[] argument, final int numberOfArguments, final boolean isSensitive) {
    final int length = ArgumentUtil.getArgumentLength(argument, numberOfArguments);
    final String[] out = new String[length];

    for (int i = 0; i < length; i++) {
      out[i] = format(argument[i], isSensitive);
    }

    return ArgumentUtil.formatOutput(out, hasMore(argument, numberOfArguments));
  }

  protected String format(final ArgumentType argument, final boolean isSensitive) {
    if (argument == null) return "null";

    return SensitiveUtil.format(doFormat(argument), isSensitive);
  }

  protected abstract String doFormat(ArgumentType input);

  protected boolean hasMore(ArgumentType[] argument, final int numberOfArguments) {
    if (argument == null) return false;

    return argument.length > numberOfArguments;
  }
}
