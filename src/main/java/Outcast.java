
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Outcast class.
 */
public class Outcast {
    /**
     * Wordnet object for outcast check.
     */
    private WordNet wordNet;

    public Outcast(WordNet wordnet) {
        wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxdist = -1;
        String ret = "";
        for (int i = 0; i < nouns.length; i++) {
            int curmax = 0;

            // Fix illegal arg exceptions

            for (int j = 0; j < nouns.length; j++) {
                if (nouns[i] != nouns[j]) {
                    curmax += wordNet.distance(nouns[i], nouns[j]);
                }
            }
            if (curmax > maxdist) {
                maxdist = curmax;
                ret = nouns[i];
            }

        }

        return ret;
    }


    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}