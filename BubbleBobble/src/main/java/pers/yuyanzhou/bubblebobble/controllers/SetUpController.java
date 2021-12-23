package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.yuyanzhou.bubblebobble.FrameWork;

import java.io.IOException;

/**
 * Controller of setup view. This view is used for setting background image. A return button, 8 image select button would be shown on the view.
 * @author YuyanZhou
 */
public class SetUpController {

@FXML
private ImageView background1;
@FXML
private ImageView background2;
@FXML
private ImageView background3;
@FXML
private ImageView background4;
@FXML
private ImageView background5;
@FXML
private ImageView background6;
@FXML
private ImageView background7;
@FXML
private ImageView background8;

private static int bgNum = 1;

    /**
     * Initialize set up view
     */
    @FXML
    public void initialize(){
        background1.setImage(new Image("background1.png"));
        background2.setImage(new Image("background2.png"));
        background3.setImage(new Image("background3.png"));
        background4.setImage(new Image("background4.png"));
        background5.setImage(new Image("background5.png"));
        background6.setImage(new Image("background6.png"));
        background7.setImage(new Image("background7.png"));
        background8.setImage(new Image("background8.png"));
    }
    /**
     * Handles action od clicking image1 button.
     */
    @FXML
    private void setBackground1(){
        bgNum=1;
    }
    /**
     * Handles action od clicking image2 button.
     */
    @FXML
    public void setBackground2() {
        bgNum=2;
    }
    /**
     * Handles action od clicking image3 button.
     */
    @FXML
    public void setBackground3() {
        bgNum=3;
    }
    /**
     * Handles action od clicking image4 button.
     */
    @FXML
    public void setBackground4() {
        bgNum=4;
    }
    /**
     * Handles action od clicking image5 button.
     */
    @FXML
    public void setBackground5() {
        bgNum=5;
    }
    /**
     * Handles action od clicking image6 button.
     */
    @FXML
    public void setBackground6() {
        bgNum=6;
    }
    /**
     * Handles action od clicking image7 button.
     */
    @FXML
    public void setBackground7() {
        bgNum=7;
    }

    /**
     * Handles action od clicking image8 button.
     */
    @FXML
    public void setBackground8() {
        bgNum=8;
    }

    /**
     * Handles action od clicking return button. Switch to game start view
     * @throws IOException Some exceptions to load fxml file
     */
    @FXML
    private void clickOnReturn() throws IOException {
        FrameWork.app.setRoot("start-view");
    }
    public static int getbgNum(){
        return bgNum;
    }
}
