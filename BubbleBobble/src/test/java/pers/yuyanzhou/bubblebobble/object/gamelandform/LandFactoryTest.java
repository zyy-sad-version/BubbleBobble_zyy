package pers.yuyanzhou.bubblebobble.object.gamelandform;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import pers.yuyanzhou.bubblebobble.app.App;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test factory that create LandformUnits
 */
@ExtendWith(ApplicationExtension.class)
class LandFactoryTest {
    private LandformsUnit wallUnit;
    private LandformsUnit platformUnit;
    private LandformsUnit ceilingUnit;
    private LandformsUnit floorUnit;
    private LandFactory factory;
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
    void setUp(){
        GameWorld normalLevel = new NormalLevel(Set.WIDTH.getInt() * Set.UNIT_SIZE.getInt(), Set.HEIGHT.ordinal() * Set.UNIT_SIZE.getInt(), 1);
        wallUnit = new WallUnit(normalLevel,1,1);
        platformUnit = new PlatformUnit(normalLevel,5,5);
        ceilingUnit = new CeilingUnit(normalLevel,10,10);
        floorUnit = new FloorUnit(normalLevel, 15,15);
        factory = new LandFactory(normalLevel);
    }

    /**
     * Test LandformUnits creation correctness
     */
    @Test
    void getLand() {
    assertEquals(factory.getLand("floor",15,15).getHitbox(),floorUnit.getHitbox());
    assertEquals(factory.getLand("wall",1,1).getHitbox(),wallUnit.getHitbox());
    assertEquals(factory.getLand("platform",5,5).getHitbox(),platformUnit.getHitbox());
    assertEquals(factory.getLand("ceiling",10,10).getHitbox(),ceilingUnit.getHitbox());
    }
}