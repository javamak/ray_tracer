package com.mak.threed.engine.primitives;

import com.mak.threed.engine.entities.Point;
import com.mak.threed.engine.entities.Ray;
import com.mak.threed.engine.entities.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testNormal() {

        var sphere = new Sphere();
        sphere.center = new Point(10, 10, 10);
        var sf = new Vector(5, 5, 5);

        System.out.println(sphere.normal(sf));

    }

    @Test
    void testIntersects() {

        var sphere = new Sphere();
        sphere.center = new Point(10, 10000.5f, 1.0f);
        sphere.radius = 10000;
        var ray = new Ray(new Vector(0, -0.35f, 1.0f), new Vector(0.5718716f, 0.55295265f, 0.6059755f));

        assertEquals(1.5437224f, sphere.intersects(ray));

    }

}