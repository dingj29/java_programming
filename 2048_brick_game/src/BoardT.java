/**
 *@file BoardT.java
 *@author Jianlan Ding - dingj29
 *@brief Class of ADT BoardT, extends JPanel and implements KeyListener.
 *@date Feb 12, 2022
 */
//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class BoardT extends JPanel implements KeyListener {
    private static final int SQUARE_GAP = 10;
    private static final int SQUARE_ARC = 20;
    private static final int SQUARE_SIZE = 86;

    // define state variables
    private SquareT[][] squares = new SquareT[4][4];
    private boolean combine = true;
    private int score = 0;

    /**
     * @brief constructor
     * @details generates a board with two squares with values, other remains zero
     */
    public BoardT() {
        initGame();
        addKeyListener(this);
    }

    /**
     * @brief Override KeyPressed from KeyListener
     * @param event - the key user pressed on keyboard
     * @details monitor the keyboard and then calls the corresponding functions
     */
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (check_over()){initGame();}
                break;
            case KeyEvent.VK_LEFT:
                slide();
                generateSquareT();
                check_win();
                check_over();
                break;
            case KeyEvent.VK_RIGHT:
                reverse_matrix();
                slide();
                reverse_matrix();
                generateSquareT();
                check_win();
                check_over();
                break;
            case KeyEvent.VK_UP:
                transpose_matrix();
                slide();
                transpose_matrix();
                generateSquareT();
                check_win();
                check_over();
                break;
            case KeyEvent.VK_DOWN:
                transpose_matrix();
                reverse_matrix();
                slide();
                reverse_matrix();
                transpose_matrix();
                generateSquareT();
                check_win();
                check_over();
                break;
            default:
                break;
        }
        UserInterface.jScore.setText(score + "");
        repaint();
    }

    /**
     * @brief initialize the squares, which contains information of all checks
     * @details initialize all SquareT objects, initialize the game with 2 squares with value
     */
    private void initGame() {
        score = 0;
        for (int indexRow = 0; indexRow < 4; indexRow++) {
            for (int indexCol = 0; indexCol < 4; indexCol++) {
                squares[indexRow][indexCol] = new SquareT();
            }
        }
        combine = true;
        generateSquareT();
        combine = true;
        generateSquareT();
    }


    /**
     * @brief generates value for a random SquareT in squares at any position on board
     * @details get the empty square list first, then random choose a SquareT object ana set the value
     */
    public void generateSquareT() {
        List<SquareT> temp_list = getEmptySquares();
        if (!temp_list.isEmpty() && combine) {
            Random random = new Random();
            int index = random.nextInt(temp_list.size());
            temp_list.get(index).value = (random.nextInt(10) % 9 == 0) ? 4 : 2;
            combine = false;
        }
    }

    /**
     * @brief returns a list of SquareT object whose values are all zero
     * @return checkList - a list of SquareT objects
     */
    public List<SquareT> getEmptySquares() {
        List<SquareT> checkList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (squares[i][j].value == 0) {
                    checkList.add(squares[i][j]);
                }
            }
        }
        return checkList;
    }

    /**
     * @brief return a boolean value for the game is over or not
     * @return true if game over, false for not game over
     */
    public boolean check_over() {
        if (!getEmptySquares().isEmpty()) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (squares[i][j].value == squares[i][j + 1].value
                        || squares[i][j].value == squares[i + 1][j].value) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @brief return a boolean value for the game is over or not
     * @return true if game over, false for not game over
     */
    public boolean check_win(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++ ){
                if (squares[i][j].value >= 2048){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @brief reverse the matrix squares
     */
    public void reverse_matrix(){
        for (int i = 0; i < 4; i++) {
            int start = 0;
            int end = 4 - 1;
            while (start < end) {
                SquareT temp = squares[i][start];
                squares[i][start] = squares[i][end];
                squares[i][end] = temp;
                start++;
                end--;
            }
        }
    }

    /**
     * @brief get transpose the matrix squares
     */
    public void transpose_matrix(){
        SquareT[][] temp = new SquareT[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[j][i] = squares[i][j];
            }
        }
        squares = temp;
    }

    /**
     * @brief slide all the SquareT to the left in squares
     * @details slide all non zero value SquareT,
     * and combine the SquareT if the values are the same, update the score
     */
    public void slide(){
        for (int i = 0; i < 4; i++) {
            for (int j = 1, index = 0; j < 4; j++) {
                if (squares[i][j].value > 0) {
                    if (squares[i][j].value == squares[i][index].value) {
                        score += squares[i][index++].value <<= 1;
                        squares[i][j].value = 0;
                        combine = true;
                    } else if (squares[i][index].value == 0) {
                        squares[i][index].value = squares[i][j].value;
                        squares[i][j].value = 0;
                        combine = true;
                    } else if (squares[i][++index].value == 0) {
                        squares[i][index].value = squares[i][j].value;
                        squares[i][j].value = 0;
                        combine = true;
                    }
                }
            }
        }
    }

    /**
     * @brief overwrite the paint method, and paint the game board each time.
     * @details if win or lose, pop up a new screen
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                drawSquare(g, i, j);
            }
        }

        if (check_win()) {
            g.setColor(new Color(246, 76, 102, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            FontMetrics fms = getFontMetrics(new Font("TimesRoman", Font.BOLD, 25));
            String value = "Congratulation you win!";
            String value2 = "Press enter for a new game.";
            g.drawString(value,
                    (getWidth()-fms.stringWidth(value)) / 2,
                    getHeight() / 3);
            g.drawString(value2,
                    (getWidth()-fms.stringWidth(value2)) / 2,
                    (getHeight() / 3)+50);
        }

        else if (check_over()) {
            g.setColor(new Color(64, 64, 64, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            FontMetrics fms = getFontMetrics(new Font("TimesRoman", Font.BOLD, 25));
            String value = "The game is over!";
            String value2 = "Press enter for a new game.";
            g.drawString(value,
                    (getWidth()-fms.stringWidth(value)) / 2,
                    getHeight() / 3);
            g.drawString(value2,
                    (getWidth()-fms.stringWidth(value2)) / 2,
                    (getHeight() / 3)+50);
        }
    }

    /**
     * @brief draw the game board
     * @param g java.awt, Graphics
     * @param i first index for each square in matrix
     * @param j second index for each square in matrix
     */
    private void drawSquare(Graphics g, int i, int j) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_NORMALIZE);
        SquareT square = squares[i][j];
        gg.setColor(square.getBackground());


        gg.fillRoundRect(SQUARE_GAP + (SQUARE_GAP + SQUARE_SIZE) * j,
                SQUARE_GAP + (SQUARE_GAP + SQUARE_SIZE) * i,
                SQUARE_SIZE, SQUARE_SIZE, SQUARE_ARC, SQUARE_ARC);
        gg.setColor(square.getForeground());
        gg.setFont(square.getSquareFont());


        FontMetrics fms = getFontMetrics(square.getSquareFont());
        String value = String.valueOf(square.value);

        gg.drawString(value,
                SQUARE_GAP + (SQUARE_GAP + SQUARE_SIZE) * j +
                        (SQUARE_SIZE - fms.stringWidth(value)) / 2,
                SQUARE_GAP + (SQUARE_GAP + SQUARE_SIZE) * i +
                        (SQUARE_SIZE - fms.getAscent() - fms.getDescent()) / 2
                        + fms.getAscent());
    }

    /**
     * @brief set_squares set the squares, called by TestBoardT.java
     * @param squares SquareT[][] object
     */
    public void set_squares(SquareT[][] squares){
        this.squares = squares;

    }

    /**
     * @brief setCombine set the combine, called by TestBoardT.java
     * @param combine boolean type
     */
    public void setCombine(boolean combine) {
        this.combine = combine;
    }

    /**
     * @brief getSquares return squares, called by TestBoardT.java
     * @return self.squares
     */
    public SquareT[][] getSquares() {
        return squares;
    }

    /**
     * function in KeyListener
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * function in KeyListener
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

}

