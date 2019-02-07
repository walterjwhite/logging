package com.walterjwhite.logging.formatter;

public class ObjectArrayFormatter extends AbstractArgumentFormatter<Object> {
  @Override
  protected String doFormat(Object input) {
    return input.toString();
  }
}
