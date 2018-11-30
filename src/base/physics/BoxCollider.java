package base.physics;

import base.Vector2D;

import javax.swing.*;

public class BoxCollider {
    public Vector2D masterPosition; //master == gameObject
    public int width;
    public int height;

    public BoxCollider(Vector2D masterPosition, int width, int height) {
        this.masterPosition = masterPosition;
        this.width = width;
        this.height = height;
    }

    public int top() {
        return (int)this.masterPosition.y;
    }

    public int bottom() {
        return this.top() + this.height;
    }

    public int left() {
        return (int)this.masterPosition.x;
    }

    public int right() {
        return this.left() + this.width;
    }

    public boolean intersects(BoxCollider other) {
        boolean intersectX = this.left() <= other.right()
                && other.left() <= this.right();
        boolean intersectY = this.top() <= other.bottom()
                && other.top() <= this.bottom();
        return intersectX && intersectY;
    }
}
