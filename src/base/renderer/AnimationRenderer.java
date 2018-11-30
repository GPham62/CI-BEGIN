package base.renderer;

import base.FrameCounter;
import base.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimationRenderer extends Renderer {
    ArrayList<BufferedImage> images;
    int currentImageIndex;
    FrameCounter nextImageCounter;
    boolean isOnce;

    public AnimationRenderer(ArrayList<BufferedImage> images) {
        this(images, 10, false);
    }

    public AnimationRenderer(ArrayList<BufferedImage> images
        , int maxFrameCount) {
        this(images, maxFrameCount, false);
    }

    public AnimationRenderer(ArrayList<BufferedImage> images
        , int maxFrameCount, boolean isOnce) {
        this.images = images;
        this.currentImageIndex = 0;
        this.nextImageCounter = new FrameCounter(maxFrameCount);
        this.isOnce = isOnce;
    }

    @Override
    public void render(Graphics g, GameObject master) {
        BufferedImage currentImage = this.images.get(currentImageIndex);
        int x = (int)(master.position.x
                - master.anchor.x * currentImage.getWidth());
        int y = (int)(master.position.y
                - master.anchor.y * currentImage.getHeight());
        g.drawImage(currentImage, x, y, null);
        if(this.nextImageCounter.run()) {
            this.currentImageIndex++;
            if(this.currentImageIndex >= this.images.size()
                && this.isOnce) {
                master.destroy();
            }
            if(this.currentImageIndex >= this.images.size()) {
                this.currentImageIndex = 0;
            }
            this.nextImageCounter.reset();
        }
    }
}
