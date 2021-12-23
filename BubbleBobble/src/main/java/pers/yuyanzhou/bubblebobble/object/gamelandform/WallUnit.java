package pers.yuyanzhou.bubblebobble.object.gamelandform;


import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * WallUnit inherits from LandformUnits.
 * WallUnit is the floor of the game world, when Hero, Enemy, Boss collide with it, move the game character left or right.
 * @author YuyanZhou
 */
public class WallUnit extends LandformsUnit {
    /**
     * Draw a wall unit
     * @param imageView location that show image of landform
     */
    @Override
    public void drawOn(ImageView imageView) {
        super.drawOn(imageView);
    }

    /**
     * Handles collide with gravity
     * @param object Collided with
     */
    @Override
    public void collideWith(Gravity object) {
        if(this.isOverlaps(object)){
           moveObject(object);
        }
    }

    /**
     * Handles moving object
     * @param object needed to be moved
     */
    @Override
    public void moveObject(Gravity object) {
        double wallCenterWid = (this.getHitbox().getMaxX() -this.getHitbox().getWidth())/2;
        double objCenterWid = (object.getHitbox().getMaxX() -object.getHitbox().getWidth())/2;
        if(objCenterWid<wallCenterWid){
            object.moveTo(new Point2D(this.getX()-object.getWidth(),object.getY()));
            object.collideWithWall();
        }
        else if(objCenterWid>wallCenterWid){
            object.moveTo(new Point2D(this.getX()+this.getWidth(),object.getY()));
            object.collideWithWall();
        }
        else {
            object.moveTo(new Point2D(this.getX()+this.getWidth(),object.getY()));
        }
    }

    /**
     * Constructor of WallUnit, pass GameWorld, coordinate to create it.
     * @param world GameWorld where need to build WallUnit.
     * @param colNum x coordinate of create WallUnit
     * @param rowNum y coordinate of create WallUnit
     */
    public WallUnit(GameWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, Set.UNIT_SIZE.getInt(),Set.UNIT_SIZE.getInt());
    }
}
