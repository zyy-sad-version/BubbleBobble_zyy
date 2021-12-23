package pers.yuyanzhou.bubblebobble.object.gamecharacter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.gamelandform.WallUnit;
import pers.yuyanzhou.bubblebobble.object.gameprojectile.Fire;
import pers.yuyanzhou.bubblebobble.object.gameprops.GoldCoin;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.net.URL;

/**
 * A Boss is a non-controllable GameObject that kills the Hero whenever it or its projectile comes in contact.
 * Boss are not able to be bubbled and free themselves from these bubbles after a period of time.
 * Boss change direction at random intervals, when hitting a wall, and when hitting the Hero's shield.
 * Boss jump at random intervals as well.
 * Boss has 100 health point. Health point decrease 10 point when Boss collide with hero projectile then Boss gets invincible for a while.
 * @author YuyanZhou
 */
public class Boss extends GameChar {
private int health,timer;
private boolean isOnPlatform;
    /**
     * constructor of boss, init world, size
     * @param world which world Boss need to be added
     * @param colNum value to calculate x coordinate of Boss
     * @param rowNum value to calculate y coordinate of Boss
     */
    public Boss(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.BOSS_WIDTH.getInt(),Set.BOSS_HEIGHT.getInt());
        isOnPlatform = false;
        setxAccel(1);
        setTerminalXVelocity(Set.ENEMY_TERMINAL_VELOCITY_X.getInt());
        setDirection(1);
        timer = Set.BOSS_HEALTH_TIMER.getInt();
        health = 100;
    }

    /**
     * draws enemy based on different situation
     * @param imageView Boss enemy need to be drawn on
     */
    @Override
    public void drawOn(ImageView imageView) {
        URL url;
        if(getDirection() == -1){
            url = this.getClass().getResource("/Image/BossEnemyLeft.png");
        }else {
            url = this.getClass().getResource("/Image/BossEnemyRight.png");
        }
        assert url !=null;
        Image image = new Image(url.toExternalForm(),getHeight(),getWidth(),true,true);
        imageView.setImage(image);
        imageView.setX(getX());
        imageView.setY(getY());
    }

    @Override
    public void collideWithProjectile() {
       if(timer==0){
           health-=20;
           timer = Set.BOSS_HEALTH_TIMER.getInt();
       }
        timer--;
    }

    @Override
    public void collideWithWall() {
            reverseDirection();
    }

    /**
     * Handles Boss collide with floor unit
     */
    @Override
    public void collideWithFloor() {
        setyVelocity(0);
        if (!isOnPlatform) {
            isOnPlatform =true;
        }
    }

    /**
     * Handles Boss collide with ceiling unit
     */
    @Override
    public void collideWithCeiling() {
    setyVelocity(0);
    }

    /**
     * Nothing happen
     */
    @Override
    public void collideWithPlatform() {
    }

    /**
     * Handles Boss collide with Gravity
     * @param gravity Object that collided with.
     */
    @Override
    public void collideWith(Gravity gravity) {
        if(gravity instanceof Hero hero){
            if (this.isOverlaps(hero)){
                hero.collideWithMock();
                if(hero.getShielding()){
                    reverseDirection();
                }
            }
        }
    }
    public void collideWithLand(LandformsUnit landformsUnit){
        if (landformsUnit instanceof WallUnit){
            if(this.isOverlaps(landformsUnit))
            {
                setxVelocity(0);
                setyAccel(0);
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if(health == 0){
            die();
        }else {
            if(Math.random()<Set.BOSS_CHANG_MOVEMENT_CHANCE.getDou()){
                reverseDirection();
            }
            if(Math.random()<Set.BOSS_SHOOT_PROJECTILE_CHANCE.getDou()){
                shootProjectile();
            }if(Math.random()<Set.ENEMY_SHOOT_PROJECTILE_CHANCE.getDou()){
                jump();
            }
        }
    }

    private void die() {
        getWorld().addOtherGravity(new GoldCoin(getWorld(), getY(), getX()));
        markToRemove();
    }
    private void shootProjectile(){
        getWorld().addOtherGravity(new Fire(getX(),getY(),getDirection(),getWorld()));
    }
    private void jump(){
        if (isOnPlatform) {
            setY(getY()-1);
            setyVelocity(-Set.ENEMY_JUMP_SPEED.getInt());
        }
    }
}
