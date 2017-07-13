/*
N - members
M - timestamps at which unions occurred.
Find when all members have the same root
 */
public class SocialNetwork {
    private int memberArray[];
    private int memberRootSizes[];
    private int rootMembers;
    private int currentDate;

    public SocialNetwork(int N) {
        rootMembers = N;
        memberArray = new int[N];
        memberRootSizes = new int[N];

        for (int i = 0; i < N; i++) {
            memberArray[i] = i;
            memberRootSizes[i] = 1;
        }
    }

    //Find the root of a member friendship chain. Each member that is not a root is replaced with their second back friend.
    private int findRoot(int memberNumber) {
        while (memberArray[memberNumber] != memberNumber) {
            memberArray[memberNumber] = memberArray[memberArray[memberNumber]];
            memberNumber = memberArray[memberNumber];
        }
        return memberNumber;
    }

    //Perform a union, decrements a root member
    public void union(int member1, int member2, int time) {
        int member1Root = findRoot(member1);
        int member2Root = findRoot(member2);

        if (member1Root == member2Root) {
            return;
        }

        if(memberRootSizes[member1Root] < memberRootSizes[member2Root]) {
            memberArray[member1Root] = member2Root;
            memberRootSizes[member2Root] += memberRootSizes[member1Root];
            currentDate = time;
            rootMembers -= 1;
        } else {
            memberArray[member2Root] = member1Root;
            memberRootSizes[member1Root] += memberRootSizes[member2Root];
            currentDate = time;
            rootMembers -= 1;
        }
    }

    //Only returns true when all members have been connected one way or another.
    public boolean connected() {
        if (rootMembers == 1) {
            return true;
        }
        return false;
    }

    //To answer this, you would basically call the union for each pair of members that friended and call the connected method.
    //If it turns true, the timestap associated with it would be the earliest point in time all friends were connected.

}
