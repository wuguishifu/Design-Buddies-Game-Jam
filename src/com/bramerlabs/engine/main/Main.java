package com.bramerlabs.engine.main;

import com.bramerlabs.engine.graphics.*;
import com.bramerlabs.engine.io.window.Input;
import com.bramerlabs.engine.io.window.Window;
import com.bramerlabs.engine.math.Vector2f;
import com.bramerlabs.engine.math.Vector3f;
import com.bramerlabs.engine.objects.Camera;
import com.bramerlabs.engine.objects.GameObject;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {

    private Window window;

    private Shader shader;

    private Renderer renderer;

    private Input input = new Input();

    public Mesh mesh = new Mesh(new Vertex[] {
            // front face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),

            // back face
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 0.0f)),

            // right face
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),

            // left face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 0.0f)),

            // top face
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f), new Vector2f(1.0f, 0.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f), new Vector2f(1.0f, 0.0f)),

            // bottom face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f), new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f), new Vector2f(0.0f, 1.0f)),


    }, new int[] {
            // front face
            0, 1, 2,
            2, 3, 0,

            // back face
            5, 4, 7,
            5, 7, 6,

            // right face
            1, 5, 6,
            6, 2, 1,

            // left face
            14, 12, 13,
            14, 13, 15,

            // top face
            17, 16, 18,
            17, 18, 19,

            // bottom face
            20, 22, 23,
            20, 23, 21,

    }, new Material("/textures/go-team.png"));

    public GameObject object = new GameObject(mesh, new Vector3f(0, 0, 0f), new Vector3f(0, 0, 0), new Vector3f(1f, 1f, 1f));

    public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0), input);

    public static void main(String[] args) {
        new Main().start();
    }

    public void run() {
        init();
        while (!window.shouldClose()) {
            update();
            render();
            if (input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.mouseState(true);
        }
        close();
    }

    private void init() {
        //create the openJL window
        window = new Window(input);
        window.create();

        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        mesh.create();
        renderer = new Renderer(window, shader);

        shader.create();
    }

    private void close() {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    public void start() {
        Thread main = new Thread(this, "Terra Nova 0.3");
        main.start();
    }

    private void update() {
        window.update();
        camera.update();
    }

    private void render() {
        renderer.renderMesh(object, camera);
        window.swapBuffers();
    }
}
