package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class SetUpControllerTest {
    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     * @param stage Will be injected by the test runner.
     * @throws IOException
     */
    @Start
    private void start(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("setup-view.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Test action clicking one Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground1(FxRobot robot) {
        robot.clickOn("#one");
        assertEquals(1,SetUpController.getbgNum());
    }
    /**
     * Test action clicking two Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground2(FxRobot robot){
        robot.clickOn("#two");
        assertEquals(2,SetUpController.getbgNum());
    }
    /**
     * Test action clicking three Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground3(FxRobot robot) {
        robot.clickOn("#three");
        assertEquals(3,SetUpController.getbgNum());
    }
    /**
     * Test action clicking four Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground4(FxRobot robot) {

        robot.clickOn("#four");
        assertEquals(4,SetUpController.getbgNum());
    }
    /**
     * Test action clicking five Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground5(FxRobot robot) {
        robot.clickOn("#five");
        assertEquals(5,SetUpController.getbgNum());
    }
    /**
     * Test action clicking six Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground6(FxRobot robot) {
        robot.clickOn("#six");
        assertEquals(6,SetUpController.getbgNum());
    }
    /**
     * Test action clicking seven Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground7(FxRobot robot) {
        robot.clickOn("#seven");
        assertEquals(7,SetUpController.getbgNum());
    }
    /**
     * Test action clicking eight Button
     * @param robot Will be injected by the test runner.
     */
    @Test
    void setBackground8(FxRobot robot) {
        robot.clickOn("#eight");
        assertEquals(8,SetUpController.getbgNum());
    }
}