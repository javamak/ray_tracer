package com.mak.threed.engine.primitives;

import com.mak.threed.engine.entities.Material;
import com.mak.threed.engine.entities.Point;
import com.mak.threed.engine.entities.Ray;
import com.mak.threed.engine.entities.Vector;

public class Triangle implements ThreeDObject {

    public Point p0, p1, p2;
    public Material material;
    private static final double EPSILON = 0.0000001;

    @Override
    public Float intersects(Ray ray) {

        Vector edge1 = new Vector();
        Vector edge2 = new Vector();
        Vector h = new Vector();
        Vector s = new Vector();
        Vector q = new Vector();

        double a, f, u, v;
        edge1 = p1.sub(p0);
        edge2 = p2.sub(p0);
        h = ray.direction.crossProduct(edge2);
        a = edge1.dotProduct(h);
        if (a > -EPSILON && a < EPSILON) {
            return null;    // This ray is parallel to this triangle.
        }

        f = 1.0 / a;
        s = ray.origin.sub(p0);
        u = f * (s.dotProduct(h));
        if (u < 0.0 || u > 1.0) {
            return null;
        }
        q = s.crossProduct(edge1);
        v = f * ray.direction.dotProduct(q);
        if (v < 0.0 || u + v > 1.0) {
            return null;
        }
        // At this stage we can compute t to find out where the intersection point is on the line.
        float t = (float) (f * edge2.dotProduct(q));
        if (t > EPSILON) // ray intersection
        {
           /* outIntersectionPoint.set(0.0, 0.0, 0.0);
            outIntersectionPoint.scaleAdd(t, ray.direction, ray.origin);*/
            return t;
        } else // This means that there is a line intersection but not a ray intersection.
        {
            return null;
        }
    }

    @Override
    public Vector normal(Vector surfacePoint) {
        Vector x = p1.sub(p0);
        Vector y = p2.sub(p0);

        return surfacePoint.sub(x.crossProduct(y)).normalize();
    }

    @Override
    public Material getMaterial() {
        return material;
    }

}
