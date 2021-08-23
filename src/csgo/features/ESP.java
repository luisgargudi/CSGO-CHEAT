package csgo.features;


import csgo.dependencies.*;
import csgo.helper.ScreenPos;
import csgo.helper.vector3;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class ESP extends AnimationTimer {
    private final Rectangle[] EspBoxes = new Rectangle[32];
    public static boolean bEsp = false;


    //Not working at all, need to be fixed

    private void stopEsp(){
        for(Rectangle r : EspBoxes)
            if(r != null)
                r.setVisible(false);
    }
    
    private void DrawBox(Entity entity, LocalPlayer player, Pane pane){
        vector3 bottom = entity.getPosition();
        vector3 offset = entity.getViewOffsets();
        vector3 top = bottom.add(offset);

        ScreenPos topPos = Helper.toScreen(top, player);
        ScreenPos bottomPos = Helper.toScreen(bottom, player);

        if(!topPos.isVisible || !bottomPos.isVisible) return;

        float height = Math.abs(topPos.vec.y - bottomPos.vec.y);
        float width = height;

        topPos.vec.y -= height * 0.2f;
        height *= 1.3f;

        Rectangle box = new Rectangle();
        box.setX(topPos.vec.x - width/1.6f);
        box.setY(topPos.vec.y);
        box.setWidth(width);
        box.setHeight(height);
        box.setFill(Color.TRANSPARENT);
        box.setStroke(Color.RED);
        box.setVisible(bEsp);
        pane.getChildren().add(box);
    }

    @Override
    public void handle(long now) {
        Pane espOverlay = overlay.pane;
        if(Modules.getGameState() == Modules.IN_GAME){
            LocalPlayer localPlayer = new LocalPlayer();
            for(int i = 0; i < 32; i++){
                int curEnt = Modules.getProcess().readInt(Modules.getClient() + Offsets.dwEntityList + i * 0X10);
                if(curEnt != 0){
                    Entity entity = new Entity(curEnt);
                    if(entity.isDormant() || entity.getHealth() <= 0 || entity.getHealth() > 100 || entity.getEntity() == localPlayer.getLocalPlayer()) continue;

                }
            }
        }



    }

}




