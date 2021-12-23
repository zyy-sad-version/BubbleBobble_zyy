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
 * Controller of death view. Final score, restart button, quit button would be shown on this view.
 * @author DLZ
 */
public class DeathController {
    @FXML
    Text score;
    @FXML
    ImageView imageView;
    @FXML
    ImageView labelView;

    /**
     * Initialize this view
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    public void initialize() throws IOException {
        SoundEffect.BGM.stop();
        Image death = new Image("/gameOverImage.png");
        Image label = new Image("/loseLable.gif");
         imageView.setImage(death);
         labelView .setImage(label);
        score.setText(Score.getInstance().getThisScore());
        Score.getInstance().storeScore();
    }

    /**
     * Handles action of click the restart button. Player shall return to start view.
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
     * Handles action of click the quit button. Player shall exit bubblebobble.
     */
    @FXML
    private void clickOnQuitBtn(){
        FrameWork.app.exit();
    }
}
