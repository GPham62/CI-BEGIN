package base.player;

import base.FrameCounter;
import base.action.Action;
import base.game.GameCanvas;
import base.GameObject;
import base.KeyEventPress;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    Action action;

    public Player() {
        super();
        this.createRenderer();
        this.position.set(200, 300);
        this.action = new ActionFire(4);
    }

    private void createRenderer() {
        //ArrayList<BufferedImage> images
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/1.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/2.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/3.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/4.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/5.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/6.png"));
        //AnimationRenderer(images)
        this.renderer = new AnimationRenderer(images);
    }

    @Override
    public void run() {
        this.move(); //change velocity
        this.action.run(this);
        super.run(); //this.position.addThis(this.velocity)
    }

    private void move() {
        int vx = 0;
        int vy = 0;
        if(KeyEventPress.isUpPress) {
            vy -= 1;
        }
        if(KeyEventPress.isDownPress) {
            vy += 1;
        }
        if(KeyEventPress.isLeftPress) {
            vx -= 1;
        }
        if(KeyEventPress.isRightPress) {
            vx += 1;
        }
        this.velocity.set(vx, vy);
    }
}
