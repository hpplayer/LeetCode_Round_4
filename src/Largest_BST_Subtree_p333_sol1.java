/*
333. Largest BST Subtree

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree
with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree,
which will result in O(nlogn) time complexity.

Follow up:
Can you figure out ways to solve it with O(n) time complexity?
*/

/**
 * Post-order traversal
 * 
 * To make sure if a tree is BST or not, we need firstly get the information from left and right subtrees.
 * If either left or right subtree is not valid, we return result immediately. 
 * If both subtrees are valid, then we check if we can include curr node by checking left_max and right_min vs
 * curr.val. 
 * 
 * To be convenient we return a dummy node for null node. So we can calculate leaf node correctly.
 * But then we need to replace min/max value of dummy node by using the real value of leaf node
 * Otherwise, min/max value will not be correctly update!!!!!!!!!!!!!!!
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 8, 2016 8:37:16 PM
 */
public class Largest_BST_Subtree_p333_sol1 {
    public int largestBSTSubtree(TreeNode root) {
        return DFS(root).size;
    }
    
    private MyNode DFS(TreeNode root){
        //return a valid MyNode for null node
        if(root == null) return new MyNode(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        
        MyNode left = DFS(root.left);
        MyNode right = DFS(root.right);
        
        //in case left or right node is invalid, or curr node is invalid, we return immediately 
        if( !left.isValid || !right.isValid || root.val <= left.mx || root.val >= right.mn ){
            return new MyNode(0, 0, Math.max(left.size, right.size), false);
        }
        
        //valid node
        //we need check min and max to replace values from dummy node of null node
        int min = root.left == null? root.val : left.mn;
        int max = root.right == null? root.val : right.mx;
        
        return new MyNode(min, max, left.size + 1 + right.size, true);
    }
    
    private class MyNode{
        int mn;
        int mx;
        int size;
        boolean isValid;
        
        MyNode(int mn, int mx, int size, boolean isValid){
            this.mn = mn;
            this.mx = mx;
            this.size = size;
            this.isValid = isValid;
        }
    }
}
