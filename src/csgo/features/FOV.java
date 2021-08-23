package csgo.features;

import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import javafx.animation.AnimationTimer;

public class FOV extends AnimationTimer {
    public static int fov = 90;
    @Override
    public void handle(long now) {
        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();
            if(fov > 0 && fov < 180)
                localPlayer.setFov(fov);

        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());
    }
}
