package com.mak.threed.engine.engine;

import com.mak.threed.engine.entities.Color;
import com.mak.threed.engine.entities.Ray;
import com.mak.threed.engine.entities.Scene;
import com.mak.threed.engine.entities.Vector;
import com.mak.threed.engine.util.SceneLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RenderEngineTest {

    @Test
    void testRayTrace() throws IOException {

        var scene = SceneLoader.loadScene("2balls.json");

        //Ray: Ray{origin=Vector{x=0.0, y=-0.35, z=-1.0}, direction=Vector{x=0.19470401, y=0.10190647, z=0.9755539}}
        //Color: Color{r=0.004891529, g=0.0039132233, b=0.0026584396}

        //hit: Sphere{center=Vector{x=-0.75, y=-0.1, z=1.0}, radius=0.3} distance=0.33750856
        //hit pos: Vector{x=-0.85173714, y=0.17900792, z=0.9575252}
        //hit normal: Vector{x=-0.33912385, y=0.93002653, z=-0.14158271}

        //new Ray Pos: Vector{x=-0.8520763, y=0.17993794, z=0.95738363}
        //new Ray Dir: Vector{x=-0.79403615, y=0.47271764, z=-0.38215792}
        var ray = new Ray(new Vector(-0.0f, -0.35f, -1.0f), new Vector(0.19470401f, 0.10190647f, 0.9755539f));
        var engine = new RenderEngine();
        var color = engine.rayTrace(ray, scene, 0);
        System.out.println(color);

        assertEquals(new Color(0.004891529f, 0.0039132233f, 0.0026584396f), color);
    }

}