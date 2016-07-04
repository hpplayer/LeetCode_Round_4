import java.util.*;

/*
294. Flip Game II

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
you and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/

/**
 * Backtracking solution
 * 
 * This solution is not hard, but also not easy to get up with
 * To check if player 1 can win the game we need to make sure player 2 cannot win the game 
 * In each recursion, player 1 may have several steps to choose, and we just need to find the one that make 
 * player2 dead in next move. 
 * 
 * Accordingly, if player 2 get the input, and he found a way to win the game, then in above recursion, it means
 * this step cannot make player 1 win the game, we need to search next move
 * 
 * Here we also use a HashMap to speed up the execution. Since we may face same string input several times, we can
 * return prev results directly
 * 
 * Time complexity: exponential
 * Space complexity: Same as above
 * 
 * @author hpPlayer
 * @date Jul 3, 2016 9:09:31 PM
 */
public class Flip_Game_II_p294_sol1 {
    public boolean canWin(String s) {
        return canWinWithHashMap(s, new HashMap<String, Boolean>());
    }
    
    public boolean canWinWithHashMap(String s, HashMap<String, Boolean> hs){
        if( hs.containsKey(s) ) return hs.get(s);
        
        for(int i = 1; i < s.length(); i++){
            if( s.charAt(i-1) == '+' && s.charAt(i) == '+' ){
                //found a place to flip
                String temp = s.substring(0, i-1) + "--" + s.substring(i+1);
                
                //if this move can make opponent dead, we will return true directly
                if( !canWinWithHashMap(temp, hs)  ){
                    //Notice: here we make a flip in input s to win the game, so we need record s not temp!!!!!
                    hs.put(s, true);
                    return true;
                }
            }
        }
        
        hs.put(s, false);
        return false;
    }
}
