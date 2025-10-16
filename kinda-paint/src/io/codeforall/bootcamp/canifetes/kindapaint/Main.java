package io.codeforall.bootcamp.canifetes.kindapaint;

import com.codeforall.simplegraphics.graphics.Canvas;

public class Main {

    public static void main(String[] args) {

        Canvas.setMaxX(600);
        Canvas.setMaxY(600);

        Field field = new Field(30, 30);

        // Center the game window after the menu initializes
        ScreenUtils.centerWindow();
    }

}
