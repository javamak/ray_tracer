package com.mak.threed.engine.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Material.class, name = "material"),
        @JsonSubTypes.Type(value = ChequeredMaterial.class, name = "chequered")
})
public class Material {
    public Color color;
    public float ambient = 0.5f;
    public float diffuse = 1.0f;
    public float specular = 1.0f;
    public float reflection = 0.5f;

    public Material() {
    }

    public Color colorAt(Vector position) {
        return this.color;
    }

    @Override
    public String toString() {
        return "Material{" +
                "color=" + color +
                ", ambient=" + ambient +
                ", diffuse=" + diffuse +
                ", specular=" + specular +
                ", reflection=" + reflection +
                '}';
    }
}
