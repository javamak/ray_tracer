package com.mak.threed.engine.entities;

public class Light {
    public Vector position;
    public Color color;

    public Light() {
    }

    public Light(Vector position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Light(Vector position, String color) {
        this.position = position;
        this.color = Color.fromHex(color);
    }
}
