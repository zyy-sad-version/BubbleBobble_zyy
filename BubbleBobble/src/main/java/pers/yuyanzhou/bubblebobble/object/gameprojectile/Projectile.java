package pers.yuyanzhou.bubblebobble.object.gameprojectile;


import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * Projectile is created by Hero or Enemy or Boss.
 * Different projectiles have different effects for different characters.
 * Projectile disappears after a period of time
 * @author YuyanZhou
 */
public abstract class Projectile extends Gravity {
    protected boolean isActive;
    private int timer;

    /**
     * Constructor of Projectile, pass Gameworld, coordinate, size to create Projectile
     * @param world Where the HeroProjectile need to add
     * @param x x coordinate of HeroProjectile
     * @param y y coordinate of HeroProjectile
     * @param width Width of HeroProjectile
     * @param height Height of HeroProjectile
     */
    public Projectile(GameWorld world, int x, int y, int width, int height) {
       super(world,x,y,width,height);
        setDirection(1);
        isActive = true;
        timer = 30;
    }

    /**
     * Handles update the projectile
     */
    @Override
    public void update() {
            setY(getY()+(int) getyVelocity());
            setX((int) (getX()+(getxVelocity()*getDirection())));
            updateVelocity();
            if(getY()<Set.PROJECTILE_Y_RATE.getInt()){
                setY(Set.PROJECTILE_Y_RATE.getInt());
            }
            if(timer<0){
                isActive = false;
            }
            if(timer<Set.PROJECTILE_DISAPPEAR_TIME.getInt()){
                markToRemove();
            }
            timer -=1;
        }

    /**
     * Handles update the velocity of object
     */
    private void updateVelocity() {
        if(getxVelocity()>0){
            setxVelocity( getxVelocity() - Set.PROJECTILE_RATE.getDou());
        }else {
            setxVelocity(0);
        }
        if(Math.abs(getyVelocity())< Set.PROJECTILE_TERMINAL_VELOCITY_Y.getInt()&&!isActive){
            setyVelocity(getyVelocity()-0.1);
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
