package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class DoubleArrayFormatter extends AbstractArgumentFormatter<Double> {
  protected String doFormat(Double input) {
    return Double.toString(input);
  }
}
