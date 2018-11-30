package base.enemy;

import base.FrameCounter;
import base.GameObject;
import base.action.Action;
import base.action.ActionParallel;
import base.game.GameCanvas;
import base.game.Settings;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject implements Physics {
    FrameCounter fireCounter;
    BoxCollider boxCollider;
    int hp;
    boolean immune;
    FrameCounter immuneCouter;
    Action action;

    public Enemy() {
        super();
        this.position.set(50,50);
        this.velocity.set(0, 3);
        this.createRenderer();
        this.fireCounter = new FrameCounter(20);
        this.boxCollider = new BoxCollider(this.position, 28, 28);
        this.hp = 3;
        this.immune = false;
        this.immuneCouter = new FrameCounter(60);
        this.action = this.createAction();
    }

    private Action createAction() {
        Action actionRun = new Action() {
            @Override
            public boolean run(GameObject master) {
                master.velocity.set(1,0);
                if (master.position.x > Settings.SCREEN_WIDTH * 3/4){
                    return true;
                }
                else return false;
            }

            @Override
            public void reset() {

            }
        };
        Action actionFire = new Action() {
            @Override
            public boolean run(GameObject master) {
                Enemy enemy = (Enemy) master;
                enemy.fire();
                return false;
            }

            @Override
            public void reset() {
                fireCounter.reset();
            }
        };
        Action actionParallel = new ActionParallel(actionRun, actionFire);
        return actionParallel;
    }

    private void createRenderer() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png"
        );
        this.renderer = new AnimationRenderer(images);
    }

    @Override
    public void run() {
        super.run();
//        if(this.position.y >= 300) {
//            this.velocity.set(0, 0);
//        }
//        this.fire();
        this.action.run(this);
    }

    //TODO: replace frameCounter
    private void fire() {
        if (this.fireCounter.run()) {
            EnemyBullet enemyBullet = GameObject.recycle(EnemyBullet.class);
            enemyBullet.position.set(this.position);
            this.fireCounter.reset();
        }
    }

    public void takeDamage(int damage) {
        if(this.immune)
            return;
        this.hp -= damage;
        if(this.hp <= 0) {
            this.hp = 0;
            this.destroy();
        } else {
            this.immune = true;
            this.immuneCouter.reset();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EnemyExplosion explosion = GameObject.recycle(EnemyExplosion.class);
        explosion.position.set(this.position);
    }

    @Override
    public void reset() {
        super.reset();
        this.velocity.set(0, 3);
        this.immune = false;
        this.immuneCouter.reset();
        this.hp = 3;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void render(Graphics g) {
        if(this.immune) {
            //TODO
            if(this.immuneCouter.run()) {
                this.immune = false;
            }
            if(this.immuneCouter.count % 4 == 0) {
                super.render(g);
            }
        } else {
            super.render(g);
        }
    }
}
