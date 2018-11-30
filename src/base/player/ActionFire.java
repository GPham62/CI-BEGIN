package base.player;

import base.FrameCounter;
import base.GameObject;
import base.KeyEventPress;
import base.Vector2D;
import base.action.Action;

import java.util.Vector;

public class ActionFire implements Action {
    FrameCounter fireCounter;
    int bulletCount;
    public ActionFire(int bulletCount) {
        this.fireCounter = new FrameCounter(20);
        this.bulletCount = bulletCount;
    }

    @Override
    public boolean run(GameObject master) {
        if(this.fireCounter.run() && KeyEventPress.isFirePress) {
            this.fire(master);
        }
        return false;
    }

    private void fire(GameObject master) {
        for (int i = 0; i < this.bulletCount; i ++){
            Vector2D velocity = calculateVelocity(i, this.bulletCount);
            PlayerBullet bullet = GameObject.recycle(PlayerBulletType1.class);
            bullet.position.set(master.position);
            bullet.velocity.set(velocity);
        }

        this.fireCounter.reset();
    }

    private Vector2D calculateVelocity(int i, int bulletCount) {
        int velocityLength = 5;
        double startAngle = 3 * Math.PI / 4;
        double step = Math.PI / 2 / (bulletCount-1);
        if (bulletCount == 1){
            return new Vector2D(0, -velocityLength);
        }
        Vector2D velocity = new Vector2D(0, -velocityLength);
        velocity.setAngle(startAngle + step * i);
        return velocity;
    }

    @Override
    public void reset() {
        this.fireCounter.reset();
    }
}
