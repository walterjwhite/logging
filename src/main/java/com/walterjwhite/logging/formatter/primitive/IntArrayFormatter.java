package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class IntArrayFormatter extends AbstractArgumentFormatter<Integer> {
  protected String doFormat(Integer input) {
    return Integer.toString(input);
  }
}
