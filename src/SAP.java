import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.RedBlackBST;

public class SAP {

    private BreadthFirstDirectedPaths bfsv;
    private BreadthFirstDirectedPaths bfsw;
    private Digraph G;
    private final RedBlackBST<Integer, Integer> sapS; //single source vertex
    private final RedBlackBST<Integer, Integer> sapM; //multiple source vertexes
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G); //deep copy to make digraph immutable
        sapS = new RedBlackBST<>();
        sapM = new RedBlackBST<>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        bfsv = new BreadthFirstDirectedPaths(G, v);
        bfsw = new BreadthFirstDirectedPaths(G, w);
        for(int i = 0; i < G.E() ; i++) {
            if(bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                sapS.put(bfsv.distTo(i) + bfsw.distTo(i), i);
            }            
        }
        if(sapS.isEmpty()) return -1;
        else return sapS.min();
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        int temp = length(v, w);
        if(temp == -1) {
            return -1;
        } else return sapS.get(temp);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        bfsv = new BreadthFirstDirectedPaths(G, v);
        bfsw = new BreadthFirstDirectedPaths(G, w);
        for(int i = 0; i < G.E() ; i++) {
            if(bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                sapM.put(bfsv.distTo(i) + bfsw.distTo(i), i);
            }            
        }
        if(sapM.isEmpty()) return -1;
        else return sapM.min();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int temp = length(v, w);
        if(temp == -1) {
            return -1;
        } else return sapM.get(temp);
    }
    
    // do unit testing of this class
    public static void main(String[] args) {
        
    }
}