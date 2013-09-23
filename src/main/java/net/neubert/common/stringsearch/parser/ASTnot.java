package net.neubert.common.stringsearch.parser;

/**
 * not -> "^" text
 */
public class ASTnot extends SimpleNode implements Matcher {
  public ASTnot(int i) {
    super(i);
  }

  public ASTnot(SimpleQueryParser p, int i) {
    super(p, i);
  }

  public boolean match(final String text, final boolean ignoreCase) {
    return !((Matcher)jjtGetChild(0)).match(text, ignoreCase);
  }
}
