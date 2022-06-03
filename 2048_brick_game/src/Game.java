/**
 * @file: Game.java
 * @Author: Jianlan Ding - dingj29
 * @Date: Feb 12, 2022
 * @Description: The Interface module
 */
//package src;

import java.awt.*;

public interface Game {

    Font scoreFont = new Font("TimesRoman", Font.BOLD, 46);
    Font font1 = new Font("TimesRoman", Font.BOLD, 46);
    Font font2 = new Font("TimesRoman", Font.BOLD, 40);
    Font font3 = new Font("TimesRoman", Font.BOLD, 34);
    Font font4 = new Font("TimesRoman", Font.BOLD, 28);
    Font font5 = new Font("TimesRoman", Font.BOLD, 22);

    void init();
    void showView();
}
