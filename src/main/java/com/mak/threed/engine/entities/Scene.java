package com.mak.threed.engine.entities;

import com.mak.threed.engine.primitives.ThreeDObject;

import java.util.List;

public class Scene {

    public Camera camera;
    public List<ThreeDObject> objects;
    public List<Light> lights;
    public int width, height;

    @Override
    public String toString() {
        return "Scene{" +
                "camera=" + camera +
                ", objects=" + objects +
                ", lights=" + lights +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
