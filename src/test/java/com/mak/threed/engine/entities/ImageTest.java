package com.mak.threed.engine.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void toByte() {

        assertEquals(128, Image.toByte(.5f));
        assertEquals(255, Image.toByte(255f));
        assertEquals(255, Image.toByte(500f));
    }
}