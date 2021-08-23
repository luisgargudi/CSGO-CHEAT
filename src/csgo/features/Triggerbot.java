package csgo.features;

import csgo.dependencies.*;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;


public class Triggerbot extends AnimationTimer {
    public static boolean bTrigger = false;
    public static boolean attack = false;
    @Override
    public void handle(long now) {
       if(Modules.getGameState() == Modules.IN_GAME){
           LocalPlayer localPlayer = new LocalPlayer();
           for(int i = 0; i < 32; i++){
               int curEnt = overlay.process.readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
               if(curEnt != 0){
                   int crosshairID = Modules.getCrosshairTarget(localPlayer.getCrosshairID());
                   int crosshairTeam = Modules.getProcess().readInt(crosshairID + Offsets.m_iTeamNum);
                   if(bTrigger && attack && 0 < localPlayer.getCrosshairID() && localPlayer.getCrosshairID() <= 64 && localPlayer.getTeam() != crosshairTeam)
                       Modules.getProcess().writeInt(Modules.getClient() +  Offsets.dwForceAttack, 6);
               }
           }
       }
    }
}
