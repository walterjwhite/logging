package com.walterjwhite.logging;

import com.walterjwhite.logging.util.ArgumentUtil;
import org.slf4j.LoggerFactory;

public class TestLogging {
  public static void main(final String[] arguments) {
    LoggerFactory.getLogger("test").error("{}({})", "superDuperMethod", null);
    LoggerFactory.getLogger("test").error("{}({})", "superDuperMethod", new Object[] {});
    LoggerFactory.getLogger("test").error("{}({})", "superDuperMethod");

    final Object[] input = testFive();
    //    final Object[] input = testNull();

    final Object result = ArgumentUtil.getArguments(input, 3);
    System.out.println("result:" + result);

    if (result instanceof Object[]) {
      final Object[] a = (Object[]) result;
      for (int i = 0; i < a.length; i++) System.out.println("result:" + a[i]);
    }
  }

  private static Object[] testFive() {
    final Object[] input = new Object[5];
    input[0] = "First";
    input[1] = "Second";
    input[2] = "Third";
    input[3] = Object.class;
    input[4] = Class.class;

    return input;
  }

  private static Object[] testNull() {
    return null;
  }
}
