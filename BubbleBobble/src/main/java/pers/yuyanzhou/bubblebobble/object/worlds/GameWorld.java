package pers.yuyanzhou.bubblebobble.object.worlds;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pers.yuyanzhou.bubblebobble.controllers.SetUpController;
import pers.yuyanzhou.bubblebobble.object.gamecharacter.GameChar;
import pers.yuyanzhou.bubblebobble.object.gamelandform.LandFactory;
import pers.yuyanzhou.bubblebobble.object.strategy.GameObject;
import pers.yuyanzhou.bubblebobble.object.strategy.Gravity;
import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.support.Score;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


/**
 * GameWorld is a container to contain all GameObjects.
 * GameObject shall update in GameWorld.
 * @author YuyanZhou
 */
public abstract class GameWorld extends Pane {
    protected ArrayList<ImageView> landImage;
    protected ArrayList<LandformsUnit> landformsUnits;
    protected ArrayList<Gravity> gravities;
    protected ArrayList<GameChar> gameChars;

    protected ArrayList<GameObject> toBeRemoved;
    protected HashMap<GameObject,ImageView> changeImageManage;

    protected boolean readyToReset;
    protected ArrayList<ImageView> hearts;
    protected int heart;

    protected AnimationTimer at;
    private Text name;
    protected LevelAdapter adapter;
    protected LandFactory landFactory;

    /**
     * Constructor of GameWorld, pass size to create GameWorld
     * @param width width of GameWorld
     * @param height height of GameWorld
     */
    public GameWorld(int width, int height) {
        setMinSize(width,height);
        landformsUnits = new ArrayList<>();
        gravities = new ArrayList<>();
        gameChars = new ArrayList<>();
        toBeRemoved = new ArrayList<>();
        landImage = new ArrayList<>();
        changeImageManage = new HashMap<>();
        hearts = new ArrayList<>();
        heart = 3;
        readyToReset = false;
        BackgroundImage backgroundImage = new BackgroundImage(new Image("background"+ SetUpController.getbgNum()+".png", Set.UNIT_SIZE.getInt() *Set.WIDTH.getInt(), Set.UNIT_SIZE.getInt() * Set.HEIGHT.getInt(),false,true),
        BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        this.setBackground(new Background(backgroundImage));
        scoreBroad();
            at = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    updateObjects();
                    name.setText(Score.getInstance().getThisScore());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        at.start();
    }
    /**
     * Pain initial map
     */
    public void paintInitComponent(){
        paintHeart();
        painLandformUnit();
    }
    /**
     * Create a high score broad. Include player name and player's score
     */
    private void scoreBroad(){
        name = new Text();
        name.setFill(Color.WHITE);
        name.setX(250);
        name.setY(50);
        name.setFont(Font.font(null, FontWeight.BOLD, 32));
        this.getChildren().add(name);
    }
    /**
     * Pain heart of hero on map
     */
    private void paintHeart() {
        for (int i=0; i < heart+1; i++) {
            Image heart = new Image(Objects.requireNonNull(getClass().getResource("/Image/heart.gif")).toExternalForm(),100,100,true,false);
            ImageView h = new ImageView(heart);
            h.setX(i*50);
            this.getChildren().add(h);
            hearts.add(h);
        }
    }

    /**
     * Pain LandformUnit on map
     */
    private void painLandformUnit(){
        for(LandformsUnit landformsUnit : landformsUnits){
            ImageView landView = new ImageView();
            landformsUnit.drawOn(landView);
            landImage.add(landView);
            this.getChildren().add(landView);
        }
    }
    /**
     * Update position of everything on screen
     * @exception IOException load fxml file exception
     */
    public final void updateObjects() throws IOException{
        gravitiesUpdate();
        gameCharUpdate();
        reDrawOn();
        collideWithLand();
        collideInGravities();
        isPass();
        removing();
    }

    /**
     * Handles Gravity update
     */
    abstract void gravitiesUpdate();

    /**
     * Handles GameChar update
     */
    abstract void gameCharUpdate();

    /**
     * Redraw the GameObject
     */
    abstract void reDrawOn();

    /**
     * Handle collision between GameObjects and Landform unit
     */
    abstract void collideWithLand();
    /**
     * Handle collision between Gravity
     */
    abstract void collideInGravities();

    /**
     * Handle removing GameObject
     * @throws IOException Some load fxml Exception
     */
    abstract void removing() throws IOException;
    /**
     * Add landform unit on map
     * @param landformsUnit unit need to be drawn on map
     */
    public void addLandformUnit(LandformsUnit landformsUnit){
        landformsUnits.add(landformsUnit);
    }

    /**
     * Determine whether the game object overlaps with the incoming game object
     * @throws IOException load fxml file exception
     */
    abstract void isPass() throws IOException;

    /**
     * Add GameChar on map
     * @param gameChar game character needed to add on map
     */
    public void addGameChar(GameChar gameChar){
        gameChars.add(gameChar);
        ImageView imageView = new ImageView();
        gameChar.drawOn(imageView);
        this.getChildren().add(imageView);
        changeImageManage.put(gameChar,imageView);
    }
    /**
     * Add gravity except GameChar on the map
     * @param gravity gravities object except game character needed to add on map
     */
    public void addOtherGravity(Gravity gravity){
        gravities.add(gravity);
        ImageView imageView = new ImageView();
        gravity.drawOn(imageView);
        this.getChildren().add(imageView);
        changeImageManage.put(gravity,imageView);
    }

    /**
     * Clears everything from the screen
     */
     public void clearContents(){
         landformsUnits.clear();
         gravities.clear();
         gameChars.clear();
         for (ImageView i:changeImageManage.values()) {
             this.getChildren().remove(i);
         }
         this.getChildren().removeAll(hearts);
         changeImageManage.clear();
         heart--;
     }
    /**
     * Removes a single object from the screen
     * @param obj object need to remove
     */
    public void removeObj(GameObject obj){
        if(obj instanceof LandformsUnit){
            landformsUnits.remove(obj);
        }else if(obj instanceof GameChar){
        gameChars.remove(obj);
        }
        else if(obj instanceof Gravity) {
            gravities.remove(obj);
        }
    }
    /**
     * Sets boolean to make sure the world is ready to be reset
     */
    public void markToReset() {
        readyToReset = true;
    }

    /**
     * Start the game
     */
    public void startGame() {
        readMap();
        paintInitComponent();
        readyToReset = false;
    }

    /**
     * Pause the game
     */
    public void pauseGame(){
        at.stop();
    }

    /**
     * Restart the game
     */
    public void reStartGame(){
        at.start();
    }

    /**
     * Read the map of the level
     */
    public abstract void readMap();

}