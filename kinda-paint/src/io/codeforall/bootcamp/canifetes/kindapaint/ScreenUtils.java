package io.codeforall.bootcamp.canifetes.kindapaint;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class ScreenUtils {

    public static void centerWindow() {
        // Get all open AWT frames
        Frame[] frames = Frame.getFrames();

        if (frames.length == 0) {
            System.err.println("No frames found to center.");
            return;
        }

        // Usually, the SimpleGraphics window is the first or last one
        Frame gameFrame = frames[0];

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Get window size
        int windowWidth = gameFrame.getWidth();
        int windowHeight = gameFrame.getHeight();

        // Compute center coordinates
        int x = (screenSize.width - windowWidth) / 2;
        int y = (screenSize.height - windowHeight) / 2;

        // Move the frame
        gameFrame.setLocation(x, y);
    }
}
