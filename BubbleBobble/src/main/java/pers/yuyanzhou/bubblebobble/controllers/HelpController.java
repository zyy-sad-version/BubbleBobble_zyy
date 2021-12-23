package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.FrameWork;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller of help view. Some keyboard information would be shown on the view.
 * @author YuyanZhou
 */
public class HelpController {
    @FXML
    ImageView buttonE;
    @FXML
    ImageView buttonLeft;
    @FXML
    ImageView buttonP;
    @FXML
    ImageView buttonQ;
    @FXML
    ImageView buttonR;
    @FXML
    ImageView buttonRight;
    @FXML
    ImageView buttonSpace;
    @FXML
    ImageView buttonUp;
    @FXML
    ImageView buttonW;

    /**
     * Handles action of clicking return button. Switch to game start view.
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    public void clickOnReturnBtn() throws IOException {
        FrameWork.app.setRoot("start-view");
    }

    /**
     * Initialize the help view.
     */
    @FXML
    public void initialize(){
    buttonE.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonE.png")).toExternalForm()));
    buttonLeft.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonLeft.png")).toExternalForm()));
    buttonP.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonP.png")).toExternalForm()));
    buttonQ.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonQ.png")).toExternalForm()));
    buttonR.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonR.png")).toExternalForm()));
    buttonRight.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonRight.png")).toExternalForm()));
    buttonSpace.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonSpace.png")).toExternalForm()));
    buttonUp.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonUp.png")).toExternalForm()));
    buttonW.setImage(new Image(Objects.requireNonNull(getClass().getResource("/buttons/buttonW.png")).toExternalForm()));
            }
}
