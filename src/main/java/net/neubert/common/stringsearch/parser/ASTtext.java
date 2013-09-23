package net.neubert.common.stringsearch.parser;

/**
 * text -> <TEXT> | parentheses
 */
public class ASTtext extends SimpleNode implements Matcher {
  public ASTtext(int i) {
    super(i);
  }

  public ASTtext(SimpleQueryParser p, int i) {
    super(p, i);
  }

  public boolean match(final String text, final boolean ignoreCase) {
    if (jjtGetNumChildren() == 0) {
      String token = jjtGetFirstToken().image.trim();
      if (ignoreCase) {
        token = token.toLowerCase();
      }

      return text.contains(token);
    }

    return ((ASTparentheses)jjtGetChild(0)).match(text, ignoreCase);
  }
}
