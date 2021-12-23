package pers.yuyanzhou.bubblebobble.object.gamelandform;


import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * CeilingUnit inherits from LandformUnits.
 * CeilingUnit is the ceiling of the game world, when Hero, Enemy, Boss collide with it, move the game character down.
 * @author YuyanZhou
 */
public class CeilingUnit extends LandformsUnit {
    /**
     * Constructor of CeilingUnit, pass GameWorld, coordinate to create it.
     * @param world GameWorld where need to build CeilingUnit.
     * @param colNum x coordinate of create CeilingUnit unit
     * @param rowNum y coordinate of create CeilingUnit unit
     */
    public CeilingUnit(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.UNIT_SIZE.getInt(),Set.UNIT_SIZE.getInt());
    }

    /**
     * Draw a ceiling unit
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
        if(this.isOverlaps(object)){
            moveObject(object);
            object.collideWithCeiling();
        }
    }
    /**
     * Handles moving object
     * @param object
     */
    @Override
    public void moveObject(Gravity object) {
        object.moveTo(new Point2D(object.getX(),this.getY()+ object.getHeight()));
        object.collideWithCeiling();
    }
}
