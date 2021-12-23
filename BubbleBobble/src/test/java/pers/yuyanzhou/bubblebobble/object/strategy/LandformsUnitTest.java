package pers.yuyanzhou.bubblebobble.object.strategy;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import pers.yuyanzhou.bubblebobble.app.App;
import pers.yuyanzhou.bubblebobble.object.gamelandform.FloorUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test abstract LandformUnits class
 */
@ExtendWith(ApplicationExtension.class)
class LandformsUnitTest {
private LandformsUnit land;
private LandformsUnit collideLand;
private ImageView imageView;
    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     * @param stage  Will be injected by the test runner.
     * @throws IOException Loading fxml file exception
     */
    @Start
    private void start(Stage stage) throws IOException {
        App app = new App(stage);
        app.setRoot("game01-view");
    }
    @BeforeEach
    void setUp() {
    GameWorld normalLevel = new NormalLevel(Set.WIDTH.getInt() * Set.UNIT_SIZE.getInt(), Set.HEIGHT.ordinal() * Set.UNIT_SIZE.getInt(), 1);
    land = new FloorUnit(normalLevel,1,1);
    collideLand = new FloorUnit(normalLevel,1,1);
    imageView = new ImageView();
    }

    /**
     * Test hit box correctness
     */
    @Test
    void getHitbox() {
      assertEquals(new Rectangle2D(land.getX(),
              land.getY(), land.getWidth(),
              land.getHeight()),land.getHitbox());
    }
    @Test
    void isOverlaps() {
        assertTrue(land.isOverlaps(collideLand));
    }

    @Test
    void markToRemove() {
    land.markToRemove();
    assertTrue(land.isCanRemove());
    }

    /**
     * test coordinate of imageview
     */
    @Test
    void drawOn() {
        land.drawOn(imageView);
        assertEquals(land.getX(),imageView.getX());
        assertEquals(land.getY(),imageView.getY());
    }

}