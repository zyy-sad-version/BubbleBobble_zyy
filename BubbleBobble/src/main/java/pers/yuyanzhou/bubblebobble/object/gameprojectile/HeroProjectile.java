package pers.yuyanzhou.bubblebobble.object.gameprojectile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.Boss;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.GameChar;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates.Enemy;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.util.Objects;

/**
 * HeroProjectile is creat by InputAction, that attack enemies then bubbles them.
 * HeroProjectile disappears after a period of time
 * @author YuyanZhou
 */
public class HeroProjectile extends Projectile {
    /**
     * Constructor of HeroProjectile, pass coordinate, direction, GameWorld to create HeroProjectile.
     * @param x x coordinate of HeroProjectile
     * @param y y coordinate of HeroProjectile
     * @param direction Direction of HeroProjectile
     * @param world Where the HeroProjectile need to add
     */
    public HeroProjectile(int x, int y, GameWorld world, int direction) {
        super(world, x, y, Set.HERO_PROJECTILE_SIZE.getInt(), Set.HERO_PROJECTILE_SIZE.getInt());
        setDirection(direction);
        setxVelocity(Set.HERO_PROJECTILE_SPEED.getInt());
    }

    /**
     * Handle collide with Gravity
     * @param gravity Object that collided with.
     */
    @Override
    public void collideWith(Gravity gravity) {
        if(gravity instanceof Enemy ||gravity instanceof Boss ){
            if(this.isOverlaps(gravity)&& isActive){
                ((GameChar) gravity).collideWithProjectile();
            }
        }
    }
    /**
     * Draw a HeroProjectile
     * @param imageView where image to draw on
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
    private void setPosition(ImageView imageView) {
        imageView.setX(getX());
        imageView.setY(getY());
        imageView.setFitHeight(getHeight());
        imageView.setFitWidth(getWidth());
    }
    /**
     * Nothing happen
     */
    @Override
    public void collideWithFloor() {
    }

    /**
     * When HeroProjectile collide with ceiling, set its y velocity to 0 and set its y accel to 0
     */
    @Override
    public void collideWithCeiling() {
        setyVelocity(0);
        setyAccel(0);
    }
    /**
     * Nothing happen
     */
    @Override
    public void collideWithWall() {
    }
    /**
     * Nothing happen
     */
    @Override
    public void collideWithPlatform() {
    }
}
