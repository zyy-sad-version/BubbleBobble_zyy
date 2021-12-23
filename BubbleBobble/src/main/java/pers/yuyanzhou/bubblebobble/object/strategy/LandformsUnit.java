package pers.yuyanzhou.bubblebobble.object.strategy;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.net.URL;

/**
 * LandformsUnit implement GameObject interface.
 * LandformsUnit is a GameObject whose coordinates do not change
 * @author YuyanZhou
 */
public abstract class LandformsUnit implements GameObject {
    private final GameWorld world;
    private int x,y;
    private final int width;
    private final int height;
    private boolean canRemove;

    /**
     * Constructor of LandformsUnit, pass GameWorld, size, coordinate to create LandformsUnit
     * @param world Where the LandformsUnit need to add
     * @param colNum value to calculate x coordinate of LandformsUnit
     * @param rowNum value to calculate y coordinate of LandformsUnit
     * @param width width of LandformsUnit
     * @param height height of LandformsUnit
     */
    public LandformsUnit(GameWorld world, int colNum, int rowNum, int width, int height) {
        this.world = world;
        x=colNum * Set.UNIT_SIZE.getInt();
        y = rowNum* Set.UNIT_SIZE.getInt();
        this.width = width;
        this.height = height;
        canRemove = false;
    }

    /**
     * Get Collision Size of Gravity.
     * @return Collision Size of Gravity.
     */
    @Override
public Rectangle2D getHitbox(){
    return new Rectangle2D(x,y,width,height);
}

    /**
     * Check whether the two GameObjects overlap
     * @param object object collide with
     * @return A boolean that true value indicate overlap happened, otherwise, overlap did not happen
     */
    @Override
public boolean isOverlaps(GameObject object){
    return getHitbox().intersects(object.getHitbox());
}

    /**
     * Move game object to a coordinate
     * true indicate this game object can be removed, otherwise it cannot be removed
     */
    @Override
public void markToRemove(){
    canRemove =true;
}
    /**
     * Draw the lands on game world
     * @param imageView location that show image of landform
     */
    @Override
    public void drawOn(ImageView imageView) {
        URL url = this.getClass().getResource("/Image/Wall.png");
        assert url != null;
        Image image = new Image(url.toExternalForm(),getWidth(),getHeight(),false,true);
        imageView.setX(getX());
        imageView.setY(getY());
        imageView.setImage(image);
    }

    /**
     * handle colliding with gravity object
     * @param gravity Object collided with
     */
    public  abstract void collideWith(Gravity gravity);
    /**
     * handles moving object
     * @param object needed to be moved
     */
    public abstract void moveObject(Gravity object);
    public GameWorld getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isCanRemove() {
        return canRemove;
    }
}