package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pers.yuyanzhou.bubblebobble.object.worlds.NormalLevel;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * Controller of level three view. landform, hero, hero lives, enemy, high score would be shown on this view
 * @author YuyanZhou
 */
public class Game03Controller {
@FXML
private AnchorPane screen;
    NormalLevel levelThree;

    /**
     * Initialize level three view
     */
    @FXML
    public void initialize(){
        levelThree = new NormalLevel(Set.UNIT_SIZE.getInt() *Set.WIDTH.getInt(), Set.UNIT_SIZE.getInt() * Set.HEIGHT.getInt(),3);
        levelThree.startGame();
        screen.getChildren().addAll(levelThree);
        screen.setVisible(true);

    }

}
