package pers.yuyanzhou.bubblebobble;

import pers.yuyanzhou.bubblebobble.app.Games;

/**
 * Entry of bubblebobble game, for running this project, please Edit Run configuration:
 *  Add these VM options: --module-path /path/to/javafx-sdk-17/lib --add-modules javafx.controls,javafx.fxml,javafx.media(Linux/Mac)
 *                          --module-path "\path\to\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml,javafx.media(Windows)
 * @author YuyanZhou
 */
public class GameEntry extends Games
{
        public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launch of bubble and set its title.
     */
    @Override
    public void onLaunch() {
           FrameWork.app.setTitle("BubbleBobble");
    }
}