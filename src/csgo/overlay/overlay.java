package csgo.overlay;

import com.github.jonatino.process.Processes;
import com.github.jonatino.process.Process;

import com.guidedhacking.GH_Tools.GHMemory;
import com.guidedhacking.GH_Tools.GHTools;
import csgo.dependencies.KeyboardHook;
import csgo.features.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class overlay extends Application  {
    public static Process process = Processes.byName("csgo.exe");
    private static boolean attached = false;



    public static void main(String[] args) {
        if(GHMemory.openProcess("Counter-Strike: Global Offensive")){
            attached = true;
            launch(args);
        }else{
            Platform.exit();
            System.exit(0);
        }

    }
    private final Label title = new Label();
    public static boolean show = false;
    public static boolean exit = false;
    private final Label THIRD_LABEL = new Label();
    private final Label NOFLASH_LABEL = new Label();
    private final Label ESP_LABEL = new Label();
    private final Label RADAR_LABEL = new Label();
    private final Label BUNNY_LABEL = new Label();
    private final Label RANK_LABEL = new Label();
    private final Label CHAMS_LABEL = new Label();
    private final Label GLOW_LABEL = new Label();
    private final Label RCS_LABEL = new Label();
    private final Label FOV_LABEL = new Label();
    private final Label AIMBOT_LABEL = new Label();
    private final Label TRIGGER_LABEL = new Label();
    private final Label EXIT = new Label("PRESS END TO CLOSE CHEAT");


    private void shutdown(){
        if(exit){
            Platform.exit();
            System.exit(0);
        }
    }
    private final int middle = GHTools.getGameWidth()/2;
    public static Pane pane;
    @Override
    public void start(Stage primaryStage) {
        if(attached){
            pane = new Pane();
            RankReveal.pane = pane;
            Scene scene = new Scene(pane, GHTools.getGameWidth(), GHTools.getGameHeight());
            primaryStage.setTitle("MENU_OVERLAY");
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
            primaryStage.setAlwaysOnTop(true);
            //Author
            {
                Label auth = new Label("Author: Notorious");
                pane.getChildren().add(auth);
                auth.setLayoutX(30);
                auth.setLayoutY(30);
                auth.setFont(new Font("Verdana", 13));
                auth.setTextFill(Color.RED);
            }
            //Discord
            {
                Label auth = new Label("Discord: Notorious#3692");
                pane.getChildren().add(auth);
                auth.setLayoutX(30);
                auth.setFont(new Font("Verdana", 13));
                auth.setLayoutY(45);
                auth.setTextFill(Color.RED);
            }
            //Menu
            {
                Font fuente = new Font("Verdana", 13);

                pane.getChildren().add(title);
                title.setFont(fuente);
                title.setLayoutY(45);
                THIRD_LABEL.setFont(fuente);
                THIRD_LABEL.setLayoutY(60);
                pane.getChildren().add(THIRD_LABEL);
                NOFLASH_LABEL.setFont(fuente);
                NOFLASH_LABEL.setLayoutY(75);
                pane.getChildren().add(NOFLASH_LABEL);
                ESP_LABEL.setFont(fuente);
                ESP_LABEL.setLayoutY(90);
                pane.getChildren().add(ESP_LABEL);
                RADAR_LABEL.setFont(fuente);
                RADAR_LABEL.setLayoutY(105);
                pane.getChildren().add(RADAR_LABEL);
                BUNNY_LABEL.setFont(fuente);
                BUNNY_LABEL.setLayoutY(120);
                pane.getChildren().add(BUNNY_LABEL);
                RANK_LABEL.setFont(fuente);
                RANK_LABEL.setLayoutY(135);
                pane.getChildren().add(RANK_LABEL);
                CHAMS_LABEL.setFont(fuente);
                CHAMS_LABEL.setLayoutY(150);
                pane.getChildren().add(CHAMS_LABEL);
                GLOW_LABEL.setFont(fuente);
                GLOW_LABEL.setLayoutY(165);
                pane.getChildren().add(GLOW_LABEL);
                RCS_LABEL.setFont(fuente);
                RCS_LABEL.setLayoutY(180);
                pane.getChildren().add(RCS_LABEL);
                AIMBOT_LABEL.setFont(fuente);
                AIMBOT_LABEL.setLayoutY(195);
                pane.getChildren().add(AIMBOT_LABEL);
                TRIGGER_LABEL.setFont(fuente);
                TRIGGER_LABEL.setLayoutY(210);
                pane.getChildren().add(TRIGGER_LABEL);
                FOV_LABEL.setFont(fuente);
                FOV_LABEL.setLayoutY(225);
                pane.getChildren().add(FOV_LABEL);
                EXIT.setTextFill(Color.RED);
                EXIT.setFont(fuente);
                pane.getChildren().add(EXIT);




            }
            primaryStage.setX(GHTools.getGameXPos());
            primaryStage.setY(GHTools.getGameYPos());
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();

            AnimationTimer update = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if(attached){
                        primaryStage.setX(GHTools.getGameXPos());
                        primaryStage.setY(GHTools.getGameYPos());

                        title.setLayoutX(middle);
                        THIRD_LABEL.setLayoutX(middle);
                        NOFLASH_LABEL.setLayoutX(middle);
                        ESP_LABEL.setLayoutX(middle);
                        RADAR_LABEL.setLayoutX(middle);
                        BUNNY_LABEL.setLayoutX(middle);
                        RANK_LABEL.setLayoutX(middle);
                        CHAMS_LABEL.setLayoutX(middle);
                        GLOW_LABEL.setLayoutX(middle);
                        RCS_LABEL.setLayoutX(middle);
                        AIMBOT_LABEL.setLayoutX(middle);
                        TRIGGER_LABEL.setLayoutX(middle);
                        FOV_LABEL.setLayoutX(middle);
                        EXIT.setLayoutX(middle);





                        shutdown();

                        if(show){
                            //Normal Colors
                            {
                                title.setText("PRESS INSERT TO HIDE MENU");
                                title.setTextFill(Color.color(0,1f,0));
                            }
                            if(Thirdperson.bThird)
                                THIRD_LABEL.setTextFill(Color.GREEN);
                            else
                                THIRD_LABEL.setTextFill(Color.WHITE);

                            if(NoFlash.bFlash)
                                NOFLASH_LABEL.setTextFill(Color.GREEN);
                            else
                                NOFLASH_LABEL.setTextFill(Color.WHITE);

                            if(ESP.bEsp)
                                ESP_LABEL.setTextFill(Color.GREEN);
                            else
                                ESP_LABEL.setTextFill(Color.WHITE);

                            if(Radar.bRadar)
                                RADAR_LABEL.setTextFill(Color.GREEN);
                            else
                                RADAR_LABEL.setTextFill(Color.WHITE);

                            if(Bunny.bHop)
                                BUNNY_LABEL.setTextFill(Color.GREEN);
                            else
                                BUNNY_LABEL.setTextFill(Color.WHITE);

                            if(RankReveal.bRank)
                                RANK_LABEL.setTextFill(Color.GREEN);
                            else
                                RANK_LABEL.setTextFill(Color.WHITE);

                            if(Chams.bChams)
                                CHAMS_LABEL.setTextFill(Color.GREEN);
                            else
                                CHAMS_LABEL.setTextFill(Color.WHITE);

                            if(Glow.bGlow)
                                GLOW_LABEL.setTextFill(Color.GREEN);
                            else
                                GLOW_LABEL.setTextFill(Color.WHITE);

                            if(RCS.bRcs)
                                RCS_LABEL.setTextFill(Color.GREEN);
                            else
                                RCS_LABEL.setTextFill(Color.WHITE);

                            if(Aimbot.bAimbot)
                                AIMBOT_LABEL.setTextFill(Color.GREEN);
                            else
                                AIMBOT_LABEL.setTextFill(Color.WHITE);
                            if(Triggerbot.bTrigger)
                                TRIGGER_LABEL.setTextFill(Color.GREEN);
                            else
                                TRIGGER_LABEL.setTextFill(Color.WHITE);

                            FOV_LABEL.setTextFill(Color.WHITE);

                            EXIT.setLayoutY(240);


                            THIRD_LABEL.setText("[F1] THIRD PERSON: " + Thirdperson.bThird);
                            NOFLASH_LABEL.setText("[F2] NO FLASH: " + NoFlash.bFlash);
                            ESP_LABEL.setText("[F3][NOT WORKING] ESP: " + ESP.bEsp);
                            RADAR_LABEL.setText("[F4] RADAR: " + Radar.bRadar);
                            BUNNY_LABEL.setText("[F5] BUNNYHOP: " + Bunny.bHop);
                            RANK_LABEL.setText("[F6] RANK REVEAL: " + RankReveal.bRank);
                            CHAMS_LABEL.setText("[F7] CHAMS: " + Chams.bChams);
                            GLOW_LABEL.setText("[F8] GLOW: " + Glow.bGlow);
                            RCS_LABEL.setText("[F9] RCS: " + RCS.bRcs);
                            AIMBOT_LABEL.setText("[F10] [NOT WORKING] AIMBOT: " + Aimbot.bAimbot);
                            TRIGGER_LABEL.setText("[F11] TRIGGERBOT: " + Triggerbot.bTrigger);
                            FOV_LABEL.setText("[LK - RK] FOV: " + FOV.fov);


                        }else
                        {
                            title.setText("PRESS INSERT TO SHOW MENU");
                            title.setTextFill(Color.RED);
                            EXIT.setLayoutY(60);
                            THIRD_LABEL.setTextFill(Color.TRANSPARENT);
                            NOFLASH_LABEL.setTextFill(Color.TRANSPARENT);
                            ESP_LABEL.setTextFill(Color.TRANSPARENT);
                            RADAR_LABEL.setTextFill(Color.TRANSPARENT);
                            BUNNY_LABEL.setTextFill(Color.TRANSPARENT);
                            RANK_LABEL.setTextFill(Color.TRANSPARENT);
                            CHAMS_LABEL.setTextFill(Color.TRANSPARENT);
                            GLOW_LABEL.setTextFill(Color.TRANSPARENT);
                            RCS_LABEL.setTextFill(Color.TRANSPARENT);
                            FOV_LABEL.setTextFill(Color.TRANSPARENT);
                            AIMBOT_LABEL.setTextFill(Color.TRANSPARENT);
                            TRIGGER_LABEL.setTextFill(Color.TRANSPARENT);

                        }



                    }else
                        Platform.exit();
                }
            };
            KeyboardHook keyboard = new KeyboardHook();
            ESP esp = new ESP();
            NoFlash flash = new NoFlash();
            SkinChanger skin = new SkinChanger();
            Thirdperson third = new Thirdperson();
            Bunny bunny = new Bunny();
            Radar radar = new Radar();

            Chams chams = new Chams();
            Glow glow = new Glow();
            RCS rcs = new RCS();
            FOV fov = new FOV();
            Aimbot aimbot = new Aimbot();
            Triggerbot trigger = new Triggerbot();
            update.start();
            keyboard.start();
            third.start();
            aimbot.start();
            RankReveal rank = new RankReveal();
            esp.start();
            flash.start();
            radar.start();
            glow.start();
            chams.start();
            rcs.start();
            rank.start();
            fov.start();
            trigger.start();
            bunny.start();
            skin.start();


        }else{
            System.exit(0);
        }
    }

}
