package csgo.dependencies;

import csgo.helper.vector3;
import csgo.overlay.overlay;

public class Entity {
    //Proximamente a implementar
    private final int entity;
    public Entity(int address){
        this.entity = address;
    }


    public int getEntity(){
        return entity;
    }
    public static Entity getEntityByIndex(int index){
        int curEnt = overlay.process.readInt(Modules.getClient() + Offsets.dwEntityList + index * 0x10L);
        return new Entity(curEnt);
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
    public int getGlowIndex(){
        return overlay.process.readInt(entity + Offsets.m_iGlowIndex);
    }
    public int getFlags(){
        return overlay.process.readInt(entity + Offsets.m_fFlags);
    }
    public short getTeam(){
        return (short) overlay.process.readShort(entity + Offsets.m_iTeamNum);
    }
    public void setChams(int r, int g, int b, int a){
        overlay.process.writeInt(entity + Offsets.m_clrRender, r);//r
        overlay.process.writeInt(entity + Offsets.m_clrRender + 1, g);//g
        overlay.process.writeInt(entity + Offsets.m_clrRender + 2, b);//b
        overlay.process.writeInt(entity + Offsets.m_clrRender + 3, a);//a
    }
    public void setGlow(float r, float g, float b, float a){
        overlay.process.writeFloat(Modules.getGlowManager() + getGlowIndex() * 0x38L + 0x8, r);
        overlay.process.writeFloat(Modules.getGlowManager() + getGlowIndex() * 0x38L + 0xC, g);
        overlay.process.writeFloat(Modules.getGlowManager() + getGlowIndex() * 0x38L + 0x10, b);
        overlay.process.writeFloat(Modules.getGlowManager() + getGlowIndex() * 0x38L + 0x14, a);
        overlay.process.writeBoolean(Modules.getGlowManager() + getGlowIndex() * 0x38L + 0x28, true);
    }
    public void setSpotted(boolean spotted){
        overlay.process.writeBoolean(entity + Offsets.m_bSpotted, spotted);
    }
    public boolean isValidEntity(Entity ent){
        if(ent.isDormant()) return false;
        if(ent.getEntity() != 0) return false;
        return ent.getHealth() >= 0 && ent.getHealth() <= 100;
    }
}
