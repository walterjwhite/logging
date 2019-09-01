package com.walterjwhite.logging.formatter;

import com.walterjwhite.logging.util.ArgumentUtil;
import java.util.Iterator;
import java.util.Map;

public class MapFormatter implements ArgumentFormatter<Map> {
  @Override
  public Object format(Map argument, final int numberOfArguments, final boolean isSensitive) {
    final int length = getLength(argument, numberOfArguments);
    final String[] out = new String[length];
    final Iterator<Map.Entry> iterator = argument.entrySet().iterator();

    for (int i = 0; i < length; i++) {
      out[i] = ArgumentUtil.format(iterator.next(), isSensitive);
    }

    return ArgumentUtil.formatOutput(out, hasMore(iterator));
  }

  protected int getLength(final Map argument, final int numberOfArguments) {
    if (argument == null || argument.isEmpty()) return 0;

    if (argument.size() > numberOfArguments) return numberOfArguments;

    return argument.size();
  }

  protected boolean hasMore(final Iterator<Map.Entry> iterator) {
    return iterator.hasNext();
  }
}
