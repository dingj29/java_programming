/**
 * @file: UserInterface.java
 * @Author: Jianlan Ding - dingj29
 * @Date: Feb 12, 2022
 * @Description: view module that displays the status of the game, implements Game
 */
//package src;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Game {
    private static final int jFrameWidth = 408;
    private static final int jFrameHeight = 510;
    private JFrame jMain;
    static JLabel jScore;
    public UserInterface() {
        init();
    }

    /**
     * @brief init the game board, override interface's method
     */
    @Override
    public void init() {
        jMain = new JFrame("2048 by Sam Ding");
        jMain.setSize(jFrameWidth, jFrameHeight);
        jMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jMain.setLocationRelativeTo(null);
        jMain.setResizable(false);
        jMain.setLayout(null);


        JLabel jScoreTitle = new JLabel("Score:", JLabel.CENTER);
        jScoreTitle.setFont(scoreFont);
        jScoreTitle.setForeground(Color.BLACK);
        jScoreTitle.setOpaque(true);
        jScoreTitle.setBounds(50, 0, 200, 70);
        jMain.add(jScoreTitle);

        jScore = new JLabel("0", JLabel.LEFT);
        jScore.setFont(scoreFont);
        jScore.setForeground(Color.BLACK);
        jScore.setOpaque(true);
        jScore.setBounds(250, 0, 130, 70);
        jMain.add(jScore);


        BoardT gameBoard = new BoardT();
        gameBoard.setBounds(0, 80, 400, 400);
        gameBoard.setBackground(Color.pink);
        gameBoard.setFocusable(true);
        gameBoard.setLayout(new FlowLayout());
        jMain.add(gameBoard);
    }

    /**
     * @brief To show the game board
     */
    @Override
    public void showView() {
        jMain.setVisible(true);
    }

}



