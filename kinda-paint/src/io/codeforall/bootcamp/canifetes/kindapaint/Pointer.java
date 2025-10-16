package io.codeforall.bootcamp.canifetes.kindapaint;

import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.simplegraphics.pictures.Picture;

public class Pointer implements KeyboardHandler, Runnable {

    private Picture pointerRect;
    private final int pointerSize;
    private int x;
    private int y;
    private final int minX, minY, maxX, maxY;
    private final Field field;

    private boolean visible = true;
    private boolean running = true;

    public Pointer(int startX, int startY, int pointerSize, int fieldCols, int fieldRows, Field field) {
        this.pointerSize = pointerSize;
        this.x = startX;
        this.y = startY;
        this.field = field;

        // Set movement boundaries based on field dimensions
        minX = 5;
        minY = 5;
        maxX = 5 + (fieldCols - 1) * pointerSize;
        maxY = 5 + (fieldRows - 1) * pointerSize;

        drawPointer();

        initKeyboard();

        // Start blinking in a new thread
        new Thread(this).start();
    }

    private void toggleCurrentCell() {
        int col = (x - 5) / pointerSize;
        int row = (y - 5) / pointerSize;

        Cell currentCell = field.getCellAt(row, col);

        if (currentCell != null) {
            currentCell.toggle(); // triggers fill or unfill
        }
    }

    private void initKeyboard() {
        Keyboard keyboard = new Keyboard(this);

        int[] keys = {
                KeyboardEvent.KEY_W,
                KeyboardEvent.KEY_S,
                KeyboardEvent.KEY_A,
                KeyboardEvent.KEY_D,
                KeyboardEvent.KEY_SPACE,
                KeyboardEvent.KEY_R,
                KeyboardEvent.KEY_ENTER,
                KeyboardEvent.KEY_ESC
        };

        for (int key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int newX = x;
        int newY = y;

        switch (event.getKey()) {
            case KeyboardEvent.KEY_W:
                newY -= pointerSize;
                System.out.println("\n Moving up...");
                break;
            case KeyboardEvent.KEY_S:
                newY += pointerSize;
                System.out.println("\n Moving down...");
                break;
            case KeyboardEvent.KEY_A:
                newX -= pointerSize;
                System.out.println("\n Moving left...");
                break;
            case KeyboardEvent.KEY_D:
                newX += pointerSize;
                System.out.println("\n Moving right...");
                break;
            case KeyboardEvent.KEY_SPACE:
                toggleCurrentCell();
                break;
            case KeyboardEvent.KEY_R:
                field.resetField();
                break;
            case KeyboardEvent.KEY_ENTER:
                field.fillField();
                break;
            case KeyboardEvent.KEY_ESC:
                System.out.println("\n Quitting...");
                field.getPointer().stop();
                System.exit(0);
                break;
        }

        // Clamp to boundaries
        if (newX < minX || newX > maxX || newY < minY || newY > maxY) {
            return;
        }

        // Update position and re-draw
        x = newX;
        y = newY;

        if (visible) {
            pointerRect.delete();
            drawPointer();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Not needed
    }

    private void drawPointer() {
        pointerRect = new Picture(x, y, "/Users/codecadet/Desktop/work/kinda-paint/kinda-paint/resources/pointers/pointer.png");
        pointerRect.draw();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(400); // Blink interval
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (visible) {
                if (pointerRect != null) {
                    pointerRect.delete();
                }
            } else {
                drawPointer();
            }

            visible = !visible;
        }
    }

    public void stop() {
        running = false;
    }
}
