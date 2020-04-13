
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * SAP Class.
 */
public class SAP {
    /**
     * Digraph in SAP.
     */
    private Digraph graph;
    /**
     * BFDirected Path for v input.
     */
    private BreadthFirstDirectedPaths pathsv;
    /**
     * BFDirecrted Path for w input.
     */
    private BreadthFirstDirectedPaths pathsw;

    public SAP(Digraph G) {

        graph = new Digraph(G);

    }

    public int length(int v, int w) {
        pathsv = new BreadthFirstDirectedPaths(graph, v);
        pathsw = new BreadthFirstDirectedPaths(graph, w);
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V()) throw new IllegalArgumentException();
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (pathsw.hasPathTo(i) && pathsv.hasPathTo(i)) {
                int num1 = pathsv.distTo(i);
                int num2 = pathsw.distTo(i);

                if (num1 + num2 < smallest) {
                    smallest = num1 + num2;

                }
            }
        }
        if (smallest == Integer.MAX_VALUE) {
            return -1;
        } else {
            return smallest;
        }


    }

    public int ancestor(int v, int w) {
        pathsv = new BreadthFirstDirectedPaths(graph, v);
        pathsw = new BreadthFirstDirectedPaths(graph, w);
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V()) throw new IllegalArgumentException();
        int smallest = Integer.MAX_VALUE;
        int anc = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (pathsw.hasPathTo(i) && pathsv.hasPathTo(i)) {
                int num1 = pathsv.distTo(i);
                int num2 = pathsw.distTo(i);
                if (num1 + num2 < smallest) {
                    smallest = num1 + num2;
                    anc = i;
                }
            }
        }
        return anc;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        if (anyNull(v, w)) throw new IllegalArgumentException();
        pathsv = new BreadthFirstDirectedPaths(graph, v);
        pathsw = new BreadthFirstDirectedPaths(graph, w);
        int smallest = Integer.MAX_VALUE;


        for (int i = 0; i < graph.V(); i++) {
            if (pathsw.hasPathTo(i) && pathsv.hasPathTo(i)) {
                int num1 = pathsv.distTo(i);
                int num2 = pathsw.distTo(i);
                if (num1 + num2 < smallest) {
                    smallest = num1 + num2;
                }
            }


        }

        if (smallest == Integer.MAX_VALUE) {
            return -1;
        } else {
            return smallest;
        }
    }

    private boolean anyNull(Iterable<Integer> v, Iterable<Integer> w) {

        Iterator<Integer> viter = v.iterator();
        Iterator<Integer> witer = w.iterator();
        while (viter.hasNext()) {
            Integer next = viter.next();
            if (next == null) return true;
            if (next < 0 || next > graph.V()) return true;
        }
        while (witer.hasNext()) {
            Integer next = witer.next();
            if (next == null) return true;
            if (next < 0 || next > graph.V()) return true;
        }
        return false;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        if (anyNull(v, w)) throw new IllegalArgumentException();
        pathsv = new BreadthFirstDirectedPaths(graph, v);
        pathsw = new BreadthFirstDirectedPaths(graph, w);
        int anc = -1;
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < graph.V(); i++) {
            if (pathsw.hasPathTo(i) && pathsv.hasPathTo(i)) {
                int num1 = pathsv.distTo(i);
                int num2 = pathsw.distTo(i);
                if (num1 + num2 < smallest) {
                    smallest = num1 + num2;
                    anc = i;
                }
            }
        }


        return anc;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}