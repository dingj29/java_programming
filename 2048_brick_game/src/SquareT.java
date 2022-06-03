/**
 * @file: SquareT.java
 * @Author: Jianlan Ding - dingj29
 * @Date: Feb 12, 2022
 * @Description: SquareT module that create object of SquareT
 */
//package src;

import java.awt.Color;
import java.awt.Font;


public class SquareT {
    public int value;

    /**
     * @brief constructor
     */
    public SquareT() {
        value = 0;
    }

    /**
     * @brief show the color of the font for each square
     */
    public Color getForeground(){
        if (value == 0){
            return new Color(0xcdc1b4);
        }
        else{ return Color.BLACK;}
    }

    /**
     * @brief show the color background for each square
     */
    public Color getBackground() {
        return switch (value) {
            case 0 -> new Color(0xcdc1b4);
            case 2 -> new Color(0xE8E0D7);
            case 4 -> new Color(0xE8DCC5);
            case 8 -> new Color(0xECAD78);
            case 16 -> new Color(0xEF9261);
            case 32 -> new Color(0xEC785D);
            case 64 -> new Color(0xEF5D3B);
            case 128 -> new Color(0xE5C86F);
            case 256 -> new Color(0xE8C85F);
            case 512 -> new Color(0xE8C44F);
            case 1024 -> new Color(0xEAC340);
            case 2048 -> new Color(0xEAC02E);
            default -> new Color(0xF1AE02);
        };
    }

    /**
     * @brief the font of value for each square has to change over time to fit in
     */
    public Font getSquareFont() {
        if (value <= 8) {
            return Game.font1;
        }
        if (value <= 64) {
            return Game.font2;
        }
        if (value <= 512) {
            return Game.font3;
        }
        if (value <= 4096) {
            return Game.font4;
        }
        return Game.font5;
    }

}


