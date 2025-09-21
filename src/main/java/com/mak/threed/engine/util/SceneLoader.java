package com.mak.threed.engine.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mak.threed.engine.entities.Scene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SceneLoader {
    public static Scene loadScene(String fileName) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(fileName));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, Scene.class);
    }
}
