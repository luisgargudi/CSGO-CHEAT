package csgo.dependencies;

import csgo.features.*;
import csgo.overlay.overlay;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class KeyboardHook implements NativeKeyListener {

    public void start(){
        // Clear previous logging configurations.
        LogManager.getLogManager().reset();

    // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new KeyboardHook());

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_END)
            overlay.exit = true;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_INSERT)
            overlay.show = !overlay.show;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F1) //F1
            Thirdperson.bThird = !Thirdperson.bThird;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F2) //F2
            NoFlash.bFlash = !NoFlash.bFlash;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F3) //F3
            ESP.bEsp = !ESP.bEsp;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F4) //F4
            Radar.bRadar = !Radar.bRadar;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F5) //F5
            Bunny.bHop = !Bunny.bHop;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F6) //F6
            RankReveal.bRank = !RankReveal.bRank;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F7) //F7
            Chams.bChams = !Chams.bChams;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F8) //F8
            Glow.bGlow = !Glow.bGlow;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F9) //F9
            RCS.bRcs = !RCS.bRcs;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F10) //F10
            Aimbot.bAimbot = !Aimbot.bAimbot;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F11)
            Triggerbot.bTrigger = !Triggerbot.bTrigger;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_LEFT)
            FOV.fov--;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_RIGHT)
            FOV.fov++;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ALT){
            Aimbot.findNewTarget = true;
        }
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SPACE)
            Bunny.jump = true;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_X)
            Triggerbot.attack = true;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F12)
            SkinChanger.update = true;


    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ALT){
            Aimbot.findNewTarget = false;
        }
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SPACE)
            Bunny.jump = false;
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_X)
            Triggerbot.attack = false;

        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F12)
            SkinChanger.update = false;
    }
}
