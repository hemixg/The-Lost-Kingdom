package main;

import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaleImage = new BufferedImage(width, height, original.getType());
        scaleImage.getGraphics().drawImage(original, 0, 0, width, height, null);
        return scaleImage;
    }
}
