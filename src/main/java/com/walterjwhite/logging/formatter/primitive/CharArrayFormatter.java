package com.walterjwhite.logging.formatter.primitive;

import com.walterjwhite.logging.formatter.AbstractArgumentFormatter;

public class CharArrayFormatter extends AbstractArgumentFormatter<Character> {
  protected String doFormat(Character input) {
    return Character.toString(input);
  }
}
