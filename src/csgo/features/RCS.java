package csgo.features;

import csgo.dependencies.Helper;
import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import csgo.helper.vector2;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;

public class RCS extends AnimationTimer {
    public static boolean bRcs = false;
    private float oldpunch_x = 0f;
    private float oldpunch_y = 0f;
    @Override
    public void handle(long now) {
        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();

            if(bRcs){
                if(localPlayer.getShotsFired() > 2){
                    float newrcs_x = Modules.getViewAngles().x - (localPlayer.getPunchAngles().x - oldpunch_x) * 2;
                    float newrcs_y = Modules.getViewAngles().y - (localPlayer.getPunchAngles().y - oldpunch_y) * 2;
                    vector2 angles = new vector2(newrcs_x, newrcs_y);
                    oldpunch_x = localPlayer.getPunchAngles().x;
                    oldpunch_y = localPlayer.getPunchAngles().y;
                    if(Helper.NanChecker(newrcs_x, newrcs_y) && Helper.checkAngles(newrcs_x, newrcs_y)){
                        Modules.setViewAngles(angles);
                    }
                }else{
                    oldpunch_x = 0f;
                    oldpunch_y = 0f;
                }
            }


        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());
    }
}
