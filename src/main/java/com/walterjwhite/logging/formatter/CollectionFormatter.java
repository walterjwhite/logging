package com.walterjwhite.logging.formatter;

import com.walterjwhite.logging.util.ArgumentUtil;
import java.util.Collection;
import java.util.Iterator;

public class CollectionFormatter implements ArgumentFormatter<Collection> {
  @Override
  public Object format(
      Collection argument, final int numberOfArguments, final boolean isSensitive) {
    final int length = getLength(argument, numberOfArguments);
    final String[] out = new String[length];
    final Iterator<Object> iterator = argument.iterator();

    for (int i = 0; i < length; i++) {
      out[i] = ArgumentUtil.format(iterator.next(), isSensitive);
    }

    return ArgumentUtil.formatOutput(out, hasMore(iterator));
  }

  protected int getLength(final Collection argument, final int numberOfArguments) {
    if (argument == null || argument.isEmpty()) return 0;

    if (argument.size() > numberOfArguments) return numberOfArguments;

    return argument.size();
  }

  protected boolean hasMore(final Iterator<Object> iterator) {
    return iterator.hasNext();
  }
}
