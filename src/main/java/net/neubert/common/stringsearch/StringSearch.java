package net.neubert.common.stringsearch;


import net.neubert.common.stringsearch.parser.ASTcompile;
import net.neubert.common.stringsearch.parser.ASTexpression;
import net.neubert.common.stringsearch.parser.ParseException;
import net.neubert.common.stringsearch.parser.SimpleQueryParser;

import java.io.StringReader;

public class StringSearch {
  private ASTcompile astCompile;

  private StringSearch() {
  }

  private StringSearch(String simpleQuery) throws ParseException {
    SimpleQueryParser parser = new SimpleQueryParser(new StringReader(simpleQuery));
    astCompile = (ASTcompile)parser.compile();
  }

  public static StringSearch compile(String simpleQuery) throws ParseException {
    return new StringSearch(simpleQuery);
  }

  public boolean match(String text) {
    return match(text, true);
  }

  public boolean match(String text, boolean ignoreCase) {
    return ((ASTexpression)astCompile.jjtGetChild(0)).match(text, ignoreCase);
  }
}
