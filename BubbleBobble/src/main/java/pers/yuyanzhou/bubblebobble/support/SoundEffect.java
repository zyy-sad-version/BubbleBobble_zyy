package pers.yuyanzhou.bubblebobble.support;

import javafx.scene.media.AudioClip;

import java.net.URL;

/**
 * Manager.SoundEffect handles the game's SFX.
 * Classes that want to use SFX will call the static variables in this enum and
 * play them via the play() method.
 * @author YuyanZhou
 */
public enum SoundEffect {
    /**
     * sound effect file path
     */
    FRUIT("/sfx/fruit.wav"),
    DEATH("/sfx/death.wav"),
    SHOOT("/sfx/shoot.wav"),
    POP("/sfx/pop.wav"),
    BUBBLED("/sfx/bubbled.wav"),
    JUMP("/sfx/jump.wav"),
    EXPLODE("/sfx/explode.wav"),
    BGM("/sfx/BGM.mp3"),
    LOAD("/sfx/loadSound.mp3"),
    BUBBLEBOBBLE("/sfx/BubbleBobble.wav"),
    SHIELD("/sfx/shield.mp3"),
    LAND("/sfx/land.wav");

    private AudioClip clip;
    /**
     * Constructor, init SoundEffect entity, get sound resource.
     * @param soundFileName sound effect used
     */
    SoundEffect(String soundFileName) {
        try{
            URL url = this.getClass().getResource(soundFileName);
            assert url != null;
            clip = new AudioClip(url.toExternalForm());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *  plays the sound effect
     */
    public void play() {
        clip.play();
    }

    /**
     * stop playing sound effect
     */
    public void stop(){
        if(clip.isPlaying()) {
            clip.stop();
        }
    }

    /**
     * sets whether or not the sound effect loops
     */
    public void setToLoop() {
        clip.setCycleCount(AudioClip.INDEFINITE);
    }

    /**
     * sets volume to be loud
     */
    public void setToLoud() {
        clip.setVolume(1);
    }

     /**
     * sets volume to be middle
     */
    public void setMedium(){
        clip.setVolume(0.5);
    }

}
