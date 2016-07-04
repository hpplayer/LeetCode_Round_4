import java.util.*;

/*
297. Serialize and Deserialize Binary Tree


Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
serialization/deserialization algorithm should work.

You just need to ensure that a binary tree can be serialized to a string and this string can be
deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
should be stateless.
*/

/**
 * We need use queue in both Serialize() and Deserialize().
 * In Serialize(), it is easy to understand since we need to convert nodes to string layer by layer
 * In Deserialize(), why we need queue? Because the input tree may be incomplete, we have to know which node
 * we are looking at so that we can relate it with values in array. Therefore we could not use 2n + 1 and 2n+2
 * to get child nodes, which is only applicable to array of complete tree 
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 3, 2016 10:38:11 PM
 */
public class Serialize_and_Deserialize_Binary_Tree_p297_sol1 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        StringBuilder sb = new StringBuilder();
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                
                if( curr == null ){
                    sb.append( "#" ).append(",");
                }else{
                    sb.append( curr.val ).append(",");
                    que.offer(curr.left);
                    que.offer(curr.right);
                }
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        TreeNode root =strs[0].equals("#")? null : new TreeNode(  Integer.valueOf(strs[0]) );
        if(root == null) return null;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        for(int i = 1; i < strs.length; i +=2 ){
            TreeNode curr = que.poll();
            
            //we won't push null node to queue, so curr must be a valid node 
            //we just need to check its child node.
            //Since we won't build child node for null node, all nodes in str and queue must be valid and related
            if(  !strs[i].equals("#") ){
                curr.left = new TreeNode( Integer.valueOf(strs[i]) );
                que.offer(curr.left);
            }
            
            
            if(  !strs[i+1].equals("#") ){
                curr.right = new TreeNode( Integer.valueOf(strs[i+1]) );
                que.offer(curr.right);
            }
        }
        
        return root;
    }
}
