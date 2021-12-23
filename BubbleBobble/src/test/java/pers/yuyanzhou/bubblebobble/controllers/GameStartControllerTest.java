package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

/**
 * Test controller of game start view
 */
@ExtendWith(ApplicationExtension.class)
class GameStartControllerTest {
    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     * @param stage Will be injected by the test runner.
     * @throws IOException Loading fxml file exception
     */
    @Start
    private void start(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("start-view.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Test initialization of game start view
     */
    @Test
    void initialize() {
        FxAssert.verifyThat("#startBtn", LabeledMatchers.hasText("START"));
        FxAssert.verifyThat("#helpBtn", LabeledMatchers.hasText("HELP"));
        FxAssert.verifyThat("#setUpBtn", LabeledMatchers.hasText("SETUP"));
        FxAssert.verifyThat("#scoreBtn", LabeledMatchers.hasText("SCORE"));
        FxAssert.verifyThat("#exitBtn", LabeledMatchers.hasText("QUIT"));
    }
}