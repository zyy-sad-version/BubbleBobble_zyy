package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * Controller of level two view. landform, hero, hero lives, enemy, high score would be shown on this view
 * @author YuyanZhou
 */
public class Game02Controller {
    @FXML
    AnchorPane screen;
    NormalLevel levelTwo;

    /**
     * Initialize level two view
     */
    @FXML
    public void initialize(){
        levelTwo = new NormalLevel(Set.UNIT_SIZE.getInt() *Set.WIDTH.getInt(), Set.UNIT_SIZE.getInt() * Set.HEIGHT.getInt(),2);
        levelTwo.startGame();
        screen.getChildren().addAll(levelTwo);
    }
}
