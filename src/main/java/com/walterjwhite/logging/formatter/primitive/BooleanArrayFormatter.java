package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class BooleanArrayFormatter extends AbstractArgumentFormatter<Boolean> {
  protected String doFormat(Boolean input) {
    return Boolean.toString(input);
  }
}
