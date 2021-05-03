package com.mak.threed.engine;

import com.mak.threed.engine.entities.Scene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SceneLoader {
    public static Scene loadScene(String fileName) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(fileName));
        ObjectMapper objectMapper = new ObjectMapper();
        Scene scene = objectMapper.readValue(data, Scene.class);
        return scene;
    }
}
