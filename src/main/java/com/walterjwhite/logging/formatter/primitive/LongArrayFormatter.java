package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class LongArrayFormatter extends AbstractArgumentFormatter<Long> {
  protected String doFormat(Long input) {
    return Long.toString(input);
  }
}
