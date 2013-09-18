package net.neubert.common.stringsearch;


import net.neubert.common.stringsearch.parser.ParseException;
import net.neubert.common.stringsearch.parser.SimpleQueryParser;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringSearchTest {
  @Test
  public void firstTest() throws ParseException {
    SimpleQueryParser parser = new SimpleQueryParser(new StringReader("Hello & JavaCC"));
    assertTrue(parser.expression("Hello, it is fun working with JavaCC!"));
  }

  @Test
  public void secondTest() throws ParseException {
    SimpleQueryParser parser = new SimpleQueryParser(new StringReader("Hello & Bye"));
    assertFalse(parser.expression("Hello, it is fun working with JavaCC!"));
  }
}
