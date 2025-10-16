package io.codeforall.bootcamp.canifetes.kindapaint;

import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.graphics.Color;

public class Cell {

    private Rectangle tile;
    private boolean filled = false;  // track fill state

    public Cell(int x, int y, int size) {
        tile = new Rectangle(x, y, size, size);
    }

    public void draw() {
        tile.setColor(Color.BLACK);
        tile.draw();
    }

    public void fill() {
        tile.setColor(Color.BLACK);  // or any color
        tile.fill();
        filled = true;
    }

    public boolean isFilled() {
        return filled;
    }

    public void unfill() {
        tile.delete();  // erase current filled rectangle
        tile = new Rectangle(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
        draw();         // redraw the empty cell border
        filled = false;
    }

    public void toggle() {
        if (filled) {
            unfill();
        } else {
            fill();
        }
    }
}
