import static org.junit.Assert.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class OutcastTest {
  private WordNet net;

  @Before
  public void setup()
  {
    String syn = "wordnet-test-files/synsets.txt";
    String hyp = "wordnet-test-files/hypernyms.txt";

    net = new WordNet(syn, hyp);

  }

  @Test
  public void testOutcast() {
    Outcast out = new Outcast(net);
    In in = new In("wordnet-test-files/outcast10.txt");
    String[] lines = in.readAllLines();
    String[] input = new String[lines.length-1];
    System.arraycopy(lines, 0, input, 0, lines.length - 1);
    assertEquals("albatross",out.outcast(input));

  }

  @Test
  public void testOutcast1() {
    Outcast out = new Outcast(net);
    In in = new In("wordnet-test-files/outcast29.txt");
    String[] lines = in.readAllLines();
    String[] input = new String[lines.length-1];
    System.arraycopy(lines, 0, input, 0, lines.length - 1);
    assertEquals("acorn",out.outcast(input));
  }

  @Test
  public void testOutcast2() {
    Outcast out = new Outcast(net);
    In in = new In("wordnet-test-files/outcast5.txt");
    String[] lines = in.readAllLines();
    String[] input = new String[lines.length-1];
    System.arraycopy(lines, 0, input, 0, lines.length - 1);
    assertEquals("cat",out.outcast(input));
  }

}
