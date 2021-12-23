package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.support.Score;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

import java.io.IOException;


/**
 * Controller of win view. A trophy, a restart button, a quit button would be shown on the view.
 * @author YuyanZhou
 */
public class WinController {
    @FXML
    private ImageView trophy;
    @FXML
    private ImageView winLabel;
    @FXML
    private Text winText;

    /**
     * Initialize win view
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    public void initialize() throws IOException {
        SoundEffect.BGM.stop();
        winLabel.setImage(new Image("winLable.gif"));
        trophy.setImage(new Image("trophy.png"));
        winText.setText(Score.getInstance().getThisScore());
        Score.getInstance().storeScore();
    }

    /**
     * Handle action of clicking on restart button. Switch to game start view.
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    private void clickOnRestartBtn() throws IOException {
        FrameWork.app.setRoot("start-view");
        SoundEffect.BGM.setToLoop();
        SoundEffect.BGM.play();
        Score.getInstance().reStart();
    }

    /**
     * Handle action of clicking on quit button. Exit the game.
     */
    @FXML
    private void clickOnQuitBtn(){
        FrameWork.app.exit();
    }


}
