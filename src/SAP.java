import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

    private BreadthFirstDirectedPaths bfsv;
    private BreadthFirstDirectedPaths bfsw;
    private final Digraph G;
    private RedBlackBST<Integer, Integer> sapS; //single source vertex
    private RedBlackBST<Integer, Integer> sapM; //multiple source vertexes
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if(G == null) {
            throw new java.lang.IllegalArgumentException();
        }
        this.G = new Digraph(G); //deep copy to make digraph immutable
 
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        sapS = new RedBlackBST<>();
        bfsv = new BreadthFirstDirectedPaths(G, v);
        bfsw = new BreadthFirstDirectedPaths(G, w);
        for(int i = 0; i < G.V() ; i++) {
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
        if(v == null || w == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if(!v.iterator().hasNext() || !w.iterator().hasNext()) {
            throw new java.lang.IllegalArgumentException();
        }
        sapM = new RedBlackBST<>();
        bfsv = new BreadthFirstDirectedPaths(G, v);
        bfsw = new BreadthFirstDirectedPaths(G, w);
        for(int i = 0; i < G.V() ; i++) {
            if(bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                sapM.put(bfsv.distTo(i) + bfsw.distTo(i), i);
            }            
        }
        if(sapM.isEmpty()) return -1;
        else return sapM.min();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if(v == null || w == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if(!v.iterator().hasNext() || !w.iterator().hasNext()) {
            throw new java.lang.IllegalArgumentException();
        }
        int temp = length(v, w);
        if(temp == -1) {
            return -1;
        } else return sapM.get(temp);
    }
    
    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("testing\\digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}