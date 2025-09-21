package com.mak.threed.engine.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class Color {

    public float r, g, b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @JsonCreator
    public static Color fromHex(String hex) {
        float x = Integer.parseInt(hex.substring(1, 3), 16) / 255.0f;
        float y = Integer.parseInt(hex.substring(3, 5), 16) / 255.0f;
        float z = Integer.parseInt(hex.substring(5, 7), 16) / 255.0f;
        return new Color(x, y, z);
    }

    public Color add(Color oth) {
        return new Color(this.r + oth.r, this.g + oth.g, this.b + oth.b);
    }

    public Color mul(float scalar) {
        return new Color(this.r * scalar, this.g * scalar, this.b * scalar);
    }


    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return r == color.r &&
                g == color.g &&
                b == color.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }
}
