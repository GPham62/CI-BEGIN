package base;

import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    //static
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    public static Player createPlayer() {
        Player player = new Player();
        gameObjects.add(player);
        return player;
    }

    //createGameObject generic
    public static <E extends GameObject> E create(Class<E> clazz) {
        try {
            E gameObject = clazz.newInstance();// = new E()
            gameObjects.add(gameObject);
            return gameObject;
        } catch(Exception ex) {
            return null;
        }
    }

    public static <E extends GameObject> E recycle(Class<E> clazz) {
        //clazz == Background.class
        //E == Background
        for(GameObject gameObject : gameObjects) {
            if(isValidRecycle(gameObject, clazz)) {
                gameObject.reset();
                return (E)gameObject;
            }
        }
        E newGameObject = create(clazz);
        return newGameObject;
    }

    /**
     * tra ve 1 gameObject co kieu clazz(E) intersect voi gameObject
     * truyen vao
     * @return
     */
    public static <E extends GameObject> E intersects(Class<E> clazz, BoxCollider boxCollider) {
        //gameObjects > result(result instanceof E, result instanceof Physics,
        // result.getBoxCollider.intersects(boxCollider))
        int size = gameObjects.size();
        for(int i = 0; i < size; i++) {
            GameObject gameObject = gameObjects.get(i);
            if(gameObject.isActive
                && gameObject.getClass().isAssignableFrom(clazz)
                && gameObject instanceof Physics) {
                Physics gameObjectPhysics = (Physics)gameObject;
                if(gameObjectPhysics.getBoxCollider().intersects(boxCollider)) {
                    return (E)gameObject;
                }
            }
        }
        return null;
    }

    private static boolean isValidRecycle(GameObject gameObject, Class clazz) {
        return !gameObject.isActive
                && gameObject.getClass().isAssignableFrom(clazz);
    }

    //thuoc tinh
    //image
    public Vector2D position;
    public Renderer renderer;
    public boolean isActive;
    public Vector2D velocity;
    public Vector2D anchor;

    //phuong thuc
    public GameObject() {
        this.position = new Vector2D();
        this.isActive = true;
        this.velocity = new Vector2D();
        this.anchor = new Vector2D(0.5f, 0.5f);
        //...
    }

    public void destroy() {
        this.isActive = false;
    }

    public void reset() {
        this.isActive = true;
    }

    //logic
    public void run() {
        this.position.addThis(this.velocity);
    }

    //hien thi
    public void render(Graphics g) {
        //draw image
        if(this.renderer != null) {
            this.renderer.render(g, this);
        }
    }
}
