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
 * Test platform unit
 */
@ExtendWith(ApplicationExtension.class)
class PlatformUnitTest {
private LandformsUnit platform;
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
        platform = new PlatformUnit(normalLevel,1,1);
    }
    /**
     * Test move method, if moved game object coordinate change
     */
    @Test
    void moveObject() {
        platform.moveObject(gravity);
        double top = gravity.getY();
        double bottom = top + gravity.getHeight();

        if(bottom < platform.getY()+platform.getHeight()){
            assertEquals(platform.getY()-gravity.getHeight(),gravity.getY());
        }
        if(top> platform.getY()){
            assertEquals(platform.getY()+ platform.getHeight(),gravity.getY());
        }
    }
}