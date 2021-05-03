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

    public Material(Color color, float ambient, float diffuse, float specular, float reflection) {
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.reflection = reflection;
    }

    public Color colorAt(Vector position) {
        return this.color;
    }
}
