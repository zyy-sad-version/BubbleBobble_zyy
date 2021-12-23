package pers.yuyanzhou.bubblebobble.object.gamelandform;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * FloorUnit inherits from LandformUnits.
 * FloorUnit is the floor of the game world, when Hero, Enemy, Boss collide with it, move the game character up.
 * @author YuyanZhou
 */
public class FloorUnit extends LandformsUnit {
    /**
     * Constructor of FloorUnit, pass GameWorld, coordinate to create it.
     * @param world GameWorld where need to build FloorUnit.
     * @param colNum x coordinate of create FloorUnit unit
     * @param rowNum y coordinate of create FloorUnit unit
     */
    public FloorUnit(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.UNIT_SIZE.getInt(),Set.UNIT_SIZE.getInt());
    }

    /**
     * Draw a FloorUnit
     * @param imageView location that show image of landform
     */
    @Override
    public void drawOn(ImageView imageView) {
        super.drawOn(imageView);
    }

    /**
     * Handles collide with Garvity
     * @param object Collided with
     */
    @Override
    public void collideWith(Gravity object) {
        double top = object.getY() + object.getHeight();
        if(this.isOverlaps(object)&& top <= this.getY()+this.getHeight())
        {
            moveObject(object);
        }
    }

    /**
     * Handles moving object
     * @param object needed to be moved
     */
    @Override
    public void moveObject(Gravity object) {
        object.moveTo(new Point2D(object.getX(),this.getY()-object.getHeight()));
        object.collideWithFloor();
    }
}
