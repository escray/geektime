class Solution {
public class List {
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode next = head, preNext = null;
    for (int i = 0; i < k; i++) {
      if (next == null) {
        preNext = null;
        break;
      }
      preNext = next;
      next = next.next;
    }

    if (preNext != null) {
      preNext.next = null;
      ListNode newHead = reverse(head);
      head.next = reverseKGroup(next, k);
      return newHead;
    }

    return head;
  }

  public ListNode reverse(ListNode n) {
    if (n.next == null) {
      return n;
    }
    ListNode next = n.next;
    ListNode newHead = reverse(next);
    next.next = n;
    return newHead;
  }

  public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
  }
}

}
