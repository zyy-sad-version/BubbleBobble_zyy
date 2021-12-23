package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextMatchers;
import org.testfx.service.query.NodeQuery;
import pers.yuyanzhou.bubblebobble.support.Score;

import java.io.IOException;

/**
 * Test the controller of death view
 */
@ExtendWith(ApplicationExtension.class)
class DeathControllerTest {
    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     * @param stage Will be injected by the test runner.
     * @throws IOException Loading fxml file exception
     */
    @Start
    private void start(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("death-view.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Test initialization of death view
     */
       @Test
    void initialize() {

        FxAssert.verifyThat("#score", TextMatchers.hasText(Score.getInstance().getThisScore()));
    }
}