import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class WordNetTest {

  private WordNet wordNet6TwoAncestors;
  @Before
  public void setUp() throws Exception {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets6.txt", "wordnet-test-files/hypernyms6TwoAncestors.txt");
  }

  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testWordNetNullSysnets() {
    WordNet w = new WordNet(null, "wordnet-test-files/hypernyms.txt");
  }
  
  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testWordNetNullHypernyms() {
    WordNet w = new WordNet("wordnet-test-files/sysnets.txt", null);
  }

  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testWordNetCycleHyper() {
    WordNet w = new WordNet("wordnet-test-files/sysnets.txt", "wordnet-test-files/hypernyms3InvalidCycle.txt");
  }

  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testWordNet2RootsHyper() {
    WordNet w = new WordNet("wordnet-test-files/sysnets.txt", "wordnet-test-files/hypernyms3InvalidTwoRoots.txt");
  }

  @Test
  public void testNouns() {
    String[] expectedNounsArray = {"a", "b", "c", "d", "e", "f"};
    ArrayList<String> expectedNouns = new ArrayList<String>(Arrays.asList(expectedNounsArray));
    for(String actualNoun: wordNet6TwoAncestors.nouns()) {
      assertTrue(expectedNouns.contains(actualNoun));
    }
  }

  @Test
  public void testNouns2() { // synset3 does not have a corresponding hypernym file
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets8.txt", "wordnet-test-files/hypernyms8ManyAncestors.txt");
    String[] expectedNounsArray = {"a", "b", "c", "d", "e", "f","g","h"};
    ArrayList<String> expectedNouns = new ArrayList<>(Arrays.asList(expectedNounsArray));
    for(String actualNoun: wordNet6TwoAncestors.nouns()) {
      assertTrue(expectedNouns.contains(actualNoun));
    }
  }

  @Test
  public void testNouns3() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets15.txt", "wordnet-test-files/hypernyms15Tree.txt");
    String[] expectedNounsArray = {"a", "b", "c", "d", "e", "f","g","h","i","j","k","l","m","n","o"};
    ArrayList<String> expectedNouns = new ArrayList<>(Arrays.asList(expectedNounsArray));
    for(String actualNoun: wordNet6TwoAncestors.nouns()) {
      assertTrue(expectedNouns.contains(actualNoun));
    }
  }

  @Test
  public void testIsNoun() {
    assertTrue(wordNet6TwoAncestors.isNoun("a"));
  }

  @Test
  public void testIsNoun2() {
    assertTrue(wordNet6TwoAncestors.isNoun("d"));
  }

  @Test
  public void testIsNoun3() {
    assertTrue(wordNet6TwoAncestors.isNoun("f"));
  }
  
  
  @Test
  public void testIsNounFalse() {
    assertFalse(wordNet6TwoAncestors.isNoun("g"));
  }

  @Test (timeout = 5000, expected = IllegalArgumentException.class)
  public void testIsNounNull() {
    assertFalse(wordNet6TwoAncestors.isNoun(null));
  }

  @Test
  public void testIsNounFalse2() {
    assertFalse(wordNet6TwoAncestors.isNoun(""));
  }

  @Test
  public void testIsNounFalse3() {
    assertFalse(wordNet6TwoAncestors.isNoun("aa"));
  }

  @Test
  public void testDistance() {
    assertEquals(3, wordNet6TwoAncestors.distance("e", "b"));
  }

  @Test
  public void testDistance1() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11AmbiguousAncestor.txt");
    assertEquals(2, wordNet6TwoAncestors.distance("j", "a"));
  }

  @Test
  public void testDistance2() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11AmbiguousAncestor.txt");
    assertEquals(2, wordNet6TwoAncestors.distance("e", "b"));
  }

  @Test
  public void testDistance3() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11ManyPathsOneAncestor.txt");
    assertEquals(1, wordNet6TwoAncestors.distance("h", "f"));
  }

  @Test
  public void testDistance4() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11ManyPathsOneAncestor.txt");
    assertEquals(3, wordNet6TwoAncestors.distance("a", "g"));
  }


  @Test
  public void testSap() {
    assertEquals("a", wordNet6TwoAncestors.sap("e", "b"));
  }

  @Test
  public void testSap1() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11AmbiguousAncestor.txt");
    assertEquals("j", wordNet6TwoAncestors.sap("j", "a"));
  }

  @Test
  public void testSap2() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11AmbiguousAncestor.txt");
    assertEquals("c", wordNet6TwoAncestors.sap("e", "h"));
  }

  @Test
  public void testSap3() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11ManyPathsOneAncestor.txt");
    assertEquals("f", wordNet6TwoAncestors.sap("j", "a"));
  }

  @Test
  public void testSap4() {
    wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets11.txt", "wordnet-test-files/hypernyms11ManyPathsOneAncestor.txt");
    assertEquals("f", wordNet6TwoAncestors.sap("a", "h"));
  }

}
