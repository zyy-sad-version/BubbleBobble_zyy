package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.GameChar;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.Hero;
import pers.yuyanzhou.bubblebobble.object.gamelandform.PlatformUnit;
import pers.yuyanzhou.bubblebobble.object.gamelandform.WallUnit;
import pers.yuyanzhou.bubblebobble.object.gameprojectile.EnemyProjectile;
import pers.yuyanzhou.bubblebobble.object.gameprops.Fruit;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

import java.net.URL;

/**
 * An Enemy is a non-controllable GameObject that kills the Hero whenever it or its projectile comes in contact.
 * Enemies are able to be bubbled and free themselves from these bubbles after a period of time.
 * Enemies change direction at random intervals, when hitting a wall, and when hitting the main.GameObject.GameCharacters.Hero's shield.
 * Enemies jump at random intervals as well.
 */
public class Enemy extends GameChar {
    private int timer;
    private int turningAwayCount;
    protected double jumpSpeed;
    protected EnemyState state;
    /**
     * Constructor of enemy, init world, size, jump speed
     * @param world which world enemy need to be drawn on
     * @param colNum x-coordinate of an enemy
     * @param rowNum y-coordinate of an enemy
     */
    public Enemy(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.ENEMY_WIDTH.getInt(), Set.ENEMY_HEIGHT.getInt());
        state = new NormalState(this);
        jumpSpeed = Set.ENEMY_JUMP_SPEED.getInt();
        setTerminalXVelocity(Set.ENEMY_TERMINAL_VELOCITY_X.getInt());
        setxAccel(1);
        setDirection(1);
        if(Math.random()<0.5) {
            reverseDirection();
        }
        timer = Set.ENEMY_BUBBLED_FRAMES.getInt();
    }

    /**
     * Handles enemy what to do on collision with a wall
     */
    @Override
    public void collideWithWall() {
    reverseDirection();
    }
    /**
     * Handles floor collision
     */
    @Override
    public void collideWithFloor() {
        state.collideWithPlatform();
    }
    /**
     * Handles ceiling collision
     */
    @Override
    public void collideWithCeiling() {
        setyVelocity(0);
    }

    /**
     * Handles platform collision, nothing happen
     */
    @Override
    public void collideWithPlatform() {
    }
    /**
     * Draws enemy based on different situation
     * @param imageView enemy needed to be drawn on
     */
    @Override
    public void drawOn(ImageView imageView) {
    URL url;
    if(getDirection() == -1){
        if (state instanceof BubbledState) {
            url = this.getClass().getResource("/Image/ZenChanLeftBubbled.png");
        }else {
            url = this.getClass().getResource("/Image/ZenChanLeft.png");
        }
    }else {
        if(state instanceof BubbledState){
            url = this.getClass().getResource("/Image/ZenChanRightBubbled.png");
        }else {
            url = this.getClass().getResource("/Image/ZenChanRight.png");
        }
    }
        assert url != null;
        Image image = new Image(url.toExternalForm(),getHeight(),getWidth(),true,true);
        imageView.setImage(image);
        imageView.setX(getX());
        imageView.setY(getY());
    }
    /**
     * Handles jump
     */
    public void jump(){
        state.jump();
    }
    /**
     * Handle enemy shoot projectile
     */
    protected void shootProjectile(){
        SoundEffect.SHOOT.setMedium();
        SoundEffect.SHOOT.play();
        getWorld().addOtherGravity(new EnemyProjectile(getX(),getY(),getDirection(),getWorld()));
    }
    /**
     * Handles what to do if hit with a projectile by the hero
     */
    @Override
    public void collideWithProjectile(){
        state.beBubbled();
    }
    /**
     * Handles collision with hero and what to do
     * @param gravity gravity object that enemy collides with
     */
    @Override
    public void collideWith(Gravity gravity) {
        if (gravity instanceof Hero hero) {
            if (this.isOverlaps(hero)) {
                if(!(state instanceof BubbledState)){
                    hero.collideWithMock();
                        if (hero.getShielding()){
                            state.collideWithShield();
                        }
                }
                else if (!isCanRemove()) {
                    SoundEffect.POP.play();
                    die();
                }
            }
            if(state instanceof TurnAwayState){
                state.collideWithShield();
            }
        }
    }
    /**
     * Handles unit collision
     * @param landformsUnit Thing happened when enemy collide with land likes platform, wall, celling, floor
     */
    public void collideWithLand(LandformsUnit landformsUnit){
         if(landformsUnit instanceof WallUnit){
            if(this.isOverlaps(landformsUnit)){
                state.collideWithWall();
            }
        }
        else if(landformsUnit instanceof PlatformUnit){
            double top = this.getY()+(double)(this.getHeight()/2);
            if(this.isOverlaps(landformsUnit)){
                    if(top<(landformsUnit.getHeight()+ landformsUnit.getY()))
                    { state.collideWithCeiAndFloor();
                    }
                    state.collideWithWall();
            }
        }
    }
    /**
     * Updates enemy, handling movement
     */
    @Override
    public void update() {
        super.update();
        state.update();
    }
    /**
     * Handles enemy die
     */
    private void die(){
        getWorld().addOtherGravity(new Fruit(getWorld(), getY(), getX()));
        markToRemove();
    }
    /**
     * Set timer value
     * @param time value set into timer
     */
    public void setTimer(int time){
        this.timer = time;
    }
    /**
     * Set Turning Away count
     * @param turningAwayCount value set into turningAwayCount
     */
    public void setTurnAwayCount(int turningAwayCount){
        this.turningAwayCount = turningAwayCount;
    }
    /**
     * Get timer value
     * @return value of timer
     */
    public int getTimer() {
        return timer;
    }

    /**
     * Get turning away count value
     * @return value of turning away count value
     */
    public int getTurningAwayCount() {
        return turningAwayCount;
    }
}
