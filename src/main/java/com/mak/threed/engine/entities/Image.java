package com.mak.threed.engine.entities;

import java.io.IOException;
import java.io.Writer;

public class Image {
    public int width, height;
    private Color[][] pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Color[height][width];
    }

    public static int toByte(float x) {
        return Math.round(Math.max(Math.min(x * 255, 255), 0));
    }

    public void setPixel(int x, int y, Color color) {
        pixels[y][x] = color;
    }

    public void writePPM(Writer writer) throws IOException {
        writePPMHeader(writer, this.width, this.height);
        writeRawPPM(writer);
    }

    public void writePPMHeader(Writer writer, int width, int height) throws IOException {
        writer.write(String.format("P3 %d %d\n255\n", width, height));
    }

    public void writeRawPPM(Writer writer) throws IOException {
        for (Color[] row : pixels) {
            for (Color color : row) {
                writer.write(String.format("%d %d %d ", toByte(color.r), toByte(color.g), toByte(color.b)));
            }
            writer.write("\n");
        }
    }

}
