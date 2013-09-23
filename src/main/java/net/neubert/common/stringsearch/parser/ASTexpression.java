package net.neubert.common.stringsearch.parser;


/**
 * expression -> term ("|" expression)?
 */
public class ASTexpression extends SimpleNode implements Matcher {

  public ASTexpression(int i) {
    super(i);
  }

  public ASTexpression(SimpleQueryParser p, int i) {
    super(p, i);
  }

  public boolean match(final String text, final boolean ignoreCase) {
    String effectiveText = (ignoreCase ? text.toLowerCase() : text);
    boolean matched = ((Matcher)jjtGetChild(0)).match(effectiveText, ignoreCase);
    if (matched) {
      return true;
    }

    int numChildren = jjtGetNumChildren();
    if (numChildren == 2) {
      return ((Matcher)jjtGetChild(1)).match(effectiveText, ignoreCase);
    }

    return matched;
  }
}
