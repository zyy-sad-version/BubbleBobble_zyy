package pers.yuyanzhou.bubblebobble.object.gameprojectile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.util.Objects;

/**
 * Fire created by Boss
 * Fire inheritance from EnemyProjectile.
 * Fire collide with Hero would kill Hero.
 * Fire disappears after a period of time
 * @author YuyanZhou
 */
public class Fire extends EnemyProjectile{
    /**
     * Constructor of Fire, pass coordinate, direction, GameWorld to create Fire.
     *
     * @param x         x coordinate of EnemyProjectile
     * @param y         y coordinate of EnemyProjectile
     * @param direction Direction of EnemyProjectile
     * @param world     Where the EnemyProjectile need to add
     */
    public Fire(int x, int y, int direction, GameWorld world) {
        super(x, y, direction, world);
        setWidth(Set.FIRE_SIZE.getInt());
        setHeight(Set.FIRE_SIZE.getInt());
        setxVelocity(Set.FIRE_SPEED.getInt());
    }

    /**
     * Draw a fire
     * @param imageView where image to draw on
     */
    @Override
    public void drawOn(ImageView imageView) {
        String imagePath;
        if(getDirection()==1){
            imagePath = "/Image/FireRight.png";
        }
        else{
            imagePath = "/Image/FireLeft.png";
        }
        setPosition(imageView);
        imageView.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(this.getClass()
                .getResource(imagePath)).toExternalForm())));
    }
    /**
     * Set the coordinate of Imageview
     * @param imageView Location that Bubble shall be drawn on
     */
    private void setPosition(ImageView imageView) {
        imageView.setY(getY());
        imageView.setX(getX());
        imageView.setFitHeight(getHeight());
        imageView.setFitWidth(getWidth());
    }

    @Override
    public void update() {

        setY(getY());
        setX((int) (getX()+(getxVelocity()*getDirection())));
        if(getTimer()<Set.FIRE_ACTIVE_TIME.getInt())
        {
            isActive = false;
        }
        if(getTimer()<Set.FIRE_DISAPPEAR_TIME.getInt()){
            markToRemove();
        }
        setTimer(getTimer()-1);
    }
}
