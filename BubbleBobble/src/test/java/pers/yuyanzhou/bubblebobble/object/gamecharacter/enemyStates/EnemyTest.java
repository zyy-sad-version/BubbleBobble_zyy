package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

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
class EnemyTest {
@Start
private void start(Stage stage) throws IOException {
    App app = new App(stage);
    app.setRoot("game01-view");
}
private Enemy enemy;
private ImageView imageView;

    @BeforeEach
    void setUp(){
    imageView = new ImageView();
    GameWorld normalLevel = new NormalLevel(Set.WIDTH.ordinal() * Set.UNIT_SIZE.getInt(), Set.HEIGHT.ordinal() * Set.UNIT_SIZE.getInt(), 1);
    enemy = new Enemy(normalLevel,1,1);
    }
    @Test
    void collideWithWall() {
        int direction  = enemy.getDirection();
        enemy.collideWithWall();
        assertEquals(-direction, enemy.getDirection());
    }

    @Test
    void collideWithFloor() {
        enemy.collideWithFloor();
        assertEquals(0,enemy.getyVelocity());
    }

    @Test
    void collideWithCeiling() {
        enemy.collideWithCeiling();
        assertEquals(0,enemy.getyVelocity());
    }


    @Test
    void drawOn() {
        enemy.drawOn(imageView);
        assertEquals(enemy.getX(),imageView.getX());
        assertEquals(enemy.getY(),imageView.getY());
    }

    @Test
    void jump() {
        int yCoordinate = enemy.getY();
        enemy.jump();
        assertEquals(yCoordinate-1,enemy.getY());
    }

    @Test
    void collideWithProjectile() {
        enemy.state = new BubbledState(enemy);
        enemy.collideWithProjectile();
        assertEquals(0,enemy.getyVelocity());
        assertEquals(0,enemy.getxAccel());
        assertEquals(-0.1,enemy.getyAccel());
    }

}