package pers.yuyanzhou.bubblebobble.object.gamecharacter;

import javafx.scene.input.KeyCode;
import pers.yuyanzhou.bubblebobble.object.gameprojectile.Bubble;
import pers.yuyanzhou.bubblebobble.support.Set;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

/**
 * InputAction is used to bind the keyboard to the Hero.
 * InputAction defines methods that listen for keyboard events ,execute corresponding methods and change image of hero
 * @author YuyanZhou
 */
public final class InputAction {
    private final Hero hero;
    private String imagePath;

    /**
     * Constructor of InputAction, passing a Hero.
     * Listening for keyboard events.
     * @param hero Be bound to keyboard
     */
    public InputAction(Hero hero) {
           this.hero = hero;
           imagePath = "/Image/Bub1Right.png";
    }

    /**
     * Listening for keyboard events
     */
    void addKeyBinding(){
        hero.getWorld().setFocusTraversable(true);
        hero.getWorld().setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.UP)){
                SoundEffect.JUMP.play();
                jump();
            }
            if(keyEvent.getCode().equals(KeyCode.LEFT)){
                moveL();
                imagePath = "/Image/Bub1Left.png";
            }
            if(keyEvent.getCode().equals(KeyCode.RIGHT)){
                moveR();
                imagePath = "/Image/Bub1Right.png";
            }
            if(keyEvent.getCode().equals(KeyCode.SPACE)){
                dash();
            }
            if(keyEvent.getCode().equals(KeyCode.E)){
                shoot();
            }
            if(keyEvent.getCode().equals(KeyCode.Q)){
                shield();
            }
            if(keyEvent.getCode().equals(KeyCode.W)){
                specialMove();
            }
            if (keyEvent.getCode().equals(KeyCode.P)){
                hero.getWorld().pauseGame();
            }
            if(keyEvent.getCode().equals(KeyCode.R)){
                hero.getWorld().reStartGame();
            }
        });
        hero.getWorld().setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.LEFT)){
                stopL();
            }
            if(keyEvent.getCode().equals(KeyCode.RIGHT)){
                stopR();
            }
            if(keyEvent.getCode().equals(KeyCode.SPACE)){
                stopDash();
            }
            if(keyEvent.getCode().equals(KeyCode.E)){
                stopShoot();
            }
            if(keyEvent.getCode().equals(KeyCode.Q)){
                noShield();
            }
        });
    }

    /**
     * Get image path of hero
     * @return Path of Image
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Handles special move
     */
    private void specialMove() {
            if(hero.readyToCharge){
                SoundEffect.EXPLODE.setMedium();
                SoundEffect.EXPLODE.play();
                hero.getWorld().addOtherGravity(new Bubble(hero.getX(), hero.getY(), hero.getWorld(), hero.getDirection()));
                hero.readyToCharge = false;
            }
    }

    /**
     * Handle no shield
     */
    private void noShield() {
        if (!hero.isStunned) {
            if(hero.getDirection()==1){
                imagePath = "/Image/Bub1Right.png";
            }
            else{
                imagePath = "/Image/Bub1Left.png";
            }}
        SoundEffect.SHIELD.stop();
            hero.isShielding = false;
    }

    /**
     * Handle shielding
     */
    private void shield() {
            if (!hero.isStunned) {
                if(hero.getDirection()==1){
                    imagePath = "/Image/Bub1RightShield.png";
                }
                else{
                    imagePath = "/Image/Bub1LeftShield.png";
                }
                SoundEffect.SHIELD.setToLoud();
                SoundEffect.SHIELD.play();
                hero.setxVelocity(0);
                hero.setxAccel(0);
                hero.isShielding = true;
            }
            else {
                if(hero.getDirection()==1){
                    imagePath = "/Image/Bub1RightStunned.png";
                }else if(hero.getDirection()==-1){
                    imagePath = "/Image/Bub1LeftStunned.png";
                }
            }
    }

    /**
     * Handles stopping to shoot
     */
    private void stopShoot() {
            hero.shootDelay = 0;
            }

    /**
     * Handles shooting
     */
    private void shoot() {
            if (!hero.isShielding && !hero.isStunned) {
                hero.shootDelay -= 1;
                if (hero.shootDelay <= 0) {
                    hero.shootProjectile();
                    hero.shootDelay = 10;
                }
            }
    }

    /**
     * Handles stopping to dash
     */
    private void stopDash() {
            hero.setTerminalXVelocity(Set.HERO_WALK.getInt());
    }

    /**
     * Handles dashing
     */
    private void dash() {
            hero.setTerminalXVelocity(Set.HERO_RUN.getInt());
    }

    /**
     * Handles jumping
     */
    private void jump() {
            if(!hero.isShielding && !hero.isStunned){
                hero.jump();
                SoundEffect.JUMP.play();
            }
    }

    /**
     * Handles stopping to move left
     */
    private void stopL() {
            hero.setxAccel(0);
    }
    /**
     * Handles moving left
     */
    private void moveL() {
            if(!hero.isShielding && !hero.isStunned) {
                hero.setxAccel(-Set.HERO_RUN_ACCEL.getInt());
            }
            hero.setDirection(-1);
    }

    /**
     * Handles stopping to move right
     */
    private void stopR() {
            hero.setxAccel(0);
    }

    /**
     * Handle moving right
     */
    private void moveR() {
            if (!hero.isShielding && !hero.isStunned){
                hero.setxAccel(Set.HERO_RUN_ACCEL.getInt());
                hero.setDirection(1);
            }
        }

}
