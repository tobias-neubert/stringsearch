package net.neubert.common.stringsearch.parser;

public interface Matcher {
  boolean match(final String text, final boolean ignoreCase);
}
