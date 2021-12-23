package pers.yuyanzhou.bubblebobble.object.gameprojectile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.Hero;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.util.Objects;

/**
 * EnemyProjectile inheritance from Projectile.
 * EnemyProjectile collide with Hero would kill Hero.
 * EnemyProjectile disappears after a period of time
 * @author YuyanZhou
 */
public class EnemyProjectile extends Projectile {
    /**
     * Constructor of EnemyProjectile, pass coordinate, direction, GameWorld to create EnemyProjectile.
     * @param x x coordinate of EnemyProjectile
     * @param y y coordinate of EnemyProjectile
     * @param direction Direction of EnemyProjectile
     * @param world Where the EnemyProjectile need to add
     */
    public EnemyProjectile(int x, int y, int direction , GameWorld world) {
        super(world, x, y, Set.ENEMY_PROJECTILE_SIZE.getInt(),Set.ENEMY_PROJECTILE_SIZE.getInt());
        setDirection(direction);
        setxVelocity(Set.ENEMY_PROJECTILE_SPEED.getInt());
    }

    /**
     * Draw a EnemyProjectile
     * @param imageView where image to draw on
     */
    @Override
    public void drawOn(ImageView imageView) {
        String imagePath;
        imagePath = "/Image/enemyProjectile.png";
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

    /**
     * Handle collide with Gravity
     * @param gravity Object that collided with.
     */
    @Override
    public void collideWith(Gravity gravity) {
        if(gravity instanceof Hero hero){
            if(this.isOverlaps(hero)&&isActive){
                hero.collideWithProjectile();
            }
        }
    }

    /**
     * Nothing happen
     */
    @Override
    public void collideWithFloor() {
    }
    /**
     * Nothing happen
     */
    @Override
    public void collideWithCeiling() {
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
