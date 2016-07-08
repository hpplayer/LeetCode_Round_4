/*
331. Verify Preorder Serialization of a Binary Tree

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value.
If it is a null node, we record using a sentinel value such as #.

_9_
/   \
3     2
/ \   / \
4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/

/**
 * Observation solution
 * 
 * If we are using the preorder traversal to visit the tree, then we should visit parent first, then two children.
 * Each parent node has one incoming edge(except for root node) and two outgoing edges, each child node also
 * has an incoming edge and two outgoing edges (except for leaf node). So we can just count the diff between 
 * incoming node and outgoing node to validate if input tree is valid
 * 
 * In this solution we will -1 for each outgoing edge and we will + 1 for each incoming edge
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 8, 2016 12:29:20 AM
 */
public class Verify_Preorder_Serialization_of_a_Binary_Tree_p331_sol1 {
    public boolean isValidSerialization(String preorder) {
        //boundary check
        
        if( preorder.length() == 0 ) return false;
        
        String[] strs = preorder.split(",");
        //count is the difference between incoming and outgoing edges
        //if root is null node, then it has no incoming nor outgoing edges
        //otherwise it has two outgoing edges
        int count = strs[0].equals("#")? 0 : -2;
        
        for(int i = 1; i < strs.length; i++){
            //each node in tree (except for root) must has an incoming edge
            //incoming count shall not exceed outgoing count
            if( ++count > 0 ) return false;
            
            //for non null node, we have two outgoing edges
            if( !strs[i].equals("#") ) count -= 2;
        }
        
        //finally check count = 0 ( we have deducted incoming edge from root node)
        return count == 0;
    }
}
