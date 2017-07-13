/*
Improve Quick Union by implementing weighting and tree compression.
Make sure that trees don't get too big, by butting smaller tree roots as bigger tree roots.
Always put the smaller tree lower.
 */
//Find, takes time proportional to how far down the tree is, union takes a constant time given roots.
//Depth of any node is at most lg_2 N
/*
Proof
Say you have a node or an object, let's call it x.
The value of X increases when the tree it is on, T1, is unified with another tree, T2
This causes the size of the tree X is contained in to at least double, because T2 >= T1 for it unify.
You can do this at most lg_2 N times. If you start with 1, and double lg N times, you get N.
This algorithm is very close to linear
 */
public class ImprovedQuickUnion {
    private int[] id;
    private int[] size;

    ImprovedQuickUnion(int N) {
        id = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    //Find the root.
    /*
    Improve using path compression. The node that is not the root is pointed to the node two nodes above it.
    This halves trees.
     */
    private int findRoot(int i) {
        while(i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    //See if the values are connected
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    //Perform a weighted quick union.
    public void weightedUnion(int p, int q) {
        int proot = findRoot(p);
        int qroot = findRoot(q);
        if (proot == qroot) {
            return;
        }
        //Keeps track of sizing and adds size to the bigger root when quick union is performed.
        if(size[proot] < size[qroot]) {
            id[proot] = qroot;
            size[qroot] += size[proot];
        } else {
            id[qroot] = proot;
            size[proot] += size[qroot];
        }

    }
}
