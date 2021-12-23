package pers.yuyanzhou.bubblebobble.object.gamelandform;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import pers.yuyanzhou.bubblebobble.app.App;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates.Enemy;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test wall unit
 */
@ExtendWith(ApplicationExtension.class)
class WallUnitTest {
    private LandformsUnit wall;
    private Gravity gravity;
    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
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
        gravity = new Enemy(normalLevel,1,1);
        wall = new WallUnit(normalLevel,1,1);

    }
    /**
     * Test move method, if moved game object coordinate change
     */
    @Test
    void moveObject() {
        wall.moveObject(gravity);
        double wallCenterWid = (wall.getHitbox().getMaxX() -wall.getHitbox().getWidth())/2;
        double objCenterWid = (gravity.getHitbox().getMaxX() -gravity.getHitbox().getWidth())/2;
        if(objCenterWid<wallCenterWid){
            assertEquals(wall.getX()-gravity.getWidth(),gravity.getX());
        }else if(objCenterWid>wallCenterWid){
            assertEquals(wall.getX()+wall.getWidth(),gravity.getX());
        }
    }
}