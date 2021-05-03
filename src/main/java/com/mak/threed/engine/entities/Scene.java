package com.mak.threed.engine.entities;

import java.util.List;

public class Scene {

    public Camera camera;
    public List<Sphere> objects;
    public List<Light> lights;
    public int width, height;

    public Scene() {
    }

    public Scene(Camera camera, List<Sphere> objects, List<Light> lights, int width, int height) {
        this.camera = camera;
        this.objects = objects;
        this.lights = lights;
        this.width = width;
        this.height = height;
    }
}
