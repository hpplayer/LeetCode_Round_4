import java.util.*;

/*
353. Design Snake Game

Design a Snake game that is played on a device with screen size = width x height.
Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and
the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food
was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

*/

/**
 * Queue solution
 * 
 * We use a queue to represent the body of snake and use another queue to represent the fruit chain
 * 
 * Time complexity: O(N) for move(), (N is len of foodChain), since we need check the snakeBody in each move()
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jul 9, 2016 8:12:38 PM
 */

public class Design_Snake_Game_p353_sol1 {
    LinkedList<int[]> foodChain;
    LinkedList<int[]> bodyChain;
    int width;
    int height;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public Design_Snake_Game_p353_sol1(int width, int height, int[][] food) {
        foodChain = new LinkedList<>();
        bodyChain = new LinkedList<>();
        
        for(int[] temp : food){
            foodChain.offerLast(temp);
        }    
        
        bodyChain.offerLast(new int[]{0, 0});
        this.width = width;
        this.height = height;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] head = bodyChain.peekLast();
        int xOffset = 0, yOffset = 0;
        
        switch(direction){
            case "U":
                xOffset--;
                break;
            case "L":
                yOffset--;
                break;
            case "R":
                yOffset++;
                break;
            case "D":
                xOffset++;
                break;
        }
        
        int[] newHead = new int[]{ head[0] + xOffset, head[1] + yOffset };
        if(newHead[0] < 0 || newHead[0] >= height || newHead[1] < 0 || newHead[1] >= width) return -1;
        
        int[] food = foodChain.isEmpty()? new int[]{-1, -1} : foodChain.peekFirst();
        
        
        if(newHead[0] == food[0] && newHead[1] == food[1]){
            //keep tail and convert food to newHead
            foodChain.pollFirst();
        }else{
            //remove tail if no food get
            bodyChain.pollFirst();
        }
        
        for(int i = 0; i < bodyChain.size(); i++){
            if( bodyChain.get(i)[0] == newHead[0] && bodyChain.get(i)[1] == newHead[1] ){
                return -1;
            }
        }
        
        //add new head
        bodyChain.offerLast(newHead);
        
        return bodyChain.size() - 1;
    }
}
