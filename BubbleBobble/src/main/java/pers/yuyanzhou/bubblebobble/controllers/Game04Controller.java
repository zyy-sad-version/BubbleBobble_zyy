package pers.yuyanzhou.bubblebobble.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pers.yuyanzhou.bubblebobble.object.worlds.FinalLevelWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * Controller of final level view. landform, hero, hero lives, boss, high score would be shown on this view
 * @author DLZ
 */
public class Game04Controller {
    @FXML
  private   AnchorPane screen;
    FinalLevelWorld levelFour;

    /**
     * Initialize final level view
     */
    @FXML
    public void initialize(){
        levelFour = new FinalLevelWorld(Set.UNIT_SIZE.getInt() *Set.WIDTH.getInt(), Set.UNIT_SIZE.getInt() * Set.HEIGHT.getInt());
        levelFour.startGame();
        screen.getChildren().add(levelFour);
    }
}
