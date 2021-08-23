package csgo.features;

import csgo.dependencies.Entity;
import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import javafx.animation.AnimationTimer;

public class Glow extends AnimationTimer {
    public static boolean bGlow = false;

    @Override
    public void handle(long now) {
        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();
            for(int i = 0; i < 32; i++){
                int curEnt = Modules.getProcess().readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
                if(curEnt != 0){
                    Entity entity = new Entity(curEnt);
                    if(localPlayer.getLocalPlayer() != entity.getEntity() && entity.getHealth() > 0 && entity.getHealth() < 101 && !entity.isDormant()){
                        float r, g;
                        if(localPlayer.getTeam() == entity.getTeam()){
                            r = 0f;
                            g = 1f;
                        }else {
                            r = 1f;
                            g = 0f;
                        }
                        if(bGlow)
                            entity.setGlow(r, g, 0f, 1f);
                    }
                }
            }
        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());
    }
}
