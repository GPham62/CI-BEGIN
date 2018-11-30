package base.enemy;

import base.GameObject;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyExplosion extends GameObject {
    public EnemyExplosion() {
        super();
        this.createAnimation();
    }

    private void createAnimation() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
            "assets/images/enemies/explosion/0.png",
                "assets/images/enemies/explosion/1.png",
                "assets/images/enemies/explosion/2.png",
                "assets/images/enemies/explosion/3.png",
                "assets/images/enemies/explosion/4.png",
                "assets/images/enemies/explosion/5.png",
                "assets/images/enemies/explosion/6.png"
        );
        this.renderer = new AnimationRenderer(images
                , 5, true);
    }
}
