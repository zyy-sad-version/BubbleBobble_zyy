package pers.yuyanzhou.bubblebobble.object.worlds;

import pers.yuyanzhou.bubblebobble.object.gamecharacter.GameChar;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.Hero;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates.Enemy;
import pers.yuyanzhou.bubblebobble.object.gamelandform.LandFactory;
import pers.yuyanzhou.bubblebobble.object.gameprops.Fruit;
import pers.yuyanzhou.bubblebobble.object.strategy.GameObject;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;
import java.util.Scanner;

import static pers.yuyanzhou.bubblebobble.FrameWork.app;

/**
 * NormalLevel inherit from GameWorld.
 * NormalLevel is level only contain a Hero and Enemy.
 * @author YuyanZhou
 */
public class NormalLevel extends GameWorld{
    protected int numOfEnemy;
    protected int numOfKilled;
    public NormalLevel(int width, int height,int level) {
        super(width, height);
        adapter = new LevelAdapter(level);
        numOfEnemy = 0;
        numOfKilled=0;
        landFactory = new LandFactory(this);
    }

    @Override
     void isPass() throws IOException {
        if(!isExitEnemy() && !isExitFruit()){
            at.stop();
            adapter.gotoNextLevel();
        }
    }
    /**
     * Update game character
     */
    @Override
     void gameCharUpdate() {
        for (GameChar gameChar: gameChars){
            gameChar.update();
            changeImageManage.get(gameChar).relocate(gameChar.getX(), gameChar.getY());
            if(gameChar.isCanRemove()){
                toBeRemoved.add(gameChar);
                if(gameChar instanceof Enemy){
                    numOfKilled++;
                    numOfEnemy--;
                }
            }
        }
    }
    /**
     * Update gravity object
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
     * If character change direction, image should also change.
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
                if (gameChar instanceof Enemy) {
                    ((Enemy) gameChar).collideWithLand(landformsUnit);
                }
            }
        }
    }
    /**
     * Determine if there are any Enemy in the world
     */
    private boolean isExitEnemy(){
        for (GameChar gameChar: gameChars){
            if(gameChar instanceof Enemy){
                return true;
            }
        }
        return false;
    }
    /**
     * Determine if there are any Fruit in the world
     */
    private boolean isExitFruit(){
        for (Gravity gravity : gravities){
            if(gravity instanceof Fruit) {
                return true;
            }
        }
        return false;
    }
    /**
     * Removing game object
     */
    @Override
    public void removing() throws IOException {
        for (GameObject object: toBeRemoved){
            this.getChildren().remove(changeImageManage.get(object));
            changeImageManage.remove(object);
            removeObj(object);
        }
        toBeRemoved.clear();
        if(readyToReset){
            if(heart>0){
                numOfEnemy =0;
                this.getChildren().removeAll(landImage);
                startGame();
            }
            else {
                at.stop();
                app.setRoot("death-view");
            }
        }
    }
    @Override
    public void collideInGravities(){
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
     * Handles load map of level
     */
    @Override
    public void readMap(){
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
                } else if (currentLine.charAt(col) == 'M') {
                    numOfEnemy++;
                    if(numOfKilled<numOfEnemy){
                        addGameChar(new Enemy(this, col, row));
                    }
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
