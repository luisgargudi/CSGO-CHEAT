package csgo.features;


import csgo.dependencies.Entity;
import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;

public class Chams extends AnimationTimer {
    public static boolean bChams = false;

    @Override
    public void handle(long now) {
        if (Modules.getGameState() == Modules.IN_GAME) {
            LocalPlayer localPlayer = new LocalPlayer();
            for (int i = 0; i < 32; i++) {
                int curEnt = overlay.process.readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
                if (curEnt != 0) {
                    Entity entity = new Entity(curEnt);
                    if (!entity.isDormant() && entity.getHealth() > 0 && entity.getHealth() < 101 && entity.getEntity() != localPlayer.getLocalPlayer()) {
                        int r, g, b, a;
                        if (entity.getTeam() == localPlayer.getTeam() && bChams) {
                            r = 0;
                            g = 255;
                            b = 0;
                            a = 255;
                        }else if(entity.getTeam() != localPlayer.getTeam() && bChams){
                            r = 255;
                            g = 0;
                            b = 0;
                            a = 255;
                        }
                        else {
                            r = 255;
                            g = 255;
                            b = 255;
                            a = 255;
                        }


                        entity.setChams(r, g, b, a);


                    }
                }
            }
        }
        else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());
    }
}
