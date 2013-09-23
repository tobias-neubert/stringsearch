package net.neubert.common.stringsearch.parser;

/**
 * unary -> text | not
 */
public class ASTunary extends SimpleNode implements Matcher {
  public ASTunary(int i) {
    super(i);
  }

  public ASTunary(SimpleQueryParser p, int i) {
    super(p, i);
  }

  public boolean match(final String text, final boolean ignoreCase) {
    return ((Matcher)jjtGetChild(0)).match(text, ignoreCase);
  }
}
