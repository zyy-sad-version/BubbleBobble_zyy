package pers.yuyanzhou.bubblebobble.object.gameprojectile;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import pers.yuyanzhou.bubblebobble.app.App;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test hero projectile gravity
 */
@ExtendWith(ApplicationExtension.class)
class HeroProjectileTest {
    private ImageView imageView;
    private HeroProjectile projectile;
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
        imageView = new ImageView();
        projectile = new HeroProjectile(1,1,normalLevel,1);
    }
    /**
     *  Test coordinate of imageview
     */
    @Test
    void drawOn() {
        projectile.drawOn(imageView);
        assertEquals(projectile.getX(),imageView.getX());
        assertEquals(projectile.getY(),imageView.getY());
    }

    /**
     * Test collide with ceiling
     */
    @Test
    void collideWithCeiling() {
        projectile.collideWithCeiling();
        assertEquals(0,projectile.getyVelocity());
        assertEquals(0,projectile.getyAccel());
    }

}