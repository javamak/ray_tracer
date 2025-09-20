package com.mak.threed.engine.primitives;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mak.threed.engine.entities.Material;
import com.mak.threed.engine.entities.Ray;
import com.mak.threed.engine.entities.Vector;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sphere.class, name = "sphere"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle")
})
public interface ThreeDObject {
    Float intersects(Ray ray);

    Vector normal(Vector surfacePoint);

    Material getMaterial();
}
