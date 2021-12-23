package pers.yuyanzhou.bubblebobble.object.gamecharacter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.gameprojectile.HeroProjectile;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Score;
import pers.yuyanzhou.bubblebobble.support.Set;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

import java.net.URL;

/**
 * A Hero is an object.gamecharacter that is controllable by the player.
 * Of all the object, only Hero has KeyBindings.
 * Hero can shoot HeroProjectiles, shield from attacks, trigger a special attack and
 * collect Fruits for points.
 * @author YyuanZhou
 */
public class Hero extends GameChar {
    protected boolean isShielding, isStunned,readyToCharge,isOnPlatform;
    protected int shieldTimer, stunTimer,shootDelay, chargeTimer;
    protected double jumpSpeed;
    private final InputAction inputAction;

    /**
     * Constructor of Hero, pass GameWorld, coordinate to create Hero
     * @param world which world Boss need to be added
     * @param colNum value to calculate x coordinate of Hero
     * @param rowNum value to calculate y coordinate of Hero
     */
      public Hero(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.HERO_SIZE.getInt(), Set.HERO_SIZE.getInt());
        isOnPlatform = false;
        setTerminalXVelocity(Set.HERO_TERMINAL_VELOCITY_X.getInt());
        jumpSpeed=Set.HERO_JUMP_SPEED.getInt();
        isShielding = false;
        shieldTimer = Set.HERO_SHIELD_TIME.getInt();
        isStunned = false;
        stunTimer = Set.HERO_STUNNED_TIME.getInt();
        shootDelay = Set.HERO_SHOOT_DELAY.getInt();
        readyToCharge = false;
        inputAction = new InputAction(this);
        inputAction.addKeyBinding();
        chargeTimer = 0;
    }

    /**
     * Draw hero
     * @param imageView Where hero need to be draw on
     */
    @Override
    public void drawOn(ImageView imageView) {
        URL url = this.getClass().getResource(inputAction.getImagePath());
        assert url != null;
        Image image = new Image(url.toExternalForm(),getWidth(),getHeight(),true,true);
        imageView.setImage(image);
        imageView.setX(getX());
        imageView.setY(getY());
    }

    /**
     * Handles hero shoot projectile
     */
    public void shootProjectile(){
        SoundEffect.SHOOT.play();
        getWorld().addOtherGravity(new HeroProjectile(this.getX(),this.getY(),this.getWorld(),this.getDirection()));
    }
    /**
     * Handles colliding with a mock
     */
    public void collideWithMock(){
        if(!isShielding){
            die();
        }
    }
    /**
     * Handles jumping
     */
    public void jump(){
        if(isOnPlatform){
            setY(getY()-1);
            setyVelocity(-jumpSpeed);
            isOnPlatform = false;
        }
    }
    /**
     * Handles collision with wall unit
     */
    @Override
    public void collideWithWall() {
        setxVelocity(0);
        setxAccel(0);
    }
    /**
     * Handles death
     */
    public void die(){
        SoundEffect.DEATH.play();
        SoundEffect.DEATH.setMedium();
        Score.getInstance().heroDie();
        getWorld().markToReset();
    }

    /**
     * Handles collision with projectile
     */
    @Override
    public void collideWithProjectile(){
        if(!isShielding){
            die();
        }
    }
    /**
     * Update position of hero, according to many variables including whether the hero is shielding or if the hero is stunned
     */
    @Override
    public void update() {
        super.update();
        if(chargeTimer==0){
            setChargeToReady();
        }
        chargeTimer--;
        if(isShielding){
            shieldTimer -= 1;
            if(shieldTimer <= 0){
                shieldTimer = 0;
                isShielding = false;
                isStunned = true;
            }
        }
        else {
            if(shieldTimer < Set.HERO_SHIELD_TIME.getInt()&& isStunned)
            {shieldTimer += 1;}
        }
        if(isStunned){
            stunTimer -= 1;
            if(stunTimer <=0){
                isStunned = false;
                stunTimer = 250;
                shieldTimer = Set.HERO_SHIELD_TIME.getInt();
            }
        }
    }
    /**
     * Handles collision with floor
     */
    @Override
    public void collideWithFloor() {
        setyVelocity(0);
        if(!isOnPlatform){
            isOnPlatform = true;
            SoundEffect.LAND.setMedium();
            SoundEffect.LAND.play();
        }
    }

    /**
     * Nothing happen
     */
    @Override
    public void collideWithCeiling() {
    }
    /**
     * Nothing happen
     */
    @Override
    public void collideWithPlatform() {
    }
    /**
     * Nothing happen
     */
    @Override
    public void collideWith(Gravity gravity) {
    }
    /**
     * Handles hero equipping shield
     * @return whether the hero is shielding on this frame.
     */
    public boolean getShielding(){
        return isShielding;
    }

    /**
     * Sets whether the hero is ready to charge the charge shot
     */
    public void setChargeToReady(){
        readyToCharge = true;
        chargeTimer = Set.BUBBLE_CHARGE_TIME.getInt();
    }
}
