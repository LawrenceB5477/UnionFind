/*
Uses same array data structure of quickFind. Each value in the array contains the index of its parent. Creates a tree like structure, with every tree having a root.
To implement find, see if the roots are the same.
To merge, set value of p's root to the value of q's root. Creates a large connected component.
 */

//Initialize N, Find N (worst case), Union N + cost of finding roots.
//Still too slow.
public class QuickUnion {
    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    //Get the root of a connected component
    private int findRoot(int i) {
        while(i != id[i]) {
            i = id[i];
        }
        return i;
    }

    //Sees if two indexes are connected by comparing their roots.
    public boolean findConnected(int p, int q) {
        return findRoot(q) == findRoot(p);
    }

    //Switches p's root to have q's root.
    public void union(int p, int q) {
        int proot = findRoot(p);
        int qroot = findRoot(q);

        id[proot] = qroot;
    }
}
