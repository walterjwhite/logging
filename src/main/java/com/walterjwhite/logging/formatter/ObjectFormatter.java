package com.walterjwhite.logging.formatter;

import com.walterjwhite.logging.util.ArgumentUtil;

public class ObjectFormatter implements ArgumentFormatter<Object> {

  @Override
  public Object format(
      final Object argument, final int numberOfArguments, final boolean isSensitive) {
    return ArgumentUtil.format(argument, isSensitive);
  }
}
