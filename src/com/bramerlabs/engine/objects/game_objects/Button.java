package com.bramerlabs.engine.objects.game_objects;

import com.bramerlabs.engine.graphics.Mesh;
import com.bramerlabs.engine.math.Vector3f;
import com.bramerlabs.engine.objects.GameObject;

public class Button extends GameObject {
    /**
     * default constructor for specified values
     *
     * @param mesh     - the mesh that this object is made of
     * @param position - the position of this object
     * @param rotation - the rotation of this object
     * @param scale    - the scale of this object
     */
    public Button(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
        super(mesh, position, rotation, scale);
    }
}
