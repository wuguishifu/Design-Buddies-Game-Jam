package com.bramerlabs.engine;

import java.util.Objects;

public class Vector3f {

    // the x, y, z components of this vector
    private float x, y, z;

    /**
     * default constructor for three specified values
     * @param x - the x component of this vector
     * @param y - the y component of this vector
     * @param z - the z component of this vector
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * constructor for one specified value
     * @param value - the value for all 3 components to be set to
     */
    public Vector3f(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    /**
     * sets the components of this vector
     * @param x - the new x component
     * @param y - the new y component
     * @param z - the new z component
     */
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * sets a specific component of this vector
     * @param c - which component to set
     * @param val - the value to be set
     */
    public void set(int c, float val) {
        switch (c) {
            case 0 : this.x = val; break;
            case 1 : this.y = val; break;
            case 2 : this.z = val; break;
        }
    }

    /**
     * gets a specific value of this vector
     * @param c - which component to get
     */
    public float get(int c) {
        switch (c) {
            case 0 : return this.x;
            case 1 : return this.y;
            case 2 : return this.z;
        }
        return -1;
    }

    /**
     * adds values to each component of this vector
     * @param dx - the value to be added to the x component
     * @param dy - the value to be added to the y component
     * @param dz - the value to be added to the z component
     * @return - this vector
     */
    public Vector3f add(float dx, float dy, float dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
        return this;
    }

    /**
     * scales the entire vector
     * @param val - the value for this vector to be scaled by
     * @return - this vector
     */
    public Vector3f scale(float val) {
        this.x *= val;
        this.y *= val;
        this.z *= val;
        return this;
    }

    /**
     * adds two vectors together
     * @param u - vector 1
     * @param v - vector 2
     * @return - a new vector u + v
     */
    public static Vector3f add(Vector3f v, Vector3f u) {
        return new Vector3f(v.x + u.x, v.y + u.y, v.z + u.z);
    }

    /**
     * subtracts two vectors
     * @param u - vector 1
     * @param v - vector 2
     * @return - a new vector u - v
     */
    public static Vector3f subtract(Vector3f u, Vector3f v) {
        return new Vector3f(u.x - v.x, u.y - v.y, u.z - v.z);
    }

    /**
     * element-wise multiplication of two vectors
     * @param u - vector 1
     * @param v - vector 2
     * @return - a new vector representing the element-wise multiplication of u, v
     */
    public static Vector3f multiply(Vector3f u, Vector3f v) {
        return new Vector3f(u.x * v.x, u.y * v.y, u.z * v.z);
    }

    /**
     * element-wise division of two vectors
     * @param u - vector 1
     * @param v - vector 2
     * @return - a new vector representing the element-wise division of u, v
     */
    public static Vector3f divide(Vector3f u, Vector3f v) {
        return new Vector3f(u.x / v.x, u.y / v.y, u.z / v.z);
    }

    /**
     * computes the dot product of two vectors
     * @param u - vector 1
     * @param v - vector 2
     * @return - the dot product (u dot v)
     */
    public static float dotProduct(Vector3f u, Vector3f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    /**
     * determines the length of a vector
     * @param v - the vector
     * @return - the length
     */
    public static float length(Vector3f v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    /**
     * normalizes a vector
     * @param v - the vector
     * @return - the vector as a normal vector
     */
    public static Vector3f normalize(Vector3f v) {
        return Vector3f.divide(v, new Vector3f(length(v)));
    }

    /**
     * normalizes a vector to a certain length val
     * @param v - the vector
     * @param val - the length to be normalized to
     * @return - the new vector
     */
    public static Vector3f normalize(Vector3f v, float val) {
        return (Vector3f.divide(v, new Vector3f(length(v)))).scale(val);
    }

    /**
     * getter method
     * @return - the x component of this vector
     */
    public float getX() {
        return this.x;
    }

    /**
     * getter method
     * @return - the y component of this vector
     */
    public float getY() {
        return this.y;
    }

    /**
     * getter method
     * @return - the z component of this vector
     */
    public float getZ() {
        return this.z;
    }

    /**
     * getter method
     * @return - the i component of this vector
     */
    public float getI() {
        return this.x;
    }

    /**
     * getter method
     * @return - the j component of this vector
     */
    public float getJ() {
        return this.y;
    }

    /**
     * getter method
     * @return - the k component of this vector
     */
    public float getK() {
        return this.z;
    }

    /**
     * sets the x component
     * @param val - the value
     */
    public void setX(float val) {
        this.x = val;
    }

    /**
     * sets the y component
     * @param val - the value
     */
    public void setY(float val) {
        this.y = val;
    }

    /**
     * sets the z component
     * @param val - the value
     */
    public void setZ(float val) {
        this.z = val;
    }

    /**
     * sets the i component
     * @param val - the value
     */
    public void setI(float val) {
        this.x = val;
    }

    /**
     * sets the j component
     * @param val - the value
     */
    public void setJ(float val) {
        this.y = val;
    }

    /**
     * sets the k component
     * @param val - the value
     */
    public void setK(float val) {
        this.z = val;
    }

    /**
     * determines if two vectors are exactly identical
     * @param o - the other vector
     * @return - true if this and o are both vectors that are exactly equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3f)) return false;
        Vector3f other = (Vector3f) o;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    /**
     * determines if two vectors are nearly identical
     * @param o - the other vector
     * @param epsilon - the max error
     * @return - true if this and o are both vectors and the absolute error for each components is less than epsilon
     */
    public boolean equals(Object o, float epsilon) {
        if (this == o) return true;
        if (!(o instanceof Vector3f)) return false;
        Vector3f other = (Vector3f) o;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(this.get(i) - other.get(i)) > epsilon) {
                return false;
            }
        }
        return true;
    }

    /**
     * creates a hashcode of this vector
     * @return - the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
