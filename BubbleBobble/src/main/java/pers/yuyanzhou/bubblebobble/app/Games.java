package pers.yuyanzhou.bubblebobble.app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class extend Application class, provide a primary stage.
 * @author YuyanZhou
 *
 */
public abstract class Games extends Application {
    private App app;

    /**
     * Constructor of Games
     */
    public Games() {
    }

    /**
     * Handles launch of stage
     */
    public abstract void onLaunch();
    public void onFinish(){}
    public boolean onExit(){
        return true;
    }

    /**
     * Combine the interface with the APP class by assigning
     * @param primaryStage stage obtain from Application class
     * @throws Exception some javafx exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        app = new App(primaryStage);
//Combine the interface with the App class by assigning
        app.onLaunch=this::onLaunch;
        app.onFinish=this::onFinish;
        app.onExist=this::onExit;
        app.launch();
    }

    /**
     * Exit the stage
     */
    @Override
    public void stop() {
        app.finish();
    }
}
