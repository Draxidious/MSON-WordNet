

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class SAP {
    private Digraph graph;
    private BreadthFirstDirectedPaths pathsv;
    private BreadthFirstDirectedPaths pathsw;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {

        graph = new Digraph(G);

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // this implementation can work for all the methods
    public int length(int v, int w) {
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

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        pathsv = new BreadthFirstDirectedPaths(graph, v);
        pathsw = new BreadthFirstDirectedPaths(graph, w);
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

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
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


    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        pathsv = new BreadthFirstDirectedPaths(graph, v);
        pathsw = new BreadthFirstDirectedPaths(graph, w);
        Iterator<Integer> witer = w.iterator();
        Iterator<Integer> viter = v.iterator();
        int smallest = Integer.MAX_VALUE;
        while (witer.hasNext()) {

            while (viter.hasNext()) {

                for (int i = 0; i < graph.V(); i++) {
                    if (pathsw.hasPathTo(i) && pathsv.hasPathTo(i)) {
                        int num1 = pathsv.distTo(i);
                        int num2 = pathsw.distTo(i);
                        if (num1 + num2 < smallest) {
                            smallest = num1 + num2;
                        }
                    }
                }
            }
        }

        if (smallest == Integer.MAX_VALUE) {
            return -1;
        } else {
            return smallest;
        }
    }

    // do unit testing of this class
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