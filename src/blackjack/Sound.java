package blackjack;


import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;

/**
 * Created by xiebin on 16/10/16.
 */
public class Sound {
    public Sound(){};

    public static void PlayerAudio(String filename) {
        try {
            FileInputStream fileau = new FileInputStream("music/"+filename+".wav");
            AudioStream as=new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
