package net.neubert.common.stringsearch;

import net.neubert.common.stringsearch.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringSearchTest {
  private String text;

  @Before
  public void setup() {
    text = "Hello JavaCC. We would like to use you for a simple simplequery parser...";
  }

  @Test
  public void oneWord() throws ParseException {
    assertTrue(StringSearch.compile("hello").match(text));
  }

  @Test
  public void partOfAWord() throws ParseException {
    assertTrue(StringSearch.compile("mple").match(text));
  }

  @Test
  public void multipleWords() throws ParseException {
    assertTrue(StringSearch.compile(". we would like to use ").match(text));
  }

  @Test
  public void oneWordNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("bye").match(text));
  }

  @Test
  public void multipleWordsNoMatch() throws ParseException {
    assertFalse(StringSearch.compile(". we would not like to use ").match(text));
  }

  @Test
  public void oneAndOneWord() throws ParseException {
    assertTrue(StringSearch.compile("woul & for").match(text));
  }

  @Test
  public void multipleAndOneWord() throws ParseException {
    assertTrue(StringSearch.compile("woul&        for & simple&parser").match(text));
  }

  @Test
  public void multipleAndMultipleWords() throws ParseException {
    assertTrue(StringSearch.compile("woul & for a simple & parser").match(text));
  }

  @Test
  public void oneAndOneWordNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("woul & forrrr").match(text));
  }

  @Test
  public void multipleAndOneWordNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("woull&        for & simple&parser").match(text));
  }

  @Test
  public void multipleAndMultipleWordsNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("woul & for one simple & parser").match(text));
  }

  @Test
  public void oneOrOneWord() throws ParseException {
    assertTrue(StringSearch.compile("woul | for").match(text));
  }

  @Test
  public void multipleOrOneWord() throws ParseException {
    assertTrue(StringSearch.compile("woul|        for | simple|parser").match(text));
  }

  @Test
  public void multipleOrMultipleWords() throws ParseException {
    assertTrue(StringSearch.compile("    for a simple|parser").match(text));
  }

  @Test
  public void oneOrOneWordNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("wouul | forrrr").match(text));
  }

  @Test
  public void multipleOrOneWordNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("woull|        forr | simpleee|pparser").match(text));
  }

  @Test
  public void multipleOrMultipleWordsNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("woull|for onee simple | pparser").match(text));
  }

  @Test
  public void notOneWord() throws ParseException {
    assertTrue(StringSearch.compile("^lorem").match(text));
  }

  @Test
  public void notMultipleWords() throws ParseException {
    assertTrue(StringSearch.compile("^lorem ipsum").match(text));
  }

  @Test
  public void notOneWordNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("^simple").match(text));
  }

  @Test
  public void notMultipleWordsNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("^     simple simple").match(text));
  }

  @Test
  public void parenthesesOneWord() throws ParseException {
    assertTrue(StringSearch.compile("(hello)").match(text));
  }

  @Test
  public void parenthesesMultipleWords() throws ParseException {
    assertTrue(StringSearch.compile("(hello javacc   )").match(text));
  }

  @Test
  public void parenthesesNested() throws ParseException {
    assertTrue(StringSearch.compile("(((hello) & (java | nothing)) & ^(lorem | ipsum))").match(text));
  }

  @Test
  public void parenthesesPreventAssociative() throws ParseException {
    assertFalse(StringSearch.compile("(lorem | ipsum) & (dolor | simple simple)").match(text));
  }

  @Test
  public void associativeOneWord() throws ParseException {
    assertTrue(StringSearch.compile("lorem|ipsum&dolor|simple").match(text));
  }

  @Test
  public void complex() throws ParseException {
    assertTrue(StringSearch.compile("ipsum | ^lorem & simple & use you for & (would | java)").match(text));
  }

  @Test
  public void complexCaseSensitive() throws ParseException {
    assertTrue(StringSearch.compile("^lorem & simple & use you for & (would & Java)").match(text, false));
  }

  @Test
  public void complexCaseSensitiveNoMatch() throws ParseException {
    assertFalse(StringSearch.compile("^lorem & simple & use you for & (would & java)").match(text, false));
  }

  @Test
  public void performance() throws ParseException {
    for (int i=10; i<=1000000; i *= 10) {
      long start = System.currentTimeMillis();
      StringSearch search = StringSearch.compile("java | something & (nothing | hello)");
      for (int j=0; j<i; j++) {
        search.match(text);
      }
      System.out.println("" + i + ": " + (System.currentTimeMillis()-start) + "ms");
    }
  }
}
