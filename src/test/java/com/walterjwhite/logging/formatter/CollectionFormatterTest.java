package com.walterjwhite.logging.formatter;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionFormatterTest {
  protected final CollectionFormatter collectionFormatter = new CollectionFormatter();

  @Test
  public void test1Item() {
    final List input = Arrays.asList("one");

    final Object output = collectionFormatter.format(input, 3, false);
    Assertions.assertEquals("one", output);
  }

  @Test
  public void test2Items() {
    final List input = Arrays.asList("one", "two");

    final Object output = collectionFormatter.format(input, 3, false);
    Assertions.assertEquals("(one,two)", output);
  }

  @Test
  public void test3Items() {
    final List input = Arrays.asList("one", "two", "three");

    final Object output = collectionFormatter.format(input, 3, false);
    Assertions.assertEquals("(one,two,three)", output);
  }

  @Test
  public void testFirst3Items() {
    final List input = Arrays.asList("one", "two", "three", "four");

    final Object output = collectionFormatter.format(input, 3, false);
    Assertions.assertEquals("first 3(one,two,three)", output);
  }

  // catch the AssertionError if running tests in a group to allow the others to continue
  @Test
  public void testBadAssertion() {
    // final FunctionalInterface f = Assert::assertNotNull;
    try {
      Assertions.assertNotNull(null);
      //      Assert.assertNotnul
    } catch (Exception e) {
      // System.out.print;
      e.printStackTrace();
    }
  }
}
