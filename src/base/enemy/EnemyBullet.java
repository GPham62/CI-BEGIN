package base.enemy;

import base.GameObject;
import base.game.Settings;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxRenderer;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject implements Physics {
    BoxCollider boxCollider;

    public EnemyBullet() {
        super();
        this.boxCollider = new BoxCollider(this.position
                , 16, 16);
//        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
//        this.renderer = new SingleImageRenderer(image);
        this.renderer = new BoxRenderer(this.boxCollider
                , Color.green, true);
        this.velocity.set(0, 5);
    }

    @Override
    public void run() {
        super.run();
        this.destroyIfNeeded();
    }

    private void destroyIfNeeded() {
        if(this.position.y < -20 || this.position.y >= Settings.SCREEN_HEIGHT
                || this.position.x < 0 || this.position.x >= Settings.BACKGROUND_WIDTH) {
            this.destroy();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
