/**
 * @file: Demo.java
 * @Author: Jianlan Ding - dingj29
 * @Date: Feb 12, 2022
 * @Description: Runs the game (client code)
 */
//package src;

public class Demo {
    public static void main(String[] args) {
        //src.BoardT model = new src.BoardT();
        BoardT model = new BoardT();
        UserInterface view = new UserInterface();
        GameController game = GameController.getInstance(model, view);
        game.runGame();
    }
}

