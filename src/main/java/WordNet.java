
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Wordnet class.
 */
public class WordNet {
    /**
     * Maps noun to bag with ids.
     */
    private HashMap<String, Bag<Integer>> nouns;
    /**
     * Maps id to sysnet.
     */
    private HashMap<Integer, String> idmap;
    /**
     * Digraph of Wordnet.
     */
    private Digraph graph;
    /**
     * SAP of Wordnet.
     */
    private SAP sap;
    /**
     * HashSet for quick lookup of nouns.
     */
    private HashSet<String> strnouns;

    public WordNet(String synsets, String hypernyms) {
        nouns = new HashMap<>();
        idmap = new HashMap<>();
        In reader = new In(synsets);
        int v = 0;
        while (reader.hasNextLine()) {
            v++;
            String[] line = reader.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            String synset = line[1];
            String[] ns = synset.split(" ");

            for (int i = 0; i < ns.length; i++) {
                Bag<Integer> bag;
                if (nouns.containsKey(ns[i])) {
                    bag = nouns.get(ns[i]);
                } else {
                    bag = new Bag<>();
                }
                bag.add(id);
                nouns.put(ns[i], bag);
            }
            idmap.put(id, synset);
        }

        reader = new In(hypernyms);

        graph = new Digraph(v); // each id is a vertex
        while (reader.hasNextLine()) {
            String[] line = reader.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                graph.addEdge(id, Integer.parseInt(line[i]));
            }
        }
        if (!rootedDi()) throw new IllegalArgumentException();
        sap = new SAP(graph);
        strnouns = new HashSet<>(nouns.keySet());
    }

    private boolean rootedDi() {
        int roots = 0;
        for (int i = 0; i < graph.V(); i++) {
            if (!graph.adj(i).iterator().hasNext()) {
                roots++;
                if (roots > 1) return false;
            }
        }
        return roots == 1;
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return strnouns;
    }

    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return strnouns.contains(word);
    }

    public int distance(String nounA, String nounB) {
        return sap.length(nouns.get(nounA), nouns.get(nounB));
    }

    public String sap(String nounA, String nounB) {
        return idmap.get(sap.ancestor(nouns.get(nounA), nouns.get(nounB)));
    }

    public static void main(String[] args) {

    }
}