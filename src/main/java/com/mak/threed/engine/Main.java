package com.mak.threed.engine;

import com.mak.threed.engine.engine.RenderEngine;
import com.mak.threed.engine.entities.Scene;
import com.mak.threed.engine.util.SceneLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var start = System.currentTimeMillis();
        Scene scene = SceneLoader.loadScene("2balls.json");

        var renderStartTime = System.currentTimeMillis();
        var engine = new RenderEngine();
        var image = engine.render(scene);
        var renderTime = System.currentTimeMillis() - renderStartTime;
        var fileOpStart = System.currentTimeMillis();
        try (var writer = new BufferedWriter(new FileWriter("one.ppm"))) {
            image.writePPM(writer);
        }
        var end = System.currentTimeMillis();
        System.out.printf("Overall time in %s ms%n", end - start);
        System.out.printf("Render completed in %s ms%n", renderTime);
        System.out.printf("File write completed in %s ms%n", end -  fileOpStart);
    }
}
