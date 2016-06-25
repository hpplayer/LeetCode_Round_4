/*
156. Binary Tree Upside Down

Given a binary tree where all the right nodes are either leaf nodes with a sibling
(a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree
where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
*/
/**
 * Observation solution
 * 
 * We observe that input tree will be biased, i.e. we only have left subtree
 * 
 * The new Root is leftmost node, and roots of subtree are always prev left nodes. Therefore
 * we will go with left path
 *  
 * We need to record prev node and prev right node, so then we can assign updated left and
 * right child node to the updated root node which is old left node
 * 
 * Time complexity: O(N) all nodes will be visited once
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 24, 2016 9:42:04 PM
 */
public class Binary_Tree_Upside_Down_p156_sol1 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        //Observation solution
    	TreeNode curr = root, prev = null, prevRight = null;
        
        //while we still have left nodes left
        while( curr != null ){
            //update is consecutive, the curr update variable depends on prev updates
            
            //next visit point
            TreeNode left = curr.left;
            //new left is old right child
            curr.left = prevRight;
            //update prevRight
            prevRight = curr.right;
            //new right is old parent
            curr.right = prev;
            //update prev
            prev = curr;
            //go to next node
            curr = left;
        }
        
        //prev is last visited node, return it
        return prev;
    }
}
