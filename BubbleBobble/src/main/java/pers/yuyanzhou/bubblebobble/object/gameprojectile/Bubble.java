package pers.yuyanzhou.bubblebobble.object.gameprojectile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates.Enemy;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.util.Objects;

/**
 * The Bubble class handles everything with the mHero's special ability, named the bubble.
 * It begins at the hero, and grows covering the whole screen.
 * Once it collides with an enemy, that enemy is bubbled.
 * @author YuyanZhou
 */
public class Bubble extends HeroProjectile {
    private double accel;

    /**
     * Constructor of Bubble. Pass coordinate of bubble, GameWorld, direction
     * @param x x coordinate of Bubble
     * @param y y coordinate of Bubble
     * @param world Which GameWorld need to add Bubble
     * @param direction Bubble direction
     */
    public Bubble(int x, int y, GameWorld world, int direction) {
        super(x, y, world,direction);
        setxAccel(0);
        setyAccel(0);
        setHeight(0);
        setWidth(0);
        accel = 1;
    }

    /**
     * Handles collide with gravity
     * @param enemy which gravity collide with
     */
    @Override
    public void collideWith(Gravity enemy) {
        if(enemy instanceof Enemy e){
            if (this.isOverlaps(enemy)) {
                e.collideWithProjectile();
            }
        }
    }

    /**
     * Draw a bubble
     * @param imageView location that show image of landform
     */
    @Override
    public void drawOn(ImageView imageView) {
        setPosition(imageView);
        imageView.setImage(new Image(Objects.requireNonNull(this.getClass()
                .getResource("/Image/bubble.png")).toExternalForm()));
    }

    /**
     * Set the coordinate of Imageview
     * @param imageView Location that Bubble shall be drawn on
     */
    private void setPosition(ImageView imageView){
        imageView.setX(getX());
        imageView.setY(getY());
        imageView.setFitHeight(getHeight());
        imageView.setFitWidth(getWidth());
    }
    /**
     * Handles update Bubble, When its size larger than window size, it shall be marked to remove.
     */
    @Override
    public void update() {
        if(getWidth() >= Set.HEIGHT.getInt()*Set.UNIT_SIZE.getInt()){
            markToRemove();
        }
        setX((int) (getX()-(accel/2)));
        setY((int) (getY()-(accel/2)));
        setWidth((int) (getWidth()+accel));
        setHeight((int) (getHeight()+accel));
        accel+=0.1;
    }

}
