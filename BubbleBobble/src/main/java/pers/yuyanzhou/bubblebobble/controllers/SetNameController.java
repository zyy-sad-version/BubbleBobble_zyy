package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.support.Score;

import java.io.IOException;

/**
 * Controller of name setting view. Allow player inter their name for storing score.
 * @author YuyanZhou
 */
public class SetNameController {
    @FXML
    private Label label;
    @FXML
    private TextField input;

    /**
     * Handles action of clicking return button. Switch to game start view
     * @throws IOException Some exceptions to load fxml file
     */
    public void clickOnReturnBtn() throws IOException {
        FrameWork.app.setRoot("start-view");
    }

    /**
     * Handles action of clicking start button. If user does not enter name, it won't switch to game view.
     * @throws IOException Some exceptions to load fxml file
     */
    public void clickOnSureBtn() throws IOException {
        if("".equals(input.getText()))
        {
            label.setTextFill(Color.RED);
            label.setText("!Please input at least one char!");
        }else
        {
            Score.getInstance().setName(input.getText());
            FrameWork.app.setRoot("game01-view");
        }
    }
}
