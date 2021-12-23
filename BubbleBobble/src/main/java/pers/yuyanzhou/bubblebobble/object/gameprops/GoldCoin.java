package pers.yuyanzhou.bubblebobble.object.gameprops;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.net.URL;

/**
 * GoldCoin create by Boss when its dead.
 * GoldCoin is able to pick up by Hero.
 * GoldCoin falls onto the PlatformUnit or FloorUnit and will not disappear
 * @author YuyanZhou
 */
public class GoldCoin extends GameProp{
    /**
     * Constructor of GoldCoin, pass GameWorld, coordinate to create GoldCoin
     * @param world Where the GoldCoin need to add
     * @param y y coordinate of GoldCoin
     * @param x x coordinate of GoldCoin
     */
    public GoldCoin(GameWorld world, int y, int x) {
        super(world, x, y, Set.GOLD_SIZE.getInt(), Set.GOLD_SIZE.getInt());
        setTerminalYVelocity(Set.GOLD_TERMINAL_VELOCITY_Y.getInt());
    }

    /**
     * Draw a GoldCoin
     * @param imageView where image to draw on
     */
    @Override
    public void drawOn(ImageView imageView) {
        URL url = this.getClass().getResource("/Image/gold.gif");
        assert url != null;
        Image image = new Image(url.toExternalForm(),getWidth(),getWidth(),true,false);
        imageView.setImage(image);
    }

}
