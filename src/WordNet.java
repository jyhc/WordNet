import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
	
//    private final ResizingArrayQueue<String> lines;
    public final ArrayList<String> lines = new ArrayList<String>();
    public final Digraph wordGraph;
    // constructor takes the name of the two input files
	
    public WordNet(String synsets, String hypernyms) {
        In sysnet = new In(synsets);
        In hypernym = new In(hypernyms);
//        String line;
        String[] parts;
        
        while(!sysnet.isEmpty()) {
            parts = sysnet.readLine().split(",");
            lines.add(parts[1]);
        }
        wordGraph = new Digraph(lines.size());
        
        while(!hypernym.isEmpty()) {
            parts = hypernym.readLine().split(",");
            for(int i = 1; i < parts.length; i++) {
                wordGraph.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[i]));
            }
        }
        
        sysnet.close();
        hypernym.close();
    }
    // returns all WordNet nouns
//    public Iterable<String> nouns(){
//        
//    }

    // is the word a WordNet noun?
//    public boolean isNoun(String word) {
//        
//    }

    // distance between nounA and nounB (defined below)
//    public int distance(String nounA, String nounB) {
//        
//    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
//    public String sap(String nounA, String nounB) {
//        
//    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet test = new WordNet("C:\\Users\\Test\\git\\WordNet\\wordnet-testing\\synsets.txt"
                ,"C:\\Users\\Test\\git\\WordNet\\wordnet-testing\\hypernyms.txt");
        StdOut.printf(test.lines.get(5115));
        StdOut.printf("\n");
        for(int item : test.wordGraph.adj(5115)) {
            StdOut.print(item);
        }

    }
}