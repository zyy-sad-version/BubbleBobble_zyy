package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.support.Score;

import java.io.IOException;

/**
 * Controller of score view. The return button, a list of score would be shown on this view.
 * @author YuyanZhou
 */
public class ScoreController {
    @FXML
    private Text scoreArea;

    /**
     * Handle action of clicking on return button. Switch to game start view.
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    private void clickReturnBtn() throws IOException {
        FrameWork.app.setRoot("start-view");
    }

    /**
     * Initialize score view.
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    public void initialize() throws IOException {
            scoreArea.setDisable(true);
            scoreArea.setText(Score.getInstance().getAllScore());
    }
}
