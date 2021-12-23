package pers.yuyanzhou.bubblebobble.object.strategy;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * GameObject interface define some methods for game object properties
 * @author YuyanZhou
 */
public interface GameObject {
    /**
     * get the hitbox of the game object to handle collision
     * @return Hitbox of this game object
     */
    Rectangle2D getHitbox();
    /**
     *Determine whether the game object overlaps with the incoming game object
     * @param object object collide with
     * @return whether this game object overlap with another object
     */
     boolean isOverlaps(GameObject object);

    /**
     * sets whether something can be removed
     */
     void markToRemove();
    /**
     * drawn game object on game world
     * @param imageView where image to draw on
     */
      void drawOn(ImageView imageView);

}
