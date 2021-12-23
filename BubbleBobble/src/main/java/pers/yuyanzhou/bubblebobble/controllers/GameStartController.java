package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.support.Score;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

import java.io.IOException;

/**Controller of game start view. The game logo, a start button, a setup button, a score button, a help button, a quit button would be shown on this view.
 * @author YuyanZhou
 */
public class GameStartController {
   @FXML
   private HBox hGrop;
   @FXML
    Pane pane;
    @FXML
    ImageView logo;
    @FXML
    ImageView blowBubble;
    @FXML
    CheckBox muteCheck;
    /**
     * Handles action of click the start button. Switch to set name view.
     */
   @FXML
    public void clickOnStartBtn() throws IOException {
        FrameWork.app.setRoot("set-name");
    }
    /**
     * Handles action of clicking the quit button. Exit the game.
     */
    @FXML
    public void clickOnExitBtn(){
       FrameWork.app.exit();
    }
    /**
     * Handles action of clicking the help button. Switch to help button.
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    public void clickOnHelpBtn() throws IOException {
       FrameWork.app.setRoot("help-view");
    }

    /**
     * Handles action of clicking the score button. Switch to score view.
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    public void clickOnScoreBtn() throws IOException{
        FrameWork.app.setRoot("score-view");
    }

    /**
     * Handles action of clicking the setup button. Switch to background setup view.
     * @throws IOException load fxml file exception
     * loading fxml file exception
     */
    @FXML
    public void clickOnSetUpBth() throws IOException {
        FrameWork.app.setRoot("setup-view");
    }

    /**
     * Handles action mute the background music or unmute background music.
     */
    @FXML
    public void clickOnMute(){
        if(muteCheck.isSelected()){
            SoundEffect.BGM.stop();
        }else{
            SoundEffect.BGM.setToLoop();
            SoundEffect.BGM.play();
        }
    }
    /**
     * Initialize game start view.
     */
    @FXML
    public void initialize() throws IOException {
        Score.getInstance().initScoreList();
               Image image = new Image("BubbleBobbleLogo.png",500,500,true,false);
               Image picture = new Image("Picture.gif",200,200,true,true);
         logo = new ImageView(image);
         blowBubble = new ImageView(picture);
         logo.setY(50);
         logo.setX(150);
         blowBubble.setX(10);
         blowBubble.setY(400);
         hGrop.setLayoutY(500);
         pane.getChildren().addAll(logo,blowBubble);
    }

}

