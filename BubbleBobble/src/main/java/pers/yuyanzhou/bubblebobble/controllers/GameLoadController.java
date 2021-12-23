package pers.yuyanzhou.bubblebobble.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.support.Set;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

import java.io.IOException;
import java.net.URL;

/**
 *
 * Controller of loading view. This view would show an animation
 * @author YuyanZhou
 */
public class GameLoadController {
    @FXML
    private Pane screen;
    /**
     * Initialize loading view. Add a loading animation on timeline and add this timeline to pane
     */
    public void initialize(){
        ImageView logo;
        URL url1 = getClass().getResource("/Image/StartPage.png");
        URL url2 = getClass().getResource("/Image/loding.gif");
        assert url1 != null;
        Image image2 = new Image(url1.toExternalForm(), Set.WIDTH.getInt()*Set.UNIT_SIZE.getInt(),Set.UNIT_SIZE.getInt() *Set.HEIGHT.getInt(),true,true);
        assert url2 != null;
        Image image1 = new Image(url2.toExternalForm(),Set.WIDTH.getInt()*Set.UNIT_SIZE.getInt(),Set.UNIT_SIZE.getInt() *Set.HEIGHT.getInt(),false,true);
        logo = new ImageView(image1);
        Timeline timeline = new Timeline();

        KeyValue kvStart = new KeyValue(logo.preserveRatioProperty(),true);
        KeyValue kvMid = new KeyValue(logo.preserveRatioProperty(),true);
        KeyValue kvStop = new KeyValue(logo.preserveRatioProperty(),true);

        KeyFrame kfStart = new KeyFrame(Duration.seconds(0), "kfStart", actionEvent -> SoundEffect.LOAD.play(),kvStart);
        KeyFrame kfMid = new KeyFrame(Duration.seconds(2), "kfMid", actionEvent -> {
            SoundEffect.LOAD.stop();
            SoundEffect.BUBBLEBOBBLE.play();
            logo.setImage(image2);
        },kvMid);
        KeyFrame kfStop = new KeyFrame(Duration.seconds(4), "kfStop", actionEvent -> {
            try {
                SoundEffect.BGM.setToLoop();
                SoundEffect.BGM.play();
                FrameWork.app.setRoot("start-view");
            } catch (IOException e) {
                e.printStackTrace();
            }
        },kvStop);
        timeline.getKeyFrames().addAll(kfStart,kfMid,kfStop);
        timeline.play();
        screen.getChildren().addAll(logo);
    }

}
