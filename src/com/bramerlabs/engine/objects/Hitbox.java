package com.bramerlabs.engine.objects;

public class Hitbox {

    // the positions of opposite maxima corners
    public float xMin, xMax, yMin, yMax, zMin, zMax;

    /**
     * default constructor for specified maxima corners
     * @param xMin - the minimum x value of this hitbox
     * @param xMax - the maximum x value of this hitbox
     * @param yMin - the minimum y value of this hitbox
     * @param yMax - the maximum y value of this hitbox
     */
    public Hitbox(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;
    }

    /**
     * checks if two hitboxes are intersecting
     * @param other - the other hitbox
     * @return - true if the two hitboxes collide
     */
    public boolean intersects(Hitbox other) {
        if (this.xMin > other.xMax || this.xMax < other.xMin) {
            return false;
        }
        if (this.yMin > other.yMax || this.yMax < other.yMin) {
            return false;
        }
        return !(this.zMin > other.zMax) && !(this.zMax < other.zMin);
    }
}
