import edu.princeton.cs.algs4.*;

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
                int num1 = pathsv.distTo(w);
                int num2 = pathsw.distTo(v);
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
        return 0;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable v, Iterable w) {
        return 0;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable v, Iterable w) {
        return 0;
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