package pers.yuyanzhou.bubblebobble.object.worlds;

import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.Boss;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.GameChar;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.Hero;
import pers.yuyanzhou.bubblebobble.object.gamelandform.LandFactory;
import pers.yuyanzhou.bubblebobble.object.gameprops.GoldCoin;
import pers.yuyanzhou.bubblebobble.object.strategy.GameObject;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;
import java.util.Scanner;

/**
 * FinalLevelWorld inherit from GameWorld.
 * FinalLevelWorld is level only contain Hero and a Boss.
 * @author YuyanZhou
 */
public class FinalLevelWorld extends GameWorld{
    public FinalLevelWorld(int width, int height) {
        super(width, height);
        landFactory = new LandFactory(this);
        adapter = new LevelAdapter(4);
    }
    /**
     * update game character
     */
    @Override
    void gameCharUpdate() {
        for (GameChar gameChar: gameChars){
            gameChar.update();
            changeImageManage.get(gameChar).relocate(gameChar.getX(), gameChar.getY());
            if(gameChar.isCanRemove()){
                toBeRemoved.add(gameChar);
            }
        }
    }
    /**
     * update gravity object
     */
    @Override
    void gravitiesUpdate() {
        for(Gravity gravity: gravities){
            gravity.update();
            changeImageManage.get(gravity).relocate(gravity.getX(),gravity.getY());
            if(gravity.isCanRemove())
            {
                toBeRemoved.add(gravity);
            }
        }
    }
    /**
     * if character change direction, image should also change.
     * This method is used to change image
     */
    @Override
     void reDrawOn() {
        for (GameObject gameObject:changeImageManage.keySet()) {
            gameObject.drawOn(changeImageManage.get(gameObject));
        }
    }
    @Override
     void collideWithLand() {
        for (LandformsUnit landformsUnit : landformsUnits) {
            for (Gravity gravity : gravities) {
                landformsUnit.collideWith(gravity);
            }
            for (GameChar gameChar: gameChars){
                landformsUnit.collideWith(gameChar);
                if (gameChar instanceof Boss) {
                    ((Boss) gameChar).collideWithLand(landformsUnit);
                }
            }
        }
    }
    /**
     * Whether player pass this level
     *
     */
    @Override
     void isPass() throws IOException {
        if (!isExitEnemy() && !isExitGoldCion()) {
            at.stop();
            adapter.win();
        }
    }
    /**
     * Determine if there are any Enemy in the world
     */
    private boolean isExitEnemy(){
        for (GameChar gameChar: gameChars){
            if(gameChar instanceof Boss){
                return true;
            }
        }
        return false;
    }
    /**
     * Determine if there are any GoldCoin in the world
     */
    private boolean isExitGoldCion(){
        for (Gravity gravity : gravities){
            if(gravity instanceof GoldCoin) {
                return true;
            }
        }
        return false;
    }
    /**
     * Removing game object
     */
    @Override
     void removing() throws IOException {
        for (GameObject object: toBeRemoved){
            this.getChildren().remove(changeImageManage.get(object));
            changeImageManage.remove(object);
            removeObj(object);
        }
        toBeRemoved.clear();
        if(readyToReset){
            if(heart>0){
                this.getChildren().removeAll(landImage);
                startGame();
            }
            else {
                at.stop();
                FrameWork.app.setRoot("death-view");
            }
        }
    }
    @Override
    void collideInGravities(){
        for(GameChar gameChar: gameChars){
            for (Gravity gravity: gravities){
                gameChar.collideWith(gravity);
                gravity.collideWith(gameChar);
            }
            for(GameChar gameChar1: gameChars){
                gameChar.collideWith(gameChar1);
                gameChar1.collideWith(gameChar);
            }
        }
    }
    /**
     * Handles loading map of level
     */
    @Override
    public void readMap() {
        Scanner scanner = new Scanner(adapter.getMapPath());
        clearContents();
        for (int row = 0; row < Set.HEIGHT.getInt(); row++) {
            String currentLine = scanner.next();
            for (int col = 0; col < Set.WIDTH.getInt(); col++) {
                if (currentLine.charAt(col) == '*') {
                    addLandformUnit(landFactory.getLand("floor",col,row));
                } else if (currentLine.charAt(col) == 'H') {
                    addGameChar(new Hero(this, col, row));
                } else if (currentLine.charAt(col) == '|') {
                    addLandformUnit(landFactory.getLand("wall",col,row));
                } else if (currentLine.charAt(col) == '_') {
                    addLandformUnit(landFactory.getLand("ceiling",col,row));
                } else if (currentLine.charAt(col) == 'B') {
                    addGameChar(new Boss(this, col, row));
                } else if (currentLine.charAt(col) == '=') {
                    addLandformUnit(landFactory.getLand("platform",col,row));
                }
            }
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
