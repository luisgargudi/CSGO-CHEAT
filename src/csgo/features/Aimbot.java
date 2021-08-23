package csgo.features;
import csgo.dependencies.*;
import csgo.helper.vector2;
import csgo.helper.vector3;
import javafx.animation.AnimationTimer;

public class Aimbot extends AnimationTimer {
    public static boolean bAimbot = false;
    public static boolean findNewTarget = false;
    private final int fov = FOV.fov;

    private vector2 oldDistance = new vector2(1111111111, 1111111111);

    @Override
    public void handle(long now) {
        if(Modules.getGameState() == Modules.IN_GAME){
            Entity entity;
            LocalPlayer localPlayer = new LocalPlayer();

            for(int i = 0; i < 32; i++){
                int curEnt = Modules.getProcess().readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
                if(curEnt != 0){
                    entity = new Entity(curEnt);
                    if(entity.getTeam() != localPlayer.getTeam() && entity.getHealth() > 0) {
                        float angle_x = Modules.getProcess().readFloat(Modules.getClientState() + Offsets.dwClientState_ViewAngles);
                        float angle_y = Modules.getProcess().readFloat(Modules.getClientState() + Offsets.dwClientState_ViewAngles + 0x4);
                        float localx = Modules.getProcess().readFloat(localPlayer.getLocalPlayer() + Offsets.m_vecOrigin);
                        float localy = Modules.getProcess().readFloat(localPlayer.getLocalPlayer() + Offsets.m_vecOrigin + 4);
                        float angle_z = Modules.getProcess().readFloat(localPlayer.getLocalPlayer() + Offsets.m_vecViewOffset + 0x8);
                        float localz = Modules.getProcess().readFloat(localPlayer.getLocalPlayer() + Offsets.m_vecOrigin + 8) + angle_z;

                        vector3 localPos = new vector3(localx,localy,localz);
                        if(bAimbot){
                            vector3 head = Helper.getBonePos(8, entity);
                            vector2 xy = Helper.calcAngle(localPos, head);

                            vector2 newDist = Helper.calcDistance(angle_x, angle_y, xy.x, xy.y);
                            if(newDist.x < oldDistance.x && newDist.y < oldDistance.y){
                                oldDistance = newDist;
                                if(findNewTarget){
                                    if(entity.getHealth() > 0 && !entity.isDormant() && entity.getEntity() != localPlayer.getLocalPlayer()){
                                        vector2 _xy_ = Helper.calcAngle(localPos, head);
                                        vector2 normalized = Helper.normalizeAngles(_xy_);

                                        System.out.println("Escribiendo Nuevos Angulos");
                                        Modules.getProcess().writeFloat(Modules.getClientState() + Offsets.dwClientState_ViewAngles, normalized.x);
                                        Modules.getProcess().writeFloat(Modules.getClientState() + Offsets.dwClientState_ViewAngles + 0x4, normalized.y);


                                    }
                                }
                            }


                        }




                    }
                }
            }
        }
    }
}


