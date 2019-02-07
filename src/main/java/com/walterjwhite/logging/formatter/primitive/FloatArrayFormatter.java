package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class FloatArrayFormatter extends AbstractArgumentFormatter<Float> {
  protected String doFormat(Float input) {
    return Float.toString(input);
  }
}
