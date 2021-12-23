package pers.yuyanzhou.bubblebobble.object.gamelandform;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * PlatformUnit inherits from LandformUnits.
 * PlatformUnit is the floor of the game world, when Hero, Enemy, Boss collide with it, move the game character up or down.
 * @author YuyanZhou
 */
public class PlatformUnit extends LandformsUnit {
    /**
     * Drawn a platform unit
     * @param imageView location that show image of landform
     */
    @Override
    public void drawOn(ImageView imageView) {
        super.drawOn(imageView);
    }

    /**
     * Handles collide with Gravity
     * @param object Collided with
     */
    @Override
    public void collideWith(Gravity object) {
    if(this.isOverlaps(object)&& object.getyVelocity()>0){
            moveObject(object);
    }
    }

    /**
     * Handles moving object
     * @param object needed to be moved
     */
    @Override
    public void moveObject(Gravity object) {
        double top = object.getY();
        double bottom = top + object.getHeight();
        if(bottom<this.getY() + this.getHeight()){
            object.moveTo(new Point2D(object.getX(), this.getY()-object.getHeight()));
            object.setyVelocity(0);
            object.collideWithPlatform();
            object.collideWithFloor();
        }
        if(top>this.getY()){
            object.moveTo(new Point2D(object.getX(), this.getY()+this.getHeight()));
            object.collideWithCeiling();
        }

    }
    /**
     * Constructor of PlatformUnit, pass GameWorld, coordinate to create it.
     * @param world GameWorld where need to build PlatformUnit.
     * @param colNum x coordinate of create PlatformUnit
     * @param rowNum y coordinate of create PlatformUnit
     */
    public PlatformUnit(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.UNIT_SIZE.getInt(),Set.UNIT_SIZE.getInt());

    }
}
