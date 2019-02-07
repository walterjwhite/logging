package com.walterjwhite.logging.formatter.enumeration;

import com.walterjwhite.logging.formatter.CollectionFormatter;

public class CollectionFormatterTest
    extends AbstractTest<CollectionFormatter, CollectionFormatterTestCase> {
  public CollectionFormatterTest() {
    super(
        CollectionFormatter.class, CollectionFormatterTestCase.values(), new CollectionFormatter());
  }

  protected Object doRun(CollectionFormatterTestCase collectionFormatterTestCase) {
    return testInstance.format(
        collectionFormatterTestCase.getInput(),
        collectionFormatterTestCase.getNumberOfArguments(),
        collectionFormatterTestCase.isSensitive());
  }
}
