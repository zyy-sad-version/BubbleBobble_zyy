package pers.yuyanzhou.bubblebobble.object.gamecharacter;


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

@ExtendWith(ApplicationExtension.class)
/**
 *Test Hero game object
 */
class HeroTest {
    private GameWorld normalLevel ;
    private Hero hero ;
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
    /**
     * Will be called  before each test mathod
     */
    @BeforeEach
    void setup() {
        imageView = new ImageView();
        normalLevel =  new NormalLevel(Set.WIDTH.ordinal()*Set.UNIT_SIZE.getInt(),Set.HEIGHT.ordinal()*Set.UNIT_SIZE.getInt(),1);
        hero = new Hero(normalLevel,1,1);
}
    /**
     *  Test coordinate of imageview
     */
    @Test
    void drawOn() {
        hero.drawOn(imageView);
        assertEquals(hero.getX(),imageView.getX());
        assertEquals(hero.getY(),imageView.getY());
    }

    /**
     * Test jumping action
     */
    @Test
    void jump() {
        hero.isOnPlatform = true;
        hero.jump();
        assertFalse(hero.isOnPlatform);
        assertEquals(-Set.HERO_JUMP_SPEED.getInt(),hero.getyVelocity());
    }
    /**
     * Test action that Hero collides with wall
     */
    @Test
    void collideWithWall() {
        hero.collideWithWall();
        assertEquals(0,hero.getxVelocity());
        assertEquals(0,hero.getxAccel());
    }
    /**
     * Test action that Hero collides with floor
     */
    @Test
    void collideWithFloor() {
        hero.collideWithFloor();
        assertEquals(0,hero.getyVelocity());
    }

    /**
     * Test set charge to ready method
     */
    @Test
    void setChargeToReady() {
        hero.setChargeToReady();
       assertEquals(Set.BUBBLE_CHARGE_TIME.getInt(),hero.chargeTimer);
    }
}