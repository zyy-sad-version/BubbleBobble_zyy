package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;


/**
 * Controller of level one view. landform, hero, hero lives, enemy, high score would be shown on this view
 * @author YuyanZhou
 */
public class Game01Controller {
    @FXML
    AnchorPane screen;
    NormalLevel levelOne;

    /**
     * init level one view
     */
    @FXML
    public void initialize() {
        levelOne = new NormalLevel(Set.UNIT_SIZE.getInt() *Set.WIDTH.getInt(), Set.UNIT_SIZE.getInt() * Set.HEIGHT.getInt(),1);
        levelOne.startGame();
        screen.getChildren().addAll(levelOne);
        screen.setVisible(true);
    }
}