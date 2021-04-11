import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        int sol[][] = new int[people.length][];
        if(people.length > 0) {
            RopeTreeNode root = new RopeTreeNode();

        }
        return sol;
    }

    public RopeTreeNode insert(RopeTreeNode root, int height, int peopleInFrontWithGreaterHeight, int value) {
        if(root == null) {
            RopeTreeNode tmp = new RopeTreeNode();
            tmp.leftValue = value;
            tmp.peopleInFrontWithGreaterHeight = peopleInFrontWithGreaterHeight;
            return tmp;
        }

        if(root.leftValue > value) {
            root.leftValue++;
            root.left = insert(root.left, height, peopleInFrontWithGreaterHeight, value);
        }


        return root;
    }

    public int[][] reconstructQueue_v2(int[][] people) {
        //1st sort by the people
        Arrays.sort(people, (o1, o2) -> o1[1] - o2[1]);

        //2nd now move all the people to their correct place
        for(int i=1; i<people.length; i++) {
            int count = people[i][1];
            int j=0;
            for(; j<i; j++) {
                if(people[j][0] >= people[i][0])
                    count--;
                if(count < 0) {
                    break;
                }
            }
            int tmp0 = people[i][0];
            int tmp1 = people[i][1];
            for(int k = i-1; k >= j; k--) {
                people[k+1][0] = people[k][0];
                people[k+1][1] = people[k][1];
            }
            people[j][0] = tmp0;
            people[j][1] = tmp1;
        }

        return people;
    }

    public int[][] reconstructQueue_v3(int[][] people) {
        //System.out.println(Arrays.deepToString(people));
        //1st sort by the people
        Arrays.sort(people, (o1, o2) -> o1[1] - o2[1]);
        if(people.length == 0)
            return people;

        //System.out.println(Arrays.deepToString(people));
        DLLNode head = new DLLNode();
        head.val = new People(people[0][0], people[0][1]);
        DLLNode curr = head;
        for(int i=1; i<people.length; i++) {
            DLLNode temp = new DLLNode();
            temp.val = new People(people[i][0], people[i][1]);
            temp.prev = curr;
            curr.next = temp;
            curr = temp;
        }

        for(DLLNode i=head.next; i!=null;) {
            //DLLNode nextNode = i.next;
            int count = i.val.inFront;
            DLLNode j=head;
            boolean adjFlag = false;
            for(;j != i; j=j.next) {
                if(j.val.height >= i.val.height)
                    count--;
                if(count < 0) {
                    adjFlag = true;
                    break;
                }
            }
            if(adjFlag) {
                //System.out.print("Before: ");
                //printz(head);
                DLLNode adjNode = i;
                if(adjNode.prev != null) adjNode.prev.next = adjNode.next;
                if(adjNode.next != null) adjNode.next.prev = adjNode.prev;
                DLLNode jPrev = j.prev;
                //if(jPrev != null)  System.out.println("jPrev: " + jPrev.val.height + " " + jPrev.val.inFront);
                if(jPrev != null) jPrev.next = adjNode;
                adjNode.prev = jPrev;
                adjNode.next = j;
                j.prev = adjNode;
                if(jPrev == null)
                    head = i;
                //System.out.print("After: ");
                //printz(head);
                i=j.next;
            }
            else
                i=i.next;
        }
        while(head.prev != null)
            head = head.prev;

        int idx = 0;
        for(DLLNode i=head; i!=null; i=i.next){
            people[idx][0] = i.val.height;
            people[idx][1] = i.val.inFront;
            idx++;
        }

        return people;
    }

    public void printz(DLLNode head) {

        while(head.prev != null)
            head = head.prev;

        while (head!=null) {
            System.out.print(" " + head.val.height + ", " + head.val.inFront + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String args[]) {
        QueueReconstructionByHeight obj = new QueueReconstructionByHeight();
        int arr[][] = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int arr_[][] = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        System.out.println(Arrays.deepToString(obj.reconstructQueue_v2(arr)));
        System.out.println(Arrays.deepToString(obj.reconstructQueue_v3(arr_)));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        int arr2[][] = {{3,0}, {2,1}, {1,1}};
        int arr2_[][] = {{3,0}, {2,1}, {1,1}};
        System.out.println(Arrays.deepToString(obj.reconstructQueue_v2(arr2)));
        System.out.println(Arrays.deepToString(obj.reconstructQueue_v3(arr2_)));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");

        int arr3[][] = {{3,0}, {1,2}, {2,1}, {4,0}};
        int arr3_[][] = {{3,0}, {1,2}, {2,1}, {4,0}};
        System.out.println(Arrays.deepToString(obj.reconstructQueue_v2(arr3)));
        System.out.println(Arrays.deepToString(obj.reconstructQueue_v3(arr3_)));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

class People{
    int height;
    int inFront;

    public People(int h, int f) {
        height = h;
        inFront = f;
    }
}

class DLLNode {
    DLLNode prev;
    DLLNode next;
    People val;
}

class RopeTreeNode {
    RopeTreeNode left;
    RopeTreeNode right;
    int peopleInFrontWithGreaterHeight;
    int leftValue;
}

class SortByPeopleInFront implements Comparator<int[]> {
    @Override
    public int compare(int[] o1, int[] o2) {
        if(o1 == o2)
            return 0;
        return o1[1] - o2[1];
    }
}
