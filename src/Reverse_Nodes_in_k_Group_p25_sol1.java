/*
25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/**
 * LinkedList solution
 * 
 * No algorithm used, just use pointers to reverse node in k group.
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Remark:
 * Recursion is good but does not take constant memory due to the cost on stack
 * 
 * @author hpPlayer
 * @date Jun 13, 2016 9:35:23 PM
 */
public class Reverse_Nodes_in_k_Group_p25_sol1 {
    public ListNode reverseKGroup(ListNode head, int k) {
        //boundary check
        
        if(head == null || k <= 1) return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        
        while( head != null ){
            //curr is pointer inside k group, head is the pointer in the whole list
            ListNode curr = head;
            for(int i = 0; i < k; i++){
                if(curr == null) return dummy.next;
                curr = curr.next;
            }
            
            //tail is the last node in the k group
            ListNode last= reverseInK(head, curr);
            prev.next = last;
            
            //now head is the new last node in k group
            //which is also the prev for next k group
            prev = head;
            //update head to curr as curr now is the starting node in next k group
            head = curr;
        }
        
        return dummy.next;
    }
    
    public ListNode reverseInK(ListNode head, ListNode tail){
        //this function will reverse the input list. head-tail have k nodes
        
        ListNode prev = tail;
        ListNode curr = head;
        
        //tail is starting node in next k group.
        //our loop will keep going until reach this starting node
        while( curr != tail ){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
