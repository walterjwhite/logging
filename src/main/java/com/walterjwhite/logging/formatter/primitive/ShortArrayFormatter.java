package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class ShortArrayFormatter extends AbstractArgumentFormatter<Short> {
  protected String doFormat(Short input) {
    return Short.toString(input);
  }
}
