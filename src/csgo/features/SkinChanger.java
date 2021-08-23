package csgo.features;

import csgo.dependencies.LocalPlayer;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import javafx.animation.AnimationTimer;

public class SkinChanger extends AnimationTimer {
    private int currentWeapon;
    public static boolean update = false;

    //Not working at all, need to be fixed


    @Override
    public void handle(long now) {
        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();
            for(int i = 0; i < 8; i++){
                currentWeapon = Modules.getProcess().readInt(localPlayer.getLocalPlayer() + Offsets.m_hMyWeapons + i * 0x4) & 0xfff;
                currentWeapon = Modules.getProcess().readInt(Modules.getClient() + Offsets.dwEntityList + (currentWeapon - 1) * 0x10);
                if(currentWeapon == 0) continue;
                short weaponID = (short)Modules.getProcess().readShort(currentWeapon + Offsets.m_iItemDefinitionIndex);
                int fallbackpaint = 0;
                float fallbackwear = 0.01f;
                int itemIDHigh = -1;
                if(weaponID == 9) //awp
                    fallbackpaint = 344;

                Modules.getProcess().writeInt(currentWeapon + Offsets.m_iItemIDHigh, itemIDHigh);
                Modules.getProcess().writeInt(currentWeapon + Offsets.m_nFallbackPaintKit, fallbackpaint);
                Modules.getProcess().writeFloat(currentWeapon + Offsets.m_flFallbackWear, fallbackwear);
                setStatTrak();
                if(update)
                    forceUpdate();



            }
        }
    }


    private void forceUpdate(){
        Modules.getProcess().writeInt(Modules.getClientState() + 0x174, -1);
    }
    private void setStatTrak(){
        Modules.getProcess().writeInt(currentWeapon + Offsets.m_nFallbackStatTrak, 420);
    }
}
