/*
99. Recover Binary Search Tree

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/

/**
 * Morris Traversal
 * 
 * To avoid the usage of stack, we use Morris-traversal to scan the input tree. 
 * Basically, we first go to rightmost node of left subtree, then make is connect with curr node
 * Therefore once we are done with left subtree, we can use this new edge to come back to curr node without extra
 * stack. 
 * 
 * Regarding the detection of swapped nodes, we just visit the tree with in-order traversal. This is because 
 * nodes in in-order traversal are following monon-ascending order. If we swap two nodes in BST, we will firstly
 * detect the swapped large node first as it is larger than next node, then we will detect the swapped small node
 * as it is smaller than its prev node
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 18, 2016 11:44:16 PM
 */
public class Recover_Binary_Search_Tree_p99_sol1 {
    public void recoverTree(TreeNode root) {
        //Morris Traversal solution
        
        //we need mark swapped nodes
        TreeNode first = null;
        TreeNode second = null;
        
        TreeNode prev = null;
        TreeNode curr = root;
        //pred = predecessor, the pointer we used to connect rightmost node in left subtree with curr root
        TreeNode pred = null;
        
        while( curr != null ){
            //check prev and curr
            
            //error node detected if we found prev.val > curr.val in inorder traversal of BST
            if(prev != null && prev.val > curr.val){
                if(first == null){
                    //first node is the larger swapped node
                    first = prev; 
                }
                
                //second node is the smaller swapped node
                //Since we may get the corner case that parent node is swapped with child node
                //we have to mark second node when we found the first node or found another incorrect pair
                second = curr;
            }
            
            
            if( curr.left != null ){
                //start morris traversal if we have left subtree       
                
                //find the rightmost node in left subtree
                pred = curr.left;
                
                //rightmost node is either connected with curr root(visited before) or has null as right child(unvisited )
                while(pred.right != null && pred.right != curr){
                    pred = pred.right;
                }
                
                if(pred.right == null){
                    //unvisited node, make it connect with curr root
                    pred.right = curr;
                    curr = curr.left;
                }else{
                    //visited node, we finished left subtree and just went back to the root node
                    //we cut our morris edge, and go back
                    pred.right = null;
                    prev = curr;
                    //start visiting right subtree  
                    curr = curr.right;
                }
                
            }else{
                //no left subtree, starts visiting right subtree
                prev = curr;
                curr = curr.right;
            }
        }
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
