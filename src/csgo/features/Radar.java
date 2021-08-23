package csgo.features;

import csgo.dependencies.Entity;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;


public class Radar extends AnimationTimer {
    public static boolean bRadar = false;


    @Override
    public void handle(long now) {

        if(Modules.getGameState() == Modules.IN_GAME){

            if(bRadar)
                for(int i = 0; i < 32; i++){
                    int curEnt = overlay.process.readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
                    if(curEnt != 0)
                    {
                        Entity entity = new Entity(curEnt);
                        entity.setSpotted(true);
                    }
                }
        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());
    }
}
