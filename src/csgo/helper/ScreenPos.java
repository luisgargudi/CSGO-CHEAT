package csgo.helper;

public class ScreenPos {
    public boolean isVisible;
    public vector2 vec;

    public ScreenPos(vector2 vec, boolean isVisible){
        this.vec = vec;
        this.isVisible = isVisible;
    }

}
