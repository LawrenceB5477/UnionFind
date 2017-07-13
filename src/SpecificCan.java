/*
Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i)
returns the largest element in the connected component containing i. The operations, union(), connected(),
and find() should all take logarithmic time or better.

For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9
for each of the four elements in the connected components.
 */
public class SpecificCan {
    private int[] objects;
    private int[] treeSizes;

    public SpecificCan(int N) {
        objects = new int[N];
        treeSizes = new int[N];
        for (int i = 0; i < N; i++) {
            objects[i] = i;
            treeSizes[i] = 1;
        }
    }

    //Find the root object and implement tree compression.
    public int root(int i) {
        while(objects[i] != i) {
            objects[i] = objects[objects[i]];
            i = objects[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);

        if (proot == qroot) {
            return;
        }

        if (treeSizes[proot] < treeSizes[qroot]) {
            objects[proot] = qroot;
            treeSizes[qroot] += treeSizes[proot];
        } else {
            objects[qroot] = proot;
            treeSizes[proot] += treeSizes[qroot];
        }
    }

    //Find the largest value in a tree that contains the argument
    public int find(int i) {
        int iRoot = root(i);
        int largestVal = i;

        for (int j = 0; j < objects.length; j++) {
            if (root(objects[j]) == iRoot && j > largestVal ) {
                largestVal = j;
            }
        }
        return largestVal;
    }
}
