package com.mak.threed.engine.entities;

public class Sphere {
    public Point center;
    public float radius;
    public Material material;

    public Sphere() {
    }

    public Float intersects(Ray ray) {
        Vector sphereToRay = ray.origin.sub(this.center);
        //a = 1
        float b = 2 * ray.direction.dot_product(sphereToRay);
        float c = sphereToRay.dot_product(sphereToRay) - this.radius * this.radius;
        float discriminant = b * b - 4 * c;

        if(discriminant >= 0){
            float dist = (float) ((-b - Math.sqrt(discriminant)) / 2);

            if (dist > 0)
                return dist;
        }
        return null;
    }
    public Vector normal(Vector surfacePoint){
        return surfacePoint.sub(this.center).normalize();
    }

}
