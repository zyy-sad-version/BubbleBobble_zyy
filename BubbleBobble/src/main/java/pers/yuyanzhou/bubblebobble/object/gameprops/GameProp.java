package pers.yuyanzhou.bubblebobble.object.gameprops;

import pers.yuyanzhou.bubblebobble.object.gamecharacter.Hero;
import pers.yuyanzhou.bubblebobble.object.gameprops.propstates.PropState;
import pers.yuyanzhou.bubblebobble.object.gameprops.propstates.UnreadyState;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;

/**
 * GameProp is created by death of Enemy or Boss.
 * It can be picked up by Hero.
 * @author YuyanZhou
 */
public abstract class GameProp extends Gravity {
private PropState state;

    /**
     * Constructor of GameProp, pass GameWorld, coordinate, size to create GameProp
     * @param world Where the GameProp need to add
     * @param x x coordinate of GameProp
     * @param y y coordinate of GameProp
     * @param width width of GameProp
     * @param height height of GameProp
     */
    public GameProp(GameWorld world, int x, int y, int width, int height) {
        super(world,x,y,width,height);
        state = new UnreadyState(this);
    }

    /**
     * Nothing happen
     */
    @Override
    public void collideWithCeiling() {}
    /**
     * Nothing happen
     */
    @Override
    public void collideWithWall() {}

    /**
     * Handles collide with Gravity
     * @param gameChar Collided with
     */
    @Override
    public  void collideWith(Gravity gameChar){
        if(gameChar instanceof Hero hero){
            if(this.isOverlaps(hero)){
                state.collideWithHero();
            }
        }
    }

    /**
     * Handle collide with floor
     */
    @Override
    public void collideWithFloor(){
        state.collideWithLand();
    }

    /**
     * Handle collide with platform
     */
    @Override
    public void collideWithPlatform() {
        state.collideWithLand();
    }

    /**
     * Set state of GameProp
     * @param state Which state need to set on
     */
    public void setState(PropState state){
        this.state = state;
    }
}
