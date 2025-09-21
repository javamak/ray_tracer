package com.mak.threed.engine.entities;

public class ChequeredMaterial extends Material {

    public Color color1;

    @Override
    public Color colorAt(Vector position) {
        if (((int) ((position.x + 5.0) * 3.0)) % 2 == ((int) ((position.z + 5.0) * 3.0)) % 2) {
            return this.color;
        }
        return this.color1;
    }
}
