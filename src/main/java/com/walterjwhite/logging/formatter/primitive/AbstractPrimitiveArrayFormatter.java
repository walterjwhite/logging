package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.ArgumentFormatter;
import com.walterjwhite.logging.util.ArgumentUtil;
import com.walterjwhite.logging.util.SensitiveUtil;

public abstract class AbstractPrimitiveArrayFormatter<PrimitiveType>
    implements ArgumentFormatter<PrimitiveType[]> {
  @Override
  public Object format(PrimitiveType[] argument, int numberOfArguments, boolean isSensitive) {
    final int length = getLength(argument, numberOfArguments);
    final String[] out = new String[length];

    for (int i = 0; i < out.length; i++) {
      out[i] = SensitiveUtil.format(doFormat(argument[i]), isSensitive);
    }

    return ArgumentUtil.formatOutput(out, hasMore(argument, numberOfArguments));
  }

  protected int getLength(PrimitiveType[] argument, final int numberOfArguments) {
    if (numberOfArguments > argument.length) return argument.length;

    return numberOfArguments;
  }

  protected abstract String doFormat(PrimitiveType input);

  protected boolean hasMore(PrimitiveType[] argument, final int numberOfArguments) {
    if (argument == null) return false;

    return argument.length > numberOfArguments;
  }
}
