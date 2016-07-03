import java.util.*;

/*
Closest Binary Search Tree Value II

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.


Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ¡Ü total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
	getPredecessor(N), which returns the next smaller node to N.
	getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.
*/

/**
 * In order traversal
 * 
 * We will use in-order traversal to visit the tree and use a LinkedList to store all potential candidates.
 * We will stop traversal once we found curr node gives a larger difference. Since inorder traversal is mono ascending,
 * it means later nodes will give a larger difference, so we don't need to proceed
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 2, 2016 4:42:42 PM
 */
public class Closest_Binary_Search_Tree_Value_II_p272_sol1 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> nodes = new LinkedList<>();
        DFS(root, nodes, target, k);
        return nodes;
    }
    
    //return boolean value indicates whether we have found results
    public boolean DFS(TreeNode node, LinkedList<Integer> nodes, double target, int k){
        if(node == null) return false;
        
        //In inorder traversal, we visit left subtree first.
        //if left subtree already have results, then we stop here, no need to proceed
        if( DFS(node.left, nodes, target, k) ) return true;
        
        if( nodes.size() == k ){
            //if we have collected k nodes in list, then we need to check if we need replace nodes inside it
            
            if( Math.abs( target - nodes.peekFirst() ) > Math.abs( node.val - target ) ){
                //need replace.
                nodes.pollFirst();
            }else{
                //no need to replace, we have done traversal, return true
                return true;
            }
        }
        
        //we add curr node into list in general cases or the case that we have removed old first node in list 
        nodes.offerLast(node.val);
        
        //visit right subtree
        return DFS( node.right, nodes, target, k );
    }
}
