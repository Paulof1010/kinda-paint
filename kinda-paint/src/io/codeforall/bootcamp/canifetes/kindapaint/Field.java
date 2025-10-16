package io.codeforall.bootcamp.canifetes.kindapaint;

import com.codeforall.simplegraphics.pictures.Picture;

public class Field {

    private Pointer pointer;
    private Cell tile;
    private Cell[][] cells;
    private int rows;
    private int cols;
    private Picture picture1;
    private Picture picture2;

    // Field Constructor
    public Field(int rows, int cols) {

        picture1 = new Picture(5, 5, "/Users/codecadet/Desktop/work/kinda-paint/kinda-paint/resources/pics/carolina.png");
        picture2 = new Picture(305, 305, "/Users/codecadet/Desktop/work/kinda-paint/kinda-paint/resources/pics/diogo.png");
        picture1.draw();
        picture2.draw();

        this.rows = rows;
        this.cols = cols;

        int tileSize = 20;

        cells = new Cell[rows][cols];

        int startX = 5;
        int startY = 5;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = startX + col * tileSize;
                int y = startY + row * tileSize;

                cells[row][col] = new Cell(x, y, tileSize);
                cells[row][col].draw(); // Draw the cell
            }
        }

        // Pointer constructor
        pointer = new Pointer(startX, startY, tileSize, cols, rows, this); // Pointer
    }

    public Cell getCellAt(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null;
        }
        return cells[row][col];
    }

    public void resetField() {
        for(Cell[] line : cells){
            for (Cell quadradixu : line){
                quadradixu.unfill();
            }
        }
    }

    public void fillField() {
        for(Cell[] line : cells){
            for (Cell quadradixu : line){
                quadradixu.fill();
            }
        }
    }

    // Pointer getter
    public Pointer getPointer() {
        return pointer;
    }
}
