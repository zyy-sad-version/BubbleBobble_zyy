package pers.yuyanzhou.bubblebobble.object.gamecharacter;


import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * GameChar inherited from Gravity. This abstract class define an abstract method collideWithProjectile() to handle game character collide with other game character's projectile.
 * @author YuyanZhou
 */
public abstract class GameChar extends Gravity {
    public GameChar(GameWorld world, int colNum, int rowNum, int width, int height) {
super(world,colNum * Set.UNIT_SIZE.getInt(),rowNum*Set.UNIT_SIZE.getInt(),width,height);

    }
    /**
     * Handles game character collide with projectile
     */
    public abstract void collideWithProjectile();
}
