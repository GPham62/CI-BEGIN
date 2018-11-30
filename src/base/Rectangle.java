package base;

public class Rectangle {
    Vector2D position;
    int width;
    int height;

    public Rectangle() {
        this(new Vector2D(), 5, 5);
    }

    public Rectangle(Vector2D position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int top() {
        return (int)this.position.y;
    }

    public int bottom() {
        return this.top() + this.height;
    }

    public int left() {
        return (int)this.position.x;
    }

    public int right() {
        return this.left() + this.width;
    }

    public boolean intersects(Rectangle other) {
//        boolean intersectX = (other.top() >= this.top() && other.top() <= this.bottom())
//                || (other.bottom() >= this.top() && other.bottom() <= this.bottom());
//        boolean intersectY = (other.left() >= this.left() && other.left() <= this.right())
//                || (other.right() >= this.left() && other.right() <= this.right());
        boolean intersectX = this.left() <= other.right()
                && other.left() <= this.right();
        boolean intersectY = this.top() <= other.bottom()
                && other.top() <= this.bottom();
        return intersectX && intersectY;
    }

    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(new Vector2D(0, 0)
                , 10, 10);
        Rectangle rect2 = new Rectangle(new Vector2D(5, 5)
                , 10, 10);
        Rectangle rect3 = new Rectangle(new Vector2D(5, -15)
                , 10, 10);

        System.out.println(rect1.intersects(rect2)); // == true
        System.out.println(rect2.intersects(rect3)); // == false
        System.out.println(rect1.intersects(rect3)); // == false
    }
}
