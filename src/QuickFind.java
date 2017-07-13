/*
Union Algorithm Questions - connect two objects, or see if they are connected.
Connected Component - A set of objects that are connected to one another.
Quick find - Put all the objects in an array. Two indexes are connected if they have the same value.
Computers do 10^9 Operations per second. Takes 1 second to look at all 10^9 operations.
 */

//Quick find algorithm
//Initialize N, Find connected 1, Union N
//Takes n^2 array accesses to perform a union N times. N times, the array must be accessed N times.
//If you were to do this on 10^9 objects in memory, would take 10^18 operations - 30 years.
public class QuickFind {
    private int[] id;

    public void quickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    //Sees if p and q are in the same connected component. Quick find algorithm.
    public boolean isConnected(int p, int q) {
        return id[q] == id[p];
    }

    //Union Algorithm
    public void union(int p, int q) {
        int pValue = id[p];
        int qValue = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pValue) {
                id[i] = qValue;
            }
        }
    }
}
