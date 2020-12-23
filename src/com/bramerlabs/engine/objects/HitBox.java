package com.bramerlabs.engine.objects;

public class HitBox {

    // the positions of opposite maxima corners
    public float xMin, xMax, yMin, yMax;

    /**
     * default constructor for specified maxima corners
     * @param xMin - the minimum x value of this hitbox
     * @param xMax - the maximum x value of this hitbox
     * @param yMin - the minimum y value of this hitbox
     * @param yMax - the maximum y value of this hitbox
     */
    public HitBox(float xMin, float xMax, float yMin, float yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }



}
