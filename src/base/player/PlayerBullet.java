package base.player;

import base.GameObject;
import base.enemy.Enemy;
import base.game.Settings;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Physics {
    BoxCollider boxCollider;
    int damage;
    public PlayerBullet() {
        super();
        this.velocity.set(0, -5);
        this.boxCollider = new BoxCollider(this.position, 24, 24);
        this.damage = 1;
    }

    @Override
    public void run() {
        super.run();
        this.destroyIfNeeded();
        this.hitEnemy();
    }

    private void hitEnemy() {
        Enemy enemy = GameObject.intersects(Enemy.class, this.boxCollider);
        if(enemy != null) {
            enemy.takeDamage(this.damage);
            this.destroy();
        }
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
