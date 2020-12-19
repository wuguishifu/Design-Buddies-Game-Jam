package com.bramerlabs.engine.math;

import java.util.Arrays;

public class Matrix4f {

    // the square dimension of this matrix
    public static final int SIZE = 4;

    // the elements of this matrix
    private float[] elements = new float[SIZE * SIZE];

    /**
     * default constructor - makes a 0 matrix
     */
    public Matrix4f() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.set(i, j, 0);
            }
        }
    }

    /**
     * creates an identity matrix
     * @return - identity matrix
     */
    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < SIZE; i++) {
            result.elements[i * SIZE + i] = 1;
        }
        return result;
    }

    /**
     * create a translation matrix out of a vector
     * @param translate - the translation vector
     * @return - the created translation matrix
     */
    public static Matrix4f translate(Vector3f translate) {
        Matrix4f result = Matrix4f.identity();

        result.set(3, 0, translate.getX());
        result.set(3, 1, translate.getY());
        result.set(3, 2, translate.getZ());

        return result;
    }

    /**
     * creates a proper rotation matrix for a specified angle around a specified axis
     * @param angle - the angle by which the matrix should be rotated
     * @param axis - the axis around which the matrix should be rotated
     * @return - the new rotation matrix representing this rotation
     */
    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f result = Matrix4f.identity();

        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float C = 1 - cos;

        float ux = axis.getX();
        float uy = axis.getY();
        float uz = axis.getZ();

        result.set(0, 0, cos + ux*ux*C);
        result.set(0, 1, ux*uy*C - uz*sin);
        result.set(0, 2, ux*uz*C + uy*sin);

        result.set(1, 0, uy*ux*C + uz*sin);
        result.set(1, 1, cos + uy*uy*C);
        result.set(1, 2, uy*uz*C - ux*sin);

        result.set(2, 0, uz*ux*C - uy*sin);
        result.set(2, 1, uz*uy*C + ux*sin);
        result.set(2, 2, cos + uz*uz*C);

        return result;
    }

    /**
     * creates a scale matrix by some group of scalars
     * @param scalar - a vector with all the scalars to scale the matrix by
     * @return - a matrix representing the scale operations specified by scalar
     */
    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f result = Matrix4f.identity();

        result.set(0, 0, scalar.getX());
        result.set(1, 1, scalar.getY());
        result.set(2, 2, scalar.getZ());

        return result;
    }

    /**
     * creates a total transformation matrix consisting of a positional translation, rotation, and scale.
     * @param position - the position to translate to
     * @param rotation - the rotations to be performed along the x, y, z axes - held as [theta, phi, gamma]
     * @param scale - the scale vector
     * @return - a matrix representing all of these operations
     */
    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {

        // create the translation matrix
        Matrix4f translationMatrix = Matrix4f.translate(position);

        // create the 3 rotation matrices
        Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));

        // create the scale matrix
        Matrix4f scaleMatrix = Matrix4f.scale(scale);

        // create one rotation matrix from the 3 component rotation matrices
        Matrix4f rotationMatrix = Matrix4f.multiply(rotXMatrix, Matrix4f.multiply(rotYMatrix, rotZMatrix));

        // create one matrix representing the sum of each operation
        return Matrix4f.multiply(translationMatrix, Matrix4f.multiply(rotationMatrix, scaleMatrix));
    }

    /**
     * creates a projection matrix based off of a certain perspective
     * @param fov - the field of view of the perspective
     * @param aspect - the aspect ratio of the perspective
     * @param near - the nearest viewable length
     * @param far - the farthest viewable length
     * @return - the projection matrix created by the specified values
     */
    public static Matrix4f projection(float fov, float aspect, float near, float far) {
        Matrix4f result = Matrix4f.identity();

        float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(0, 0, 1.0f/(aspect * tanFOV));
        result.set(1, 1, 1.0f/(tanFOV));
        result.set(2, 2, -((far + near)/range));
        result.set(2, 3, -1.0f);
        result.set(3, 2, -((2*far*near)/range));
        result.set(3, 3, 0.0f);

        return result;
    }

    /**
     * create a view matrix for a specified position and rotation
     * @param position - the position of the viewer
     * @param rotation - the rotation of the viewer
     * @return - the view matrix
     */
    public static Matrix4f view(Vector3f position, Vector3f rotation) {
        // create an inverse position vector
        Vector3f negative = new Vector3f(-position.getX(), -position.getY(), -position.getZ());

        // create a translation matrix according to the negative position vector
        Matrix4f translationMatrix = Matrix4f.translate(negative);

        // create a rotation matrix
        // create x, y, z components of rotation matrix
        Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
        // combine the component matrices into one rotation matrix
        Matrix4f rotationMatrix = multiply(rotXMatrix, multiply(rotYMatrix, rotZMatrix));

        return Matrix4f.multiply(translationMatrix, rotationMatrix);
    }

    /**
     * multiplies two matrices together
     * @param A - matrix 1
     * @param B - matrix 2
     * @return - a new Matrix, A x B
     */
    public static Matrix4f multiply(Matrix4f A, Matrix4f B) {
        Matrix4f result = Matrix4f.identity();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.set(i, j, A.get(i, 0) * B.get(0, j) +
                        A.get(i, 1) * B.get(1, j) +
                        A.get(i, 2) * B.get(2, j) +
                        A.get(i, 3) * B.get(3, j));
            }
        }

        return result;
    }

    /**
     * sets a value of the matrix
     * @param x - the column (x, y) Cartesian coordinates
     * @param y - the row
     * @param value - the value to be set
     */
    public void set(int x, int y, float value) {
        elements[y * SIZE + x] = value;
    }

    /**
     * gets the value at a certain (x, y) position
     * @param x - the column
     * @param y - the row
     * @return - the value at (x, y) starting at the top left
     */
    public float get(int x, int y) {
        return elements[y * SIZE + x];
    }

    /**
     * determines if two matrices are exactly identical
     * @param o - the other object
     * @return - true if both objects are matrices that are exactly equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix4f)) return false;
        Matrix4f matrix4f = (Matrix4f) o;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix4f.elements[j * SIZE + i] != this.elements[j * SIZE + i]) { // check that all elements of this matrix match all the elements of the other matrix
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * determines if two matrices are almost identical, with an error maximum of epsilon
     * @param o - the other object
     * @param epsilon - the max error
     * @return - true if both objects are matrices that are nearly equal
     */
    public boolean equals(Object o, float epsilon) {
        if (this == o) return true;
        if (!(o instanceof Matrix4f)) return false;
        Matrix4f other = (Matrix4f) o;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Math.abs(this.elements[j * SIZE + i] - other.elements[j * SIZE + i]) > epsilon) { // check if the absolute error between both matrix values is less than epsilon
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * generates a hashcode based on the elements of this matrix
     * @return - a unique hashcode
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}