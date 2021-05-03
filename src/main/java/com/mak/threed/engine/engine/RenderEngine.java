package com.mak.threed.engine.engine;

import com.mak.threed.engine.entities.*;

public class RenderEngine {
    private static float MIN_DISPLACE = 0.001f;
    private static int MAX_DEPTH = 5;

    public Image render(Scene scene) {
        int width = scene.width;
        int height = scene.height;
        float aspect_ratio = (float)width/height;
        float x0 = -1.0f;
        float x1 = 1.0f;
        float xstep = (x1 - x0)/ (width - 1);

        float y0 = -1.0f / aspect_ratio;
        float y1 = 1.0f / aspect_ratio;
        float ystep = (y1 - y0)/ (height - 1);

        Camera camera = scene.camera;
        Image image = new Image(width, height);

        for(int j=0 ;j<height; j++){
            float y = y0 + j * ystep;
            for(int i=0; i<width; i++) {
                float x = x0 + i * xstep;
                Ray ray = new Ray(camera, new Point(x, y, 0.0f).sub(camera));
                image.setPixel(i, j, this.ray_trace(ray, scene, 0));
            }
        }

        return image;
    }

    private Color ray_trace(Ray ray, Scene scene, int depth) {
        Color color = new Color(0, 0, 0);
        //find the nearest object hit by the ray in the scene
        Hit hit = this.find_nearest(ray, scene);
        if(hit.object == null)
            return color;

        Vector hitPos = ray.origin.add(ray.direction.mul(hit.distance));
        Vector hitNormal = hit.object.normal(hitPos);
        color = color.add(this.colorAt(hit.object, hitPos, hitNormal, scene));

        if(depth<MAX_DEPTH) {
            Vector newRayPos = hitPos.add(hitNormal.mul(MIN_DISPLACE));
            Vector newRayDir = ray.direction.sub(hitNormal.mul(2 * ray.direction.dot_product(hitNormal)));
            Ray newRay = new Ray(newRayPos, newRayDir);
            //Attenuate the reflected ray found by reflection coefficient
            color = color.add(this.ray_trace(newRay, scene, depth+1).mul(hit.object.material.reflection));
        }
        return color;
    }

    private Hit find_nearest(Ray ray, Scene scene) {
        Sphere objHit = null;
        float distMin = 0;
        for(Sphere obj : scene.objects) {
            Float dist = obj.intersects(ray);
            if(dist != null && (objHit == null || dist<distMin)){
                distMin = dist;
                objHit = obj;
            }
        }
        return new Hit(distMin, objHit);
    }

    private Color colorAt(Sphere object, Vector hitPos, Vector hitNormal, Scene scene) {
        Material material = object.material;
        Color objColor = material.colorAt(hitPos);
        Vector toCam = scene.camera.sub(hitPos);
        int specular_k = 50;
        Color color = Color.fromHex(("#000000")).mul(material.ambient);
        for(Light light : scene.lights) {
            Ray toLight = new Ray(hitPos, light.position.sub(hitPos));
            //diffuse shading (Lambert)
            color = color.add(objColor.mul(material.diffuse).mul(Math.max(hitNormal.dot_product(toLight.direction), 0)));
            //specualar shadding (Blinn-Phong)
            Vector halfVector = toLight.direction.add(toCam).normalize();
            color = color.add(light.color.mul(material.specular).mul((float) Math.pow(Math.max(hitNormal.dot_product(halfVector), 0), specular_k)));
        }

        return color;
    }

    private class Hit {
        Float distance;
        Sphere object;

        public Hit(Float distance, Sphere object) {
            this.distance = distance;
            this.object = object;
        }
    }
}
