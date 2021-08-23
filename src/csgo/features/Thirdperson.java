package csgo.features;

import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;



public class Thirdperson extends AnimationTimer {
    public static boolean bThird = false;

    @Override
    public void handle(long now) {
        int mode;
        if(bThird)
            mode = 1;
        else
            mode = 0;


        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();

            localPlayer.setObserverMode(mode);
        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());



    }
}
