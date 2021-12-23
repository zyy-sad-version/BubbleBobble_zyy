package pers.yuyanzhou.bubblebobble.object.gameprops;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.net.URL;

/**
 * Fruit create by Enemy when its dead.
 * Fruit is able to pick up by Hero.
 * Fruit falls onto the PlatformUnit or FloorUnit and will not disappear
 * @author YuyanZhou
 */
public class Fruit extends GameProp {
    /**
     * Constructor of Fruit, pass GameWorld, coordinate to create Fruit
     * @param world Where the Fruit need to add
     * @param y y coordinate of Fruit
     * @param x x coordinate of Fruit
     */
    public Fruit(GameWorld world, int y, int x) {
        super(world, x, y, Set.FRUIT_SIZE.getInt(), Set.FRUIT_SIZE.getInt());
        setTerminalYVelocity(Set.FRUIT_TERMINAL_VELOCITY_Y.getInt());
    }

    /**
     * Draw a Fruit
     * @param imageView where image to draw on
     */
    @Override
    public void drawOn(ImageView imageView){
        URL url = this.getClass().getResource("/Image/apple.gif");
        assert url != null;
        Image image = new Image(url.toExternalForm(),getWidth(),getWidth(),true,true);
        imageView.setImage(image);
    }
}
