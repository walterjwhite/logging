package com.walterjwhite.logging.formatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectArrayFormatterTest {
  protected final ObjectArrayFormatter objectArrayFormatter = new ObjectArrayFormatter();

  @Test
  public void test3Items() {
    final Object[] input = new Object[] {"one", "two", "three"};

    final Object output = objectArrayFormatter.format(input, 3, false);
    Assertions.assertEquals("(one,two,three)", output);
  }

  @Test
  public void test2Items() {
    final Object[] input = new Object[] {"one", "two"};

    final Object output = objectArrayFormatter.format(input, 3, false);
    Assertions.assertEquals("(one,two)", output);
  }

  @Test
  public void test1Item() {
    final Object[] input = new Object[] {"one"};

    final Object output = objectArrayFormatter.format(input, 3, false);
    Assertions.assertEquals("one", output);
  }
}
