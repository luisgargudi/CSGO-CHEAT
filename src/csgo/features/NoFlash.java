package csgo.features;

import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import javafx.animation.AnimationTimer;


public class NoFlash extends AnimationTimer {
    public static boolean bFlash = false;

    @Override
    public void handle(long now) {

        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();

            if(bFlash)
                localPlayer.setFlashDuration(0);
        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());


    }
}
