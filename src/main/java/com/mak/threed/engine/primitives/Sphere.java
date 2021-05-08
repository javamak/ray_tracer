package com.mak.threed.engine.primitives;

import com.mak.threed.engine.entities.*;

public class Sphere implements ThreeDObject {
    public Point center;
    public float radius;
    public Material material;

    public Sphere() {
    }

    @Override
    public Float intersects(Ray ray) {
        Vector sphereToRay = ray.origin.sub(this.center);
        //a = 1
        float b = 2 * ray.direction.dotProduct(sphereToRay);
        float c = sphereToRay.dotProduct(sphereToRay) - this.radius * this.radius;
        float discriminant = b * b - 4 * c;

        if(discriminant >= 0){
            float dist = (float) ((-b - Math.sqrt(discriminant)) / 2);

            if (dist > 0)
                return dist;
        }
        return null;
    }

    @Override
    public Vector normal(Vector surfacePoint){
        return surfacePoint.sub(this.center).normalize();
    }

    @Override
    public Material getMaterial() {
        return material;
    }
}
