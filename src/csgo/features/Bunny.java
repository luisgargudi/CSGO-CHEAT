package csgo.features;

import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;

public class Bunny extends AnimationTimer {
    public static boolean bHop = false;
    public static boolean jump = false;

    @Override
    public void handle(long now) {
        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();

            if(bHop && jump && localPlayer.getFlags() == 257 || localPlayer.getFlags() == 263)
                overlay.process.writeInt(Modules.getClient() + Offsets.dwForceJump, 6);

        }
    }
}
