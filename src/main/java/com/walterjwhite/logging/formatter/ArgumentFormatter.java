package com.walterjwhite.logging.formatter;

public interface ArgumentFormatter<ArgumentType> {
  Object format(ArgumentType argument, final int numberOfArguments, final boolean isSensitive);
}
