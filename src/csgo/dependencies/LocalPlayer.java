package csgo.dependencies;

import csgo.helper.vector2;
import csgo.helper.vector3;
import csgo.overlay.overlay;

public class LocalPlayer {
    private final int entity;
    public LocalPlayer(){
        if(Modules.getGameState() == Modules.IN_GAME){
            entity = overlay.process.readInt(Modules.getClient() + Offsets.dwLocalPlayer);
        }else
            entity = -1;
    }


    public int getLocalPlayer(){
        return entity;
    }
    public int getHealth(){
        return overlay.process.readInt(entity + Offsets.m_iHealth);
    }
    public int getArmor(){
        return overlay.process.readInt(entity + Offsets.m_ArmorValue);
    }
    public boolean isDormant(){
        return overlay.process.readBoolean(entity + Offsets.m_bDormant);
    }
    public boolean isSpotted(){
        return overlay.process.readBoolean(entity + Offsets.m_bSpotted);
    }
    public int getBoneMatrix(){
        return overlay.process.readInt(entity + Offsets.m_dwBoneMatrix);
    }
    public vector3 getPosition(){
        float x = overlay.process.readFloat(entity + Offsets.m_vecOrigin);
        float y = overlay.process.readFloat(entity + Offsets.m_vecOrigin + 0x4);
        float z = overlay.process.readFloat(entity + Offsets.m_vecOrigin + 0x8);
        return new vector3(x, y, z);

    }
    public vector3 getVelocity(){
        float x = overlay.process.readFloat(entity + Offsets.m_vecVelocity);
        float y = overlay.process.readFloat(entity + Offsets.m_vecVelocity + 0x4);
        float z = overlay.process.readFloat(entity + Offsets.m_vecVelocity + 0x8);
        return new vector3(x, y, z);
    }
    public vector3 getViewOffsets(){
        float x = overlay.process.readFloat(entity + Offsets.m_vecViewOffset);
        float y = overlay.process.readFloat(entity + Offsets.m_vecViewOffset + 0x4);
        float z = overlay.process.readFloat(entity + Offsets.m_vecViewOffset + 0x8);
        return new vector3(x, y, z);
    }
    public vector2 getPunchAngles(){
        float punch_x = overlay.process.readFloat(entity + Offsets.m_aimPunchAngle);
        float punch_y = overlay.process.readFloat(entity + Offsets.m_aimPunchAngle + 0x4);
        return new vector2(punch_x, punch_y);
    }
    public int getCrosshairID(){
        return overlay.process.readInt(entity + Offsets.m_iCrosshairId);
    }
    public float getDistance(vector3 vec){
        vector3 here = this.getPosition();
        return (float) Math.sqrt(Math.pow((here.x-vec.x),2) + Math.pow((here.y-vec.y),2) + Math.pow((here.z-vec.z),2));
    }
    public int getShotsFired(){
        return overlay.process.readInt(entity + Offsets.m_iShotsFired);
    }
    public int getFov(){
        return overlay.process.readInt(entity + Offsets.m_iDefaultFOV);
    }
    public void setFov(int fov){
        overlay.process.writeInt(entity + Offsets.m_iDefaultFOV, fov);
    }
    public int getFlags(){
        return overlay.process.readInt(entity + Offsets.m_fFlags);
    }
    public int getFlashDuration(){
        return overlay.process.readInt(entity + Offsets.m_flFlashDuration);
    }
    public void setFlashDuration(int time){
        overlay.process.writeInt(entity + Offsets.m_flFlashDuration, time);
    }
    public float getFlashAlpha(){
        return overlay.process.readFloat(entity + Offsets.m_flFlashMaxAlpha);
    }
    public void setFlashAlpha(float alpha){
        overlay.process.writeFloat(entity + Offsets.m_flFlashMaxAlpha, alpha);
    }
    public int getObserverMode(){
        return overlay.process.readInt(entity + Offsets.m_iObserverMode);
    }
    public void setObserverMode(int mode){
        overlay.process.writeInt(entity + Offsets.m_iObserverMode, mode);
    }
    public short getTeam(){
        return (short) overlay.process.readShort(entity + Offsets.m_iTeamNum);
    }
    public int getTotalShotsOnServer(){
        return overlay.process.readInt(entity + 0xA3A8);
    }
    public void setTotalShotsOnServer(int shots){
        overlay.process.writeInt(entity + 0xA3A8, shots);
    }

}
