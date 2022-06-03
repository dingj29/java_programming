/**
 * @file: TestBoardT.java
 * @Author: Jianlan Ding - dingj29
 * @Date: Feb 12, 2022
 * @Description: Test module for boardT.java
 */
//package src;
import org.junit.*;
import static org.junit.Assert.*;

public class TestBoardT {
    private SquareT[][] squares = new SquareT[4][4];
    private BoardT boardT = new BoardT();

    @Before
    public void init() {
        set_squares();
        squares[0][1].value = 16;
        squares[0][3].value = 16;
        squares[1][0].value = 4;
        squares[1][2].value = 2;
        squares[2][2].value = 2;
        squares[3][0].value = 4;
        squares[3][3].value = 2;
        boardT.set_squares(squares);

    }

    @After
    public void tearDown()
    {
        squares = null;
    }

    public void set_squares(){
        for (int indexRow = 0; indexRow < 4; indexRow++) {
            for (int indexCol = 0; indexCol < 4; indexCol++) {
                squares[indexRow][indexCol] = new SquareT();
            }
        }
    }

    @Test
    public void test_getEmptySquares(){
        assert (boardT.getEmptySquares().size() == 9);
    }

    @Test
    public void test_generateSquareT(){
        boardT.setCombine(true);
        boardT.generateSquareT();
        boardT.setCombine(true);
        boardT.generateSquareT();
        assert (boardT.getEmptySquares().size() == 7);
    }

    @Test
    public void test_slide(){
        boardT.slide();
        assert (boardT.getEmptySquares().size() == 10);
    }

    @Test
    public void test_reverse_matrix(){
        boardT.reverse_matrix();
        assert (boardT.getSquares()[1][3].value == 4);
    }

    @Test
    public void test_transpose(){
        boardT.transpose_matrix();
        assert (boardT.getSquares()[0][1].value == 4);
    }

    @Test
    public void test_check_over(){
        assert (!boardT.check_over());
    }

    @Test
    public void test_check_win(){
        assert (!boardT.check_win());
    }



}

