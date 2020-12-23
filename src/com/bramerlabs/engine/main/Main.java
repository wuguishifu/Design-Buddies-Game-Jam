package com.bramerlabs.engine.main;

import com.bramerlabs.engine.graphics.*;
import com.bramerlabs.engine.io.window.Input;
import com.bramerlabs.engine.io.window.Window;
import com.bramerlabs.engine.math.Vector3f;
import com.bramerlabs.engine.objects.Camera;
import com.bramerlabs.engine.objects.Cube;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class Main implements Runnable {

    private Window window;

    private Shader shader;

    private Renderer renderer;

    private Input input = new Input();

    private ArrayList<Cube> cubes = new ArrayList<>();

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

    /**
     * initialize the program
     */
    private void init() {
        //create the openJL window
        window = new Window(input);
        window.create();

        // create game objects here
        String dPath = "/textures/3ttest.png";
        cubes.add(new Cube(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), "/textures/3ttest.png"));
        cubes.add(new Cube(new Vector3f(2, 0, 0), dPath));
        cubes.add(new Cube(new Vector3f(4, 0, 0), dPath));
        cubes.add(new Cube(new Vector3f(0, 2, 0), dPath));
        cubes.add(new Cube(new Vector3f(2, 2, 0), dPath));
        cubes.add(new Cube(new Vector3f(4, 2, 0), dPath));
        cubes.add(new Cube(new Vector3f(0, 4, 0), dPath));
        cubes.add(new Cube(new Vector3f(2, 4, 0), dPath));
        cubes.add(new Cube(new Vector3f(4, 4, 0), dPath));
        cubes.add(new Cube(new Vector3f(0, 0, 2), dPath));
        cubes.add(new Cube(new Vector3f(2, 0, 2), dPath));
        cubes.add(new Cube(new Vector3f(4, 0, 2), dPath));
        cubes.add(new Cube(new Vector3f(0, 2, 2), dPath));
        cubes.add(new Cube(new Vector3f(2, 2, 2), dPath));
        cubes.add(new Cube(new Vector3f(4, 2, 2), dPath));
        cubes.add(new Cube(new Vector3f(0, 4, 2), dPath));
        cubes.add(new Cube(new Vector3f(2, 4, 2), dPath));
        cubes.add(new Cube(new Vector3f(4, 4, 2), dPath));
        cubes.add(new Cube(new Vector3f(0, 0, 4), dPath));
        cubes.add(new Cube(new Vector3f(2, 0, 4), dPath));
        cubes.add(new Cube(new Vector3f(4, 0, 4), dPath));
        cubes.add(new Cube(new Vector3f(0, 2, 4), dPath));
        cubes.add(new Cube(new Vector3f(2, 2, 4), dPath));
        cubes.add(new Cube(new Vector3f(4, 2, 4), dPath));
        cubes.add(new Cube(new Vector3f(0, 4, 4), dPath));
        cubes.add(new Cube(new Vector3f(2, 4, 4), dPath));
        cubes.add(new Cube(new Vector3f(4, 4, 4), dPath));
        for (Cube cube : cubes) {
            cube.createMesh();
        }

        // create the shader
        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");

        // create the renderer based on the main window and the shader
        renderer = new Renderer(window, shader);

        // initialize the shader
        shader.create();
    }

    /**
     * releases the objects
     */
    private void close() {
        // release the window
        window.destroy();

        // release the game objects
        for (Cube cube : cubes) {
            cube.destroy();
        }

        // release the shader
        shader.destroy();
    }

    /**
     * begins the thread
     */
    public void start() {
        Thread main = new Thread(this, "Game Buddies Game Jam!");
        main.start();
    }

    /**
     * update the window and game objects
     */
    private void update() {
        window.update();
        camera.update();
    }

    /**
     * render the game objects
     */
    private void render() {
        // render the game objects
        for (Cube cube : cubes) {
            renderer.renderMesh(cube, camera);
        }

        // must be called at the end
        window.swapBuffers();
    }
}
