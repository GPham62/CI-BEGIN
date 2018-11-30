package tklibs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by huynq on 5/11/17.
 */
public class SpriteUtils {
    public static BufferedImage loadImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<BufferedImage> loadImages(String... urls) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for(String url: urls) {
            BufferedImage image = loadImage(url);
            if(image != null) {
                images.add(image);
            }
        }
        return images;
    }

    public static void renderAtCenter(Graphics graphics, BufferedImage image, double x, double y) {
        graphics.drawImage(image, (int)(x - (double)image.getWidth() / 2), (int)(y - (double) image.getHeight() / 2), null);
    }

    public static BufferedImage maskWhite(BufferedImage image) {
        BufferedImage returnImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                int alpha = color & 0xFF000000;
                if (alpha != 0) {
                    returnImage.setRGB(x, y, color | 0x00FFFFFF | alpha);
                } else {
                    returnImage.setRGB(x, y, color);
                }
            }
        }

        return returnImage;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> numbers = new ArrayList<>();
//        int size = 10000;
//        for(int i = 0; i < size; i++) {
//            numbers.add(i);
//        }
//
//        //test
//        int total1 = 0;
//        long startTime1 = System.currentTimeMillis();
//        for(int i = 0; i < size; i++) {
//            total1 += i;
//        }
//        long delay1 = System.currentTimeMillis() - startTime1;
//
//
//        int total2 = 0;
//        long startTime2 = System.currentTimeMillis();
//        for(Integer i : numbers) {
//            total2 += i;
//        }
//        long delay2 = System.currentTimeMillis() - startTime2;
//
//        System.out.println("for i: " + delay1 + " " + total1);
//        System.out.println("for each: " + delay2 + " " + total2);
//    }
}
