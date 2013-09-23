package net.neubert.common.stringsearch.parser;

/**
 * parentheses -> "(" expression ")"
 */
public class ASTparentheses extends SimpleNode implements Matcher {
  public ASTparentheses(int i) {
    super(i);
  }

  public ASTparentheses(SimpleQueryParser p, int i) {
    super(p, i);
  }

  public boolean match(String text, boolean ignoreCase) {
    return ((ASTexpression)jjtGetChild(0)).match(text, ignoreCase);
  }
}
