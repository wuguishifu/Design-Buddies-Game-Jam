package com.bramerlabs.engine.objects;

import com.bramerlabs.engine.io.window.Input;
import com.bramerlabs.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {

    // the position and rotation of the camera
    private Vector3f position, rotation;

    // the input object for handling callbacks
    private Input input;

    // mouse motion variables
    private final float moveSpeed = 0.05f, mouseSensitivity = 0.1f;
    private float rotateSpeed = 0.02f * 360;

    // the position of the mouse
    private double oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

    /**
     * default constructor for specified position, rotation, and input object
     * @param position - the position of the camera object
     * @param rotation - the rotation of the camera object
     * @param input - the callback input object
     */
    public Camera(Vector3f position, Vector3f rotation, Input input) {
        this.position = position;
        this.rotation = rotation;
        this.input = input;
    }

    /**
     * updates the camera based on keyboard and mouse input
     */
    public void update() {
        newMouseX = input.getMouseX();
        newMouseY = input.getMouseY();

        float x = (float)Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float)Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;

        // handle the WASD keys
        if (input.isKeyDown(GLFW.GLFW_KEY_A)) position = Vector3f.add(position, new Vector3f(-z, 0,  x));
        if (input.isKeyDown(GLFW.GLFW_KEY_D)) position = Vector3f.add(position, new Vector3f( z, 0, -x));
        if (input.isKeyDown(GLFW.GLFW_KEY_W)) position = Vector3f.add(position, new Vector3f(-x, 0, -z));
        if (input.isKeyDown(GLFW.GLFW_KEY_S)) position = Vector3f.add(position, new Vector3f( x, 0,  z));

        // handle going up and down
        if (input.isKeyDown(GLFW.GLFW_KEY_SPACE)) position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
        if (input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));

        // handle mouse motion
        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;

        // rotate according to the mouse motion
        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSensitivity, -dx * mouseSensitivity, 0)); //dx, dy must be flipped and inverted
    }

    /**
     * getter method
     * @return - the position of this camera
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * getter method
     * @return - the rotation of this camera
     */
    public Vector3f getRotation() {
        return rotation;
    }
}
