package csgo.dependencies;

import com.guidedhacking.GH_Tools.GHTools;
import csgo.helper.ScreenPos;
import csgo.helper.vector2;
import csgo.helper.vector3;
import csgo.overlay.overlay;

public class Helper {

    public static Entity closestToCrosshair(LocalPlayer player){
        int centerX = GHTools.getGameWidth()/2;
        int centerY = GHTools.getGameHeight()/2;
        vector2 center = new vector2(centerX, centerY);
        Entity out = null;
        float shortest = Float.MAX_VALUE;
        for(int i = 0; i < 32; i++){
            int curEnt = overlay.process.readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
            if(curEnt != 0){
                Entity ent = new Entity(curEnt);
                if(ent.getEntity() == player.getLocalPlayer()) continue;
                if(ent.getHealth() <= 0 || ent.getHealth() > 100) continue;
                if(ent.isDormant()) continue;
                ScreenPos pos = toScreen(getBonePos(8, ent), player);
                if(!pos.isVisible && pos.vec != null) {
                    float dist = distanceBetweenPoints(center, pos.vec);
                    if(dist < shortest){
                        out = ent;
                        shortest = dist;
                    }
                }
            }
        }
        return out;
    }

    public static ScreenPos toScreen(vector3 vec, LocalPlayer player){
        float[][] viewMatrix = new float[4][4];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                viewMatrix[i][j] = overlay.process.readFloat(Modules.getClient() + Offsets.dwViewMatrix +  count * 4L);
                count++;
            }
        }

        float x = viewMatrix[0][0] * vec.x + viewMatrix[0][1] * vec.z + viewMatrix[0][2] * vec.y + viewMatrix[0][3];
        float y = viewMatrix[1][0] * vec.x + viewMatrix[1][1] * vec.z + viewMatrix[1][2] * vec.y + viewMatrix[1][3];
        double w = viewMatrix[3][0] * vec.x + viewMatrix[3][1] * vec.z + viewMatrix[3][2] * vec.y + viewMatrix[3][3];

        double invw = 1.0 / w;

        x *= invw;
        y *= invw;

        int width = GHTools.getGameWidth();
        int height = GHTools.getGameHeight();

        int x2 = width / 2;
        int y2 = height / 2;

        x2 += 0.5 * x * width + 0.5;
        y2 -= 0.5 * y * height + 0.5;

        boolean visible = true;
        vector2 diffAngle = new vector2(0, 0);
        vector3 myPos = player.getPosition();
        vector3 playerAngles = player.getViewOffsets();
        vector2 angles = new vector2(playerAngles.x, playerAngles.y);
        calcAngles(myPos, vec, diffAngle);
        float distanceFromCrosshair = Math.abs(normalizeAngle(distanceBetweenPoints(diffAngle, angles)));
        if (distanceFromCrosshair > 90f) visible = false;


        vector2 pos = new vector2((float)x2, (float)y2);
        return new ScreenPos(pos, visible);
    }

    public static void calcAngles(vector3 src, vector3 dst, vector2 angles) {
        double[] delta = { (src.x-dst.x), (src.z-dst.z), (src.y-dst.y) };
        double hyp = Math.sqrt(delta[0]*delta[0] + delta[1]*delta[1]);

        angles.x = (float) Math.toDegrees(Math.asin(delta[2]/hyp)); // pitch
        angles.y = (float) Math.toDegrees(Math.atan(delta[1]/delta[0])); // yaw
        if(delta[0] >= 0.0)
        {
            angles.y += 180.0f;
        }
    }


    //Good maths
    public static float distanceBetweenPoints(vector2 a, vector2 b) {
        float diffX = b.x - a.x;
        float diffY = b.y - a.y;
        return (float)Math.sqrt(diffX*diffX + diffY*diffY);
    }
    public static boolean NanChecker(float x, float y){
        return !Float.isNaN(x) && !Float.isNaN(y);
    }
    public static boolean checkAngles(float x, float y){
        if(x > 89f) return false;
        if(x < -89f) return false;
        if(y > 360f) return false;
        return !(y < -360f);
    }
    public static vector2 normalizeAngles(vector2 angle){
        if(angle.x > 89)
            angle.x -= 360;
        if(angle.x < -89)
            angle.x += 360;
        if(angle.y > 180)
            angle.y -= 360;
        if(angle.y < -180)
            angle.y += 360;
        return new vector2(angle.x, angle.y);
    }
    public static vector2 calcDistance(vector2 from, vector2 to){
        float distancex = to.x - from.x;
        if (distancex < -89)
            distancex += 360;
        else if(distancex > 89)
            distancex -= 360;
        else if(distancex < 0.0)
            distancex = -distancex;

        float distancey = to.y - from.y;
        if (distancey < -180)
            distancey += 360;
        else if(distancey > 180)
            distancey -= 360;
        else if (distancey < 0.0)
            distancey = -distancey;
        return new vector2(distancex, distancey);

    }
    public static vector2 calcAngle(vector3 localPos, vector3 enemyPos){
        vector3 delta = new vector3(0,0,0);
        delta.x = localPos.x - enemyPos.x;
        delta.y = localPos.y - enemyPos.y;
        delta.z = localPos.y - enemyPos.z;
        double hyp = Math.sqrt(delta.x * delta.x + delta.y * delta.y + delta.z * delta.z);
        double x = Math.atan(delta.z / hyp) * 180 / Math.PI;
        double y = Math.atan(delta.y / delta.x) * 180 / Math.PI;
        if(delta.x >= 0.0)
            y += 180.0;
        return new vector2((float)x, (float)y);
    }

    public static vector2 calcDistance(float localX, float localY, float enemyX, float enemyY){
        float distancex = enemyX - localX;
        if (distancex < -89)
            distancex += 360;
        else if(distancex > 89)
            distancex -= 360;
        else if(distancex < 0.0)
            distancex = -distancex;

        float distancey = enemyY - localY;
        if (distancey < -180)
            distancey += 360;
        else if(distancey > 180)
            distancey -= 360;
        else if (distancey < 0.0)
            distancey = -distancey;
        return new vector2(distancex, distancey);

    }

    //
    public static float normalizeAngle(float angle){
        return 0;
    }

    public static float clamp(float val, float min, float max){
        if(val < min) return min;
        return Math.min(val, max);
    }




    public static float vecToAngle(vector2 vec){
        return (float)Math.toDegrees(Math.atan2(vec.y, vec.x));
    }




    public static float differenceBetweenAngles(final float ang1, final float ang2) {
        return Math.abs(((ang1 - ang2 + 180) % 360 + 360) % 360 - 180);
    }

    public static float pythag(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }


    public static vector3 getBonePos(int bone, Entity ent){
        float x = overlay.process.readFloat(ent.getBoneMatrix() + 0x30L * bone + 0x0C);
        float y = overlay.process.readFloat(ent.getBoneMatrix() + 0x30L * bone + 0x1C);
        float z = overlay.process.readFloat(ent.getBoneMatrix() + 0x30L * bone + 0x2C);
        return new vector3(x, y, z);
    }
}

