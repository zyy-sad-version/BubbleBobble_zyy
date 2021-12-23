package pers.yuyanzhou.bubblebobble.app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pers.yuyanzhou.bubblebobble.FrameWork;
import pers.yuyanzhou.bubblebobble.GameEntry;
import pers.yuyanzhou.bubblebobble.support.Set;

import java.io.IOException;

/**
 * This class encapsulates the control program's lifecycle methods
 * @author YuyanZhou
 */
public class App {
    private final Stage stage;
    private final Scene scene;
    OnLaunch onLaunch;
    OnFinish onFinish;
    OnExist onExist;

    /**
     * Constructor of App class. Pass a stage to set scene on it.
     * @param stage Where scene would bbe set on
     * @throws IOException Some resource file ead exception
     */
    public App(Stage stage) throws IOException {
        this.stage = stage;
        scene = new Scene(loadFXML("game-load-view"), Set.UNIT_SIZE.getInt() *Set.WIDTH.getInt(), Set.UNIT_SIZE.getInt() * Set.HEIGHT.getInt());
        stage.setScene(scene);
        stage.setResizable(false);
        initFramework();
        initApp();
    }

    /**
     * handles launch of stage
     */
    public void launch(){
        if(onLaunch!=null){
            onLaunch.handle();
        }
        stage.requestFocus();
        stage.show();
    }

    /**
     * handle finish of stage
     */
    public void finish() {
        if (onFinish != null) {
            onFinish.handle();
        }
    }
    /**
     * exit the game
     */
    public void exit(){
        Platform.exit();
    }

    /**
     * Initialize the stage
     */
    private void initApp() {
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event-> {
            if(onExist!=null&& !onExist.handle()){
                event.consume();
            }
        });
    }
    /**
     * Initialize the framework
     */
    private void initFramework() {
        FrameWork.app = this;
    }
    /**
     * change the scene
     * @param fxml fxml file to change
     */
    public  void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /**
     * load the fxml file, then return load value
     * @param fxml name of fxml file
     * @return load value
     */
    private  Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(GameEntry.class.getClassLoader().getResource(fxml+".fxml"));
        return loader.load();
    }

    /**
     * Set title of stage
     * @param title Text would be set as title
     */
    public void setTitle(String title){
        stage.setTitle(title);
    }

    public static interface OnLaunch{
        void handle() ;
    }
    public static interface OnFinish{
        void handle();
    }
    public static interface OnExist{
        boolean handle();
    }
}
