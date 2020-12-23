package com.bramerlabs.engine.main;

import com.bramerlabs.engine.graphics.*;
import com.bramerlabs.engine.io.window.Input;
import com.bramerlabs.engine.io.window.Window;
import com.bramerlabs.engine.math.Vector3f;
import com.bramerlabs.engine.objects.Camera;
import com.bramerlabs.engine.objects.Cube;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {

    private Window window;

    private Shader shader;

    private Renderer renderer;

    private Input input = new Input();

    public Cube cube = new Cube(new Vector3f(0, 0, 0), new Vector3f(0, 45, 0), new Vector3f(1f, 1f, 1f), "/textures/3ttest.png");
    public Cube cube1 = new Cube(new Vector3f(-5f, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1f, 1f, 1f), "/textures/test.png");

    public Camera camera = new Camera(new Vector3f(0, 0, 2), new Vector3f(0, 0, 0), input);

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
        cube.createMesh();
        cube1.createMesh();
        renderer = new Renderer(window, shader);

        shader.create();
    }

    private void close() {
        window.destroy();
        cube.destroy();
        cube1.destroy();
        shader.destroy();
    }

    public void start() {
        Thread main = new Thread(this, "Game Buddies Game Jam!");
        main.start();
    }

    private void update() {
        window.update();
        camera.update();
    }

    private void render() {
        renderer.renderMesh(cube, camera);
        renderer.renderMesh(cube1, camera);
        window.swapBuffers();
    }
}
