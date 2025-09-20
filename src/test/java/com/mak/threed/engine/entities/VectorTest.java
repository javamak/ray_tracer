package com.mak.threed.engine.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class VectorTest {

    @Test
    void magnitude() {

        var v = new Vector(10.0f, 10.2f, 10.3f);
        assertEquals(17.61050796508789, v.magnitude());
    }

    @Test
    void normalize() {
        var v = new Vector(10.0f, 10.2f, 10.3f);
        assertEquals(new Vector(0.5678428f, 0.5791996f, 0.5848781f), v.normalize());
    }

    @Test
    void dotProduct() {

        var v = new Vector(10.0f, 10.2f, 10.3f);
        var v1 = new Vector(10.0f, 10.2f, 10.3f);
        assertEquals(310.13f, v.dotProduct(v1));
    }

    @Test
    void crossProductSameVectors() {
        var v = new Vector(10.0f, 10.2f, 10.3f);
        assertEquals(Vector.origin(), v.crossProduct(v));
    }

    @Test
    void crossProductDiffVectors() {
        var v = new Vector(10.0f, 10.2f, 10.3f);
        var v1 = new Vector(10.1f, 10.1f, 10.1f);
        assertEquals(new Vector(-1.0100021f, 3.0300064f, -2.0200043f), v.crossProduct(v1));
    }
}