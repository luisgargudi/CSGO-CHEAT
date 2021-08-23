package csgo.helper;

public class vector2 {
    public float x;
    public float y;
    public vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
