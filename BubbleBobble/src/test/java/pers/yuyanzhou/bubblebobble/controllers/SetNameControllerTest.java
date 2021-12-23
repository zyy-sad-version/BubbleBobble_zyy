package pers.yuyanzhou.bubblebobble.controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

/**
 * Test controller of set name view
 */
@ExtendWith(ApplicationExtension.class)
class SetNameControllerTest {
    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     * @param stage Will be injected by the test runner.
     * @throws IOException Loading fxml file exception
     */
    @Start
    private void start(Stage stage) throws IOException {
       Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("set-name.fxml")));
       stage.setScene(scene);
        stage.show();
    }

    /**
     * Test initialization of set name view
     */
    @Test
    void initialize(){
        FxAssert.verifyThat("#sureBtn",LabeledMatchers.hasText("Yes, I'm sure"));
        FxAssert.verifyThat("#returnBtn",LabeledMatchers.hasText("I have no idea"));
        FxAssert.verifyThat("#label", LabeledMatchers.hasText("Please Enter Your Name"));
    }

    /**
     * Test action that clicking sureBtn button
     * @param robot Will be injected by the test runner
     */
    @Test
    void clickOnSureBtn(FxRobot robot) {
     robot.clickOn("#sureBtn");
     FxAssert.verifyThat("#label",LabeledMatchers.hasText("!Please input at least one char!"));
    }
}