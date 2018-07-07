import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SET;
//import edu.princeton.cs.algs4.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
	
    
    private final RedBlackBST<String,SET<Integer>> nouns; //use RedBlackBST for searches in isNoun
    private final ArrayList<String> lines; //used to store synsets
    private final Digraph wordGraph;
    // constructor takes the name of the two input files
	
    public WordNet(String synsets, String hypernyms) {
        lines = new ArrayList<>();
        nouns = new RedBlackBST<>();
        In sysnet = new In(synsets);
        In hypernym = new In(hypernyms);
//        String line;
        String[] parts;
        
        while(!sysnet.isEmpty()) {
            parts = sysnet.readLine().split(",");
            lines.add(parts[1]);
            
            for(String s: parts[1].split(" ")) {
                SET<Integer> value = nouns.get(s);
                if(value == null) {
                    value = new SET<>();//create new SET if not exist;
                    value.add(Integer.parseInt(parts[0]));
                    nouns.put(s, value);
                } else {
                    value.add(Integer.parseInt(parts[0]));
                }  
            }
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
    public Iterable<String> nouns(){
        ArrayList<String> copy = new ArrayList<String>();
        for(String s : lines) {
            copy.add(s);
        }
        return copy;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        /*nouns of a synset are separated by space; 
          words only match if first word of noun matches word*/
        return nouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        wordGraph.
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
//    public String sap(String nounA, String nounB) {
//        
//    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet test = new WordNet("testing\\synsets.txt"
                ,"testing\\hypernyms.txt");
        StdOut.printf(test.lines.get(5115));
        StdOut.printf("\n");
        for(int item : test.wordGraph.adj(5115)) {
            StdOut.print(item);
        }
//        for(String item : test.nouns()) {
//            StdOut.print(item+"\n");
//        }
        StdOut.print(test.isNoun("175"));
    }
}