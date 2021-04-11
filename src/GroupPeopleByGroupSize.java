import java.util.*;

/*
https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
 */
public class GroupPeopleByGroupSize {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> sol = new ArrayList<>();
        Map<Integer, Group> utilMap = new HashMap<>();

        for(int i=0; i<groupSizes.length; i++) {
            if(!utilMap.containsKey(groupSizes[i])) {
                utilMap.put(groupSizes[i], new Group(groupSizes[i]));
            }
            Group currGroup = utilMap.get(groupSizes[i]);
            currGroup.add(i);
            if(currGroup.isFull()) {
                sol.add(currGroup.members);
                utilMap.remove(groupSizes[i]);
            }
        }
        return sol;
    }

}


class Group {
    public int maxSize;
    public int currSize;
    public List<Integer> members;

    public Group(int maxSize) {
        this.maxSize = maxSize;
        currSize = 0;
        members = new ArrayList<>();
    }

    public boolean isFull() {
        if(maxSize == currSize)
            return true;
        return false;
    }

    public boolean add(int member) {
        currSize++;
        return members.add(member);
    }
}
