import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    
    private final WordNet wordNet;
//    private RedBlackBST<Integer, String> distances;
    
    public Outcast(WordNet wordnet) {    // constructor takes a WordNet object 
        wordNet = wordnet;
    }
    public String outcast(String[] nouns) {  // given an array of WordNet nouns, return an outcast
        RedBlackBST<Integer, String> distances = new RedBlackBST<>();
        for (String noun_i : nouns) {
            int temp = 0;
            for (String noun_n : nouns) {
                temp += wordNet.distance(noun_i, noun_n);                     
            }
            distances.put(temp, noun_i);
        }
        return distances.get(distances.max());
    }
    
    public static void main(String[] args) {// see test client below
        WordNet wordnet = new WordNet("testing\\synsets.txt"
                ,"testing\\hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            In in = new In(s);
            String[] nouns = in.readAllStrings();
            StdOut.println(s + ": " + outcast.outcast(nouns));
        }
    }
    
}