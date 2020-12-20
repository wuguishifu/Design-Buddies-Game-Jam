package com.bramerlabs.engine.objects;

import com.bramerlabs.engine.graphics.Mesh;
import com.bramerlabs.engine.math.Vector3f;

public class GameObject {

    // object location data
    private Vector3f position, rotation, scale;

    // the mesh that this object is made of
    private Mesh mesh;

    /**
     * default constructor for specified values
     * @param mesh - the mesh that this object is made of
     * @param position - the position of this object
     * @param rotation - the rotation of this object
     * @param scale - the scale of this object
     */
    public GameObject(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    /**
     * updates the object
     */
    public void update() {

    }

    /**
     * getter method
     * @return - the position of this object
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * getter method
     * @return - the rotation of this object
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * getter method
     * @return - the scale of this object
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * getter method
     * @return - the mesh that this object is made of
     */
    public Mesh getMesh() {
        return mesh;
    }
}
