package csgo.dependencies;

import com.github.jonatino.process.Process;
import csgo.helper.vector2;
import csgo.helper.vector3;
import csgo.overlay.overlay;

public class Modules {
    public static final int IN_GAME = 6;
    private static final Process process = overlay.process;
    private static final long client = overlay.process.findModule("client.dll").address();
    private static final long engine = overlay.process.findModule("engine.dll").address();

    public static Process getProcess(){
        return process;
    }
    public static int getClientState(){
        return process.readInt(engine + Offsets.dwClientState);
    }
    public static int getGameState(){
        return process.readInt(getClientState() + Offsets.dwClientState_State);
    }
    public static int getGlowManager(){
        return process.readInt(client + Offsets.dwGlowObjectManager);
    }
    public static vector2 getViewAngles(){
        float rcs_x = process.readFloat(Modules.getClientState() + Offsets.dwClientState_ViewAngles);
        float rcs_y = process.readFloat(Modules.getClientState() + Offsets.dwClientState_ViewAngles + 0x4);
        return new vector2(rcs_x, rcs_y);
    }
    public static long getClient() {
        return client;
    }
    public static long getEngine(){
        return engine;
    }
    public static int getCrosshairTarget(int crosshairID){
        return process.readInt( client + Offsets.dwEntityList + (crosshairID - 1) * 0x10L);
    }
    public static void setViewAngles(vector2 angles){
        process.writeFloat(getClientState() + Offsets.dwClientState_ViewAngles, angles.x);
        process.writeFloat(getClientState() + Offsets.dwClientState_ViewAngles + 0x4, angles.y);
    }
}
