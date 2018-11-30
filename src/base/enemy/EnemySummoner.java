package base.enemy;

import base.FrameCounter;
import base.GameObject;
import base.action.Action;
import base.action.ActionRepeat;
import base.action.ActionSequence;
import base.action.ActionWait;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxRenderer;

import java.awt.*;

public class EnemySummoner extends GameObject implements Physics {
    BoxCollider boxCollider;
    FrameCounter summonCounter;
    Action action;
    public EnemySummoner() {
        this.boxCollider = new BoxCollider(this.position
                , 40, 100);
        this.renderer = new BoxRenderer(this.boxCollider
                , Color.WHITE, true);
        this.position.set(20, 100);
        this.summonCounter = new FrameCounter(30);
        this.action = this.createAction();
    }

    private Action createAction() {
        Action actionSummoner = new Action() {
            boolean isDone;

            @Override
            public boolean run(GameObject master) {
                EnemySummoner enemySummoner = (EnemySummoner) master;
                enemySummoner.summonEnemy();
                this.isDone = true;
                return this.isDone;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        Action actionWait = new ActionWait(100);
        Action actionSequence = new ActionSequence(actionWait, actionSummoner);
        Action actionRepeat = new ActionRepeat(actionSequence);
        return actionRepeat;
    }

    @Override
    public void run() {
        super.run();
//        if(this.summonCounter.run()) {
//            this.summonEnemy();
//            this.summonCounter.reset();
//        }
        this.action.run(this);
    }

    private void summonEnemy() {
        Enemy enemy = GameObject.recycle(Enemy.class);
        enemy.position.set(this.position.add(20, 0));
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
