package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class ByteArrayFormatter extends AbstractArgumentFormatter<Byte> {
  protected String doFormat(Byte input) {
    return Byte.toString(input);
  }
}
