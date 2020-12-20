package com.bramerlabs.engine.graphics;

import com.bramerlabs.engine.io.window.Window;
import com.bramerlabs.engine.math.Matrix4f;
import com.bramerlabs.engine.objects.Camera;
import com.bramerlabs.engine.objects.GameObject;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

    // the amount of attributes in this renderer
    private int numAttribs;

    // the shader program for this renderer to use
    private Shader shader;

    // the window to render to
    private Window window;

    /**
     * default constructor for specified window and shader
     * @param window - the window to render to
     * @param shader - the shader to use
     */
    public Renderer(Window window, Shader shader) {
        this.shader = shader;
        this.window = window;
        numAttribs = shader.getNumAttribs();
    }

    /**
     * renders an object
     * @param object - the object to be used
     * @param camera - the view camera object
     */
    public void renderMesh(GameObject object, Camera camera) {
        Mesh mesh = object.getMesh();

        glBindVertexArray(mesh.getVAO());
        enableVertexAttribArrays();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, mesh.getMaterial().getTextureID());
        shader.bind(); //bind the shader
        shader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale())); //mvp
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        shader.setUniform("projection", window.getProjectionMatrix());
        draw(mesh);
        shader.unbind(); //unbind the shader
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        disableVertexAttribArrays();
        glBindVertexArray(0);
    }

    /**
     * draws a mesh
     * @param mesh - the mesh to be drawn
     */
    private void draw(Mesh mesh) {
        glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
    }

    /**
     * enables the vertex attribute array for OpenGL
     */
    private void enableVertexAttribArrays() {
        for (int i = 0; i < numAttribs; i++) {
            glEnableVertexAttribArray(i);
        }
    }

    /**
     * disables the vertex attribute array for OpenGL
     */
    private void disableVertexAttribArrays() {
        for (int i = 0; i < numAttribs; i++) {
            glDisableVertexAttribArray(i);
        }
    }

}
