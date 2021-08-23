package csgo.features;

import com.guidedhacking.GH_Tools.GHTools;
import csgo.dependencies.Modules;
import csgo.dependencies.Offsets;
import csgo.overlay.overlay;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;



public class RankReveal extends AnimationTimer {
    public static Pane pane;
    public static boolean bRank = false;
    private final String[] ranks = {"Unranked", "Silver I", "Silver II", "Silver III", "Silver IV",
            "Silver Elite", "Silver Elite Master", "Gold Nova I", "Gold Nova II", "Gold Nova III", "Gold Nova Master",
            "Master Guardian I", "Master Guardian II", "Master Guardian Elite", "Distinguished Master Guardian",
            "Legendary Eagle", "Legendary Eagle Master", "Supreme Master First Class", "The Global Elite"};

    /*
    Need to be showed in the overlay
     */
    @Override
    public void handle(long now) {

        if(Modules.getGameState() == Modules.IN_GAME){

            if(bRank)
                for(int i = 0; i < 32; i++){
                    int curEnt = overlay.process.readInt(Modules.getClient() + Offsets.dwEntityList + i * 0x10);
                    if(curEnt != 0){
                        int playerInfo = overlay.process.readInt(Modules.getClientState() + Offsets.dwClientState_PlayerInfo);
                        int playerInfo_Items =  overlay.process.readInt(overlay.process.readInt(playerInfo + 0x40) + 0xC);
                        int info = overlay.process.readInt(playerInfo_Items + 0x28 + (i * 0x34));
                        int playerres = overlay.process.readInt(Modules.getClient() + Offsets.dwPlayerResource);
                        int rank = overlay.process.readInt(playerres + Offsets.m_iCompetitiveRanking + (i * 4));
                        //int wins = overlay.process.readInt(playerres + Offsets.m_iCompetitiveWins + i * 4);
                        String name = overlay.process.readString(info + 0x10, 80);
                        if(!name.equals("GOTV")){
                            String NAME_RANK = "[PLAYER] " + name + " - [RANK] " + ranks[rank] + "\n";
                            System.out.print(NAME_RANK);
                        }
                    }

                }
        }else
            System.out.println("You're not in game, your current state is " + Modules.getGameState());

    }
}
