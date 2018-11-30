package base;

import base.game.Settings;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject {
    public Background() {
        super();
        BufferedImage image = SpriteUtils.loadImage(
                "assets/images/background/0.png"
        );
        this.renderer = new SingleImageRenderer(image);
        this.position.set(0
                , Settings.SCREEN_HEIGHT - image.getHeight());
        this.velocity.set(0, 10);
        this.anchor.set(0, 0);
    }

    @Override
    public void run() {
        super.run();
        if(this.position.y >= 0) {
            this.velocity.set(0, 0);
        }
    }
}
