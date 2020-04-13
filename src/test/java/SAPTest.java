import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {
  private Digraph graph;
  private SAP sapDigraph1;
  @Before
  public void setup() {
    In in = new In("wordnet-test-files/digraph1.txt");
    Digraph G = new Digraph(in);
    graph = G;
    System.out.println(G);
    sapDigraph1 = new SAP(G);
  }

  @Test
  public void testSAPImmutable() {
    int anc = sapDigraph1.ancestor(7,9);
    graph.reverse();
    assertEquals(anc,sapDigraph1.ancestor(7,9));
  }

  @Test
  public void testLengthIntInt() {
    assertEquals(4, sapDigraph1.length(3, 11));
  }

  @Test
  public void testLengthIntInt2() {
    In in = new In("wordnet-test-files/digraph-ambiguous-ancestor.txt");
    Digraph G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(3, sapDigraph1.length(5, 1));
     in = new In("wordnet-test-files/digraph2.txt");
     G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(2, sapDigraph1.length(3, 1));

  }

  @Test
  public void testAncestorIntInt() {
    assertEquals(1, sapDigraph1.ancestor(3, 11));
  }
  @Test
  public void testAncestorIntInt2() {
    In in = new In("wordnet-test-files/digraph-ambiguous-ancestor.txt");
    Digraph G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(2, sapDigraph1.ancestor(5, 1));
    in = new In("wordnet-test-files/digraph2.txt");
    G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(3, sapDigraph1.ancestor(3, 1));
  }


  @Test
  public void testLengthIterableIterable() {
    ArrayList<Integer> verticesV = new ArrayList<Integer>();
    verticesV.add(7);
    verticesV.add(4);
    verticesV.add(9);
    ArrayList<Integer> verticesW = new ArrayList<Integer>();
    verticesW.add(11);
    verticesW.add(2);
    assertEquals(3,sapDigraph1.length(verticesV, verticesW));
  }

  @Test
  public void testLengthIterableIterable2() {
    ArrayList<Integer> verticesV = new ArrayList<Integer>();
    verticesV.add(1);
    verticesV.add(3);
    verticesV.add(5);
    ArrayList<Integer> verticesW = new ArrayList<Integer>();
    verticesW.add(10);
    verticesW.add(4);

    In in = new In("wordnet-test-files/digraph-ambiguous-ancestor.txt");
    Digraph G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(1, sapDigraph1.length(verticesV, verticesW));

     verticesV = new ArrayList<Integer>();
    verticesV.add(1);
    verticesV.add(4);
    verticesW = new ArrayList<Integer>();
    verticesW.add(2);
    verticesW.add(5);


    in = new In("wordnet-test-files/digraph2.txt");
    G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(1,sapDigraph1.length(verticesV, verticesW));
  }



  @Test
  public void testAncestorIterableIterable() {
    ArrayList<Integer> verticesV = new ArrayList<Integer>();
    verticesV.add(7);
    verticesV.add(4);
    verticesV.add(9);
    ArrayList<Integer> verticesW = new ArrayList<Integer>();
    verticesW.add(11);
    verticesW.add(2);
    assertEquals(5, sapDigraph1.ancestor(verticesV, verticesW));
  }

  @Test
  public void testAncestorIterableIterable2() {
    ArrayList<Integer> verticesV = new ArrayList<Integer>();
    verticesV.add(1);
    verticesV.add(3);
    verticesV.add(5);
    ArrayList<Integer> verticesW = new ArrayList<Integer>();
    verticesW.add(10);
    verticesW.add(4);

    In in = new In("wordnet-test-files/digraph-ambiguous-ancestor.txt");
    Digraph G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(4, sapDigraph1.ancestor(verticesV, verticesW));

    verticesV = new ArrayList<Integer>();
    verticesV.add(1);
    verticesV.add(4);
    verticesW = new ArrayList<Integer>();
    verticesW.add(2);
    verticesW.add(5);


    in = new In("wordnet-test-files/digraph2.txt");
    G = new Digraph(in);
    sapDigraph1 = new SAP(G);
    assertEquals(2,sapDigraph1.ancestor(verticesV, verticesW));
  }

  

}
