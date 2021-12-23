package pers.yuyanzhou.bubblebobble.object.gamecharacter;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import pers.yuyanzhou.bubblebobble.app.App;
import pers.yuyanzhou.bubblebobble.object.gamelandform.WallUnit;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
/**
 * Test Boss game object
 */
class BossTest {
    private Boss boss ;
    private ImageView imageView;
    private LandformsUnit land;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     * @param stage  Will be injected by the test runner.
     * @throws IOException Loading fxml file exception
     */
    @Start
    private void start(Stage stage) throws IOException{
        App app = new App(stage);
        app.setRoot("game01-view");
    }

    /**
     * Will be called  before each test mathod
     */
    @BeforeEach
    void setUp() {
        imageView = new ImageView();
        GameWorld normalLevel = new NormalLevel(Set.WIDTH.ordinal() * Set.UNIT_SIZE.getInt(), Set.HEIGHT.ordinal() * Set.UNIT_SIZE.getInt(), 1);
        boss = new Boss(normalLevel,1,1);
        land = new WallUnit(normalLevel,1,1);
    }

    /**
     * Test coordinate of imageview
     */
    @Test
    void drawOn() {
        boss.drawOn(imageView);
        assertEquals(boss.getX(),imageView.getX());
        assertEquals(boss.getY(),imageView.getY());
    }

    /**
     * Test action that Boss collides with wall
     */
    @Test
    void collideWithWall() {
        int direction = boss.getDirection();
        boss.reverseDirection();
        assertEquals(-direction,boss.getDirection());
    }

    /**
     * Test action that Boss collides with floor
     */
    @Test
    void collideWithFloor() {
        boss.collideWithFloor();
        assertEquals(0,boss.getyVelocity());
    }

    /**
     * Test action that Boss collides with ceiling
     */
    @Test
    void collideWithCeiling() {
        boss.collideWithCeiling();
        assertEquals(0,boss.getyVelocity());
    }

    /**
     * Test action that Boss collides with land
     */
    @Test
    void collideWithLand() {
       boss.collideWithLand(land);
        if (boss.isOverlaps(land)) {
            assertEquals(0,boss.getxVelocity());
            assertEquals(0,boss.getyAccel());
        }
    }
}