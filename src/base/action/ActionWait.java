package base.action;

import base.FrameCounter;
import base.GameObject;

public class ActionWait implements Action{
    FrameCounter frameCounter;

    public ActionWait(int maxFrameCount){
        this.frameCounter = new FrameCounter(maxFrameCount);
    }

    @Override
    public boolean run(GameObject master) {
        if (this.frameCounter.run()){
            return true;
        }
        else return false;
    }

    @Override
    public void reset() {
        this.frameCounter.reset();
    }
}
