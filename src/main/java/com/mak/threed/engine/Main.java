package com.mak.threed.engine;

import com.mak.threed.engine.engine.RenderEngine;
import com.mak.threed.engine.entities.Image;
import com.mak.threed.engine.entities.Scene;

import java.io.FileWriter;
import java.io.IOException;

import com.mak.threed.engine.util.SceneLoader;

public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Scene scene = SceneLoader.loadScene("2balls.json");
        RenderEngine engine = new RenderEngine();
        Image image = engine.render(scene);
        image.writePPM(new FileWriter("one.ppm"));
        System.out.println((System.currentTimeMillis() - start));
    }
}
