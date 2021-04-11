import java.util.*;

public class MergeKSortedLists {
    public ListNode mergeKList(ListNode[] a, int N)
    {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> {
            if(o1 == null && o2 != null) {
                return 1;
            }
            else if(o1 != null && o2 == null) {
                return -1;
            }
            else if(o1 == null && o2 == null) {
                return  0;
            }
            return o1.data - o2.data;
        });

        for(ListNode node : a) {
            if(node != null) {
                minHeap.add(node);
            }
        }

        ListNode head = new ListNode(-1);
        ListNode merge = head;
        while(!minHeap.isEmpty()) {
            merge.next = minHeap.poll();
            if(merge.next != null && merge.next.next != null) {
                minHeap.add(merge.next.next);
                merge.next.next = null;
            }
            merge = merge.next;
        }

        ListNode mergedList = head.next;
        head.next = null;
        return mergedList;
    }
}
