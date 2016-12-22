package com.soulraven.ccase.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageConvert {

    static int MONO_THRESHOLD = 368;

    public static void main(String[] args)
    {
        for (int i = 300; i < 400; i += 10) {
            toMonoImage("test_1.png", "test_1_" + i + ".png", i);
            toMonoImage("test_2.png", "test_2_" + i + ".png", i);
        }
    }

    private ImageConvert() {

    }

    public static void invertImage(String imageName) {
        invertImage(imageName, "invert-" + imageName);
    }

    public static void invertImage(String src, String dest) {
        BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File(src));
            inputFile = resize(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        try {
            File outputFile = new File(dest);
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage resize(BufferedImage originalImage) {
        int width = originalImage.getWidth() * 2;
        int height = originalImage.getHeight() * 2;
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }

    public static void toMonoImage(String imageName) {
        toMonoImage(imageName, "mono-" + imageName);
    }

    public static void toMonoImage(String src, String dest) {
        toMonoImage(src, dest, MONO_THRESHOLD);
    }

    public static void toMonoImage(String src, String dest, int threshold) {
        BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File(src));
            inputFile = resize(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);

                if (col.getRed() + col.getGreen() + col.getBlue() > threshold) {
                    col = new Color(255, 255, 255);
                }
                else {
                    col = new Color(0, 0, 0);
                }
                inputFile.setRGB(x, y, col.getRGB());
            }
        }
        try {
            File outputFile = new File(dest);
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
