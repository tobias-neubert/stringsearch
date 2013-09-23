package net.neubert.common.stringsearch.parser;

/**
 * term -> unary ("&" term)?
 */
public class ASTterm extends SimpleNode implements Matcher {
  public ASTterm(int i) {
    super(i);
  }

  public ASTterm(SimpleQueryParser p, int i) {
    super(p, i);
  }

  public boolean match(final String text, final boolean ignoreCase) {
    boolean matched = ((Matcher)jjtGetChild(0)).match(text, ignoreCase);
    if (!matched) {
      return false;
    }

    int numChildren = jjtGetNumChildren();
    if (numChildren == 2) {
      return ((Matcher)jjtGetChild(1)).match(text, ignoreCase);
    }

    return matched;
  }
}
