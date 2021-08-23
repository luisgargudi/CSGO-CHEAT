package csgo.helper;

public class vector3 {
    public float x, y, z;
    public vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public vector3 add(vector3 other){
        return new vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    @Override
    public String toString() {
        return "vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
