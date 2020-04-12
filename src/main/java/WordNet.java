import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import java.util.HashMap;
import java.util.HashSet;


public class WordNet {
    private HashMap<String, Bag<Integer>> nouns; // maps noun to Bag with ids
    private HashMap<Integer, String> idmap; //maps id to synset
    private Digraph graph;
    private SAP sap;
    private HashSet<String> strnouns;

    // constructor takes the name of the two input files
    // going to need to find a way to associate an id with its synset
    public WordNet(String synsets, String hypernyms) {
        nouns = new HashMap<>();
        idmap = new HashMap<>();
        In reader = new In(synsets);
        int V = 0;
        while (reader.hasNextLine()) {
            V++;

            //split the line

            String[] line = reader.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            String synset = line[1];

            // grab the id
            // grab the synset out of the current line
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

            // for each noun in the synset
            // if that noun is not in your HashMap
            // create an entry with the id
            // else
            // add the current id to the Bag associated with that noun
        }

        // use the reader to read the hypernyms file
        reader = new In(hypernyms);

        graph = new Digraph(V); // each id is a vertex
        while (reader.hasNextLine()) {
            String[] line = reader.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                graph.addEdge(id, Integer.parseInt(line[i]));
            }

            // grab the current line,
            // add an edge to graph for each pair of hypernyms (remember that id of the main sysnet if first)
            // (all hypernyms come after)
        }
        // creat a SAP object with your Digraph
        if(!rootedDi()) throw new IllegalArgumentException();
        sap = new SAP(graph);
        strnouns = new HashSet<>(nouns.keySet());
    }
    private boolean rootedDi()
    {
       int roots = 0;
       for (int i = 0; i<graph.V();i++)
       {
           if(!graph.adj(i).iterator().hasNext())
           {
               roots++;
               if(roots>1) return false;
           }
       }
       return true;
    }
    // returns all WordNet nouns
    public Iterable<String> nouns() {
       return strnouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if(word==null) throw new IllegalArgumentException();
        return strnouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return sap.length(nouns.get(nounA),nouns.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    // return sysnet with id
    public String sap(String nounA, String nounB) {
        return idmap.get(sap.ancestor(nouns.get(nounA),nouns.get(nounB)));
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}