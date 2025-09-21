package com.mak.threed.engine.engine;

import com.mak.threed.engine.entities.Camera;
import com.mak.threed.engine.entities.Color;
import com.mak.threed.engine.entities.Image;
import com.mak.threed.engine.entities.Light;
import com.mak.threed.engine.entities.Material;
import com.mak.threed.engine.entities.Point;
import com.mak.threed.engine.entities.Ray;
import com.mak.threed.engine.entities.Scene;
import com.mak.threed.engine.entities.Vector;
import com.mak.threed.engine.primitives.ThreeDObject;

public class RenderEngine {
    private final static float MIN_DISPLACE = 0.001f;
    private final static int MAX_DEPTH = 5;

    private static Color colorAt(ThreeDObject object, Vector hitPos, Vector hitNormal, Scene scene) {
        Material material = object.getMaterial();
        Color objColor = material.colorAt(hitPos);
        Vector toCam = scene.camera.sub(hitPos);
        int specular_k = 50;
        Color color = Color.fromHex(("#000000")).mul(material.ambient);
        for (Light light : scene.lights) {
            Ray toLight = new Ray(hitPos, light.position.sub(hitPos));
            //diffuse shading (Lambert)
            color = color.add(objColor.mul(material.diffuse).mul(Math.max(hitNormal.dotProduct(toLight.direction), 0)));
            //specualar shadding (Blinn-Phong)
            Vector halfVector = toLight.direction.add(toCam).normalize();
            color = color.add(light.color.mul(material.specular).mul((float) Math.pow(Math.max(hitNormal.dotProduct(halfVector), 0), specular_k)));
        }

        return color;
    }

    public Image render(Scene scene) {
        int width = scene.width;
        int height = scene.height;
        float aspect_ratio = (float) width / height;
        float x0 = -1.0f;
        float x1 = 1.0f;
        float xstep = (x1 - x0) / (width - 1);

        float y0 = -1.0f / aspect_ratio;
        float y1 = 1.0f / aspect_ratio;
        float ystep = (y1 - y0) / (height - 1);

        Camera camera = scene.camera;
        Image image = new Image(width, height);

        for (int j = 0; j < height; j++) {
            float y = y0 + j * ystep;
            for (int i = 0; i < width; i++) {
                float x = x0 + i * xstep;
                Ray ray = new Ray(camera, new Point(x, y, 0.0f).sub(camera));
                image.setPixel(i, j, this.rayTrace(ray, scene, 0));
            }
        }

        return image;
    }

    Color rayTrace(Ray ray, Scene scene, int depth) {
        Color color = new Color(0, 0, 0);
        //find the nearest object hit by the ray in the scene
        Hit hit = this.findNearest(ray, scene);
        if (hit.object == null)
            return color;

        Vector hitPos = ray.origin.add(ray.direction.mul(hit.distance));
        Vector hitNormal = hit.object.normal(hitPos);

        color = color.add(colorAt(hit.object, hitPos, hitNormal, scene));

        if (depth < MAX_DEPTH) {
            Vector newRayPos = hitPos.add(hitNormal.mul(MIN_DISPLACE));
            Vector newRayDir = ray.direction.sub(hitNormal.mul(2 * ray.direction.dotProduct(hitNormal)));
            Ray newRay = new Ray(newRayPos, newRayDir);
            //Attenuate the reflected ray found by reflection coefficient
            color = color.add(this.rayTrace(newRay, scene, depth + 1).mul(hit.object.getMaterial().reflection));
        }

        return color;
    }

    private Hit findNearest(Ray ray, Scene scene) {
        ThreeDObject objHit = null;
        float distMin = 0;
        for (ThreeDObject obj : scene.objects) {
            Float dist = obj.intersects(ray);
            if (dist != null && (objHit == null || dist < distMin)) {
                distMin = dist;
                objHit = obj;
            }
        }
        return new Hit(distMin, objHit);
    }

    private static class Hit {
        Float distance;
        ThreeDObject object;

        public Hit(Float distance, ThreeDObject object) {
            this.distance = distance;
            this.object = object;
        }
    }
}
