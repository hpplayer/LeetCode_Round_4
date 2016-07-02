/*
255. Verify Preorder Sequence in Binary Search Tree

Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

/**
 * Stack solution
 * 
 * Validate a BST, we usually use lower and upper bound. In this problem, we visit the tree in preorder 
 * traversal, and we can just verify the lower bound to simply the validation step.
 * 
 * The basic idea is iteratively updating the lower boundary and reuse the input array to check the inputs
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 2, 2016 12:04:32 AM
 */
public class Verify_Preorder_Sequence_in_Binary_Search_Tree_p255_sol1 {
    public boolean verifyPreorder(int[] preorder) {
        int index = 0;
        int lower_boundary = Integer.MIN_VALUE;
        
        for(int temp : preorder){
            if( temp < lower_boundary ) return false;
            
            //checking if curr input is a node in right subtree
            while( index - 1 >= 0 && preorder[index - 1] < temp ){
                lower_boundary = preorder[--index];
            }
            
            //insert temp to preorder[] and move index
            preorder[index++] = temp;    
        }
        
        return true;
    }
}
