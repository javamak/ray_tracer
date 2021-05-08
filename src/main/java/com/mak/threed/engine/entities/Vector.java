package com.mak.threed.engine.entities;
import java.util.Objects;

public class Vector {
    public float x, y, z;

    public Vector(){}

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector oth){
        return new Vector(this.x + oth.x, this.y + oth.y, this.z + oth.z);
    }

    public Vector sub(Vector oth){
        return new Vector(this.x - oth.x, this.y - oth.y, this.z - oth.z);
    }

    public Vector mul(float sclar){
        return new Vector(this.x * sclar, this.y * sclar, this.z * sclar);
    }

    public Vector div(float sclar){
        return new Vector(this.x / sclar, this.y / sclar, this.z / sclar);
    }

    public float magnitude(){
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector normalize(){
        return div(magnitude());
    }

    public float dotProduct(Vector oth){
        return this.x*oth.x + this.y*oth.y + this.z*oth.z;
    }

    public Vector crossProduct(Vector oth) {
        Vector v = new Vector();
        v.x = this.y*oth.z - this.z*oth.y;
        v.y = this.z*oth.x - this.x*oth.z;
        v.z = this.x*oth.y - this.y*oth.x;
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Float.compare(vector.x, x) == 0 &&
                Float.compare(vector.y, y) == 0 &&
                Float.compare(vector.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
