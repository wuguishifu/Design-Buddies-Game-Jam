package com.bramerlabs.engine.objects;

import com.bramerlabs.engine.graphics.Mesh;
import com.bramerlabs.engine.math.Vector3f;

public class GameObject {

    private Vector3f position, rotation, scale;
    private Mesh mesh;

    public GameObject(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public void update() {

    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Mesh getMesh() {
        return mesh;
    }
}
