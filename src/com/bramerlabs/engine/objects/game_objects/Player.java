package com.bramerlabs.engine.objects.game_objects;

import com.bramerlabs.engine.math.Vector3f;
import com.bramerlabs.engine.io.window.Input;
import com.bramerlabs.engine.objects.Camera;
import com.bramerlabs.engine.objects.GameObject;
import com.bramerlabs.engine.objects.Hitbox;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class Player extends Cube {

    // input class for handling key events
    private Input input;

    // camera class for handling motion
    private Camera camera;

    // the movement speed of the player
    private static final float moveSpeed = 0.1f;

    /**
     * constructor for specified existence
     * @param position - the position of the player
     * @param rotation - the rotation of the player
     * @param scale - the scale of the player
     * @param pathToTexture - the path to the player texture
     */
    public Player(Vector3f position, Vector3f rotation, Vector3f scale, String pathToTexture) {
        super(position, rotation, scale, pathToTexture);
    }

    /**
     * constructor for specified position
     * @param position - the position of the player
     * @param pathToTexture - the path to the player texture
     */
    public Player(Vector3f position, String pathToTexture) {
        super(position, pathToTexture);
    }

    /**
     * adds the input class - must be called before any update methods
     * @param input - the constructed input class
     */
    public void addInput(Input input) {
        this.input = input;
    }

    /**
     * adds the camera class - must be called before any update methods
     * @param camera - the constructed camera class
     */
    public void addCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * updates this player
     */
    public void update(ArrayList<GameObject> gameObjects) {

        // create a vector pointing in the direction of motion
        // handle motion
        float moveSpeed = Player.moveSpeed;
        if (input.isKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL)) moveSpeed *= 2;
        // create a movement vector
        Vector3f move = new Vector3f(0, 0, -moveSpeed);
        // get the horizontal angle normal to the vector [0, 1, 0]
        float theta = (float) Math.toRadians(camera.getHorizontalAngle());
        float oldX = move.getX();
        float oldZ = move.getZ();
        // create dx and dz movement based on angular rotation of the movement vector corresponding to the horizontal angle of the camera
        float dx = (float) (oldX * Math.cos(theta) - oldZ * Math.sin(theta));
        float dy;
        float dz = (float) (oldX * Math.sin(theta) + oldZ * Math.cos(theta));

        // create new temp position based on the key inputs
        Vector3f dPos = new Vector3f(0, 0, 0);
        // handle key presses
        // forward and backward
        if (input.isKeyDown(GLFW.GLFW_KEY_W)) dPos = Vector3f.add(dPos, new Vector3f(dx, 0, dz));
        if (input.isKeyDown(GLFW.GLFW_KEY_S)) dPos = Vector3f.add(dPos, new Vector3f(-dx, 0, -dz));
        // strafing
        if (input.isKeyDown(GLFW.GLFW_KEY_A)) dPos = Vector3f.add(dPos, new Vector3f(dz, 0, -dx));
        if (input.isKeyDown(GLFW.GLFW_KEY_D)) dPos = Vector3f.add(dPos, new Vector3f(-dz, 0, dx));
        // up and down
        if (input.isKeyDown(GLFW.GLFW_KEY_SPACE)) dPos = Vector3f.add(dPos, new Vector3f(0, moveSpeed, 0));
        if (input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) dPos = Vector3f.add(dPos, new Vector3f(0, -moveSpeed, 0));

        // check collision by attempting to move the player
        // create dx, dy, dz components
        dx = dPos.getX();
        dy = dPos.getY();
        dz = dPos.getZ();
        // find the min/max x, y, z
        Vector3f curMin = Vector3f.subtract(this.getPosition(), Vector3f.scale(this.getScale(), 0.5f));
        Vector3f curMax = Vector3f.add(this.getPosition(), Vector3f.scale(this.getScale(), 0.5f));
        Vector3f newPos = Vector3f.add(this.getPosition(), dPos);
        Vector3f newMin = Vector3f.subtract(newPos, Vector3f.scale(this.getScale(), 0.5f));
        Vector3f newMax = Vector3f.add(newPos, Vector3f.scale(this.getScale(), 0.5f));
        // create temporary hitboxes corresponding to each coordinate delta
        Hitbox hdx = new Hitbox(newMin.getX(), newMax.getX(), curMin.getY(), curMax.getY(), curMin.getZ(), curMax.getZ());
        Hitbox hdy = new Hitbox(curMin.getX(), curMax.getX(), newMin.getY(), newMax.getY(), curMin.getZ(), curMax.getZ());
        Hitbox hdz = new Hitbox(curMin.getX(), curMax.getX(), curMin.getY(), curMax.getY(), newMin.getZ(), newMax.getZ());

        for (GameObject o : gameObjects) {
            if (hdx.intersects(o.getHitbox())) {
                dx = 0;
            }
            if (hdy.intersects(o.getHitbox())) {
                dy = 0;
            }
            if (hdz.intersects(o.getHitbox())) {
                dz = 0;
            }
        }

        this.setPosition(Vector3f.add(getPosition(), new Vector3f(dx, dy, dz)));
    }

}
