package pers.yuyanzhou.bubblebobble.object.worlds;

import java.io.IOException;
import java.io.InputStream;

import static pers.yuyanzhou.bubblebobble.FrameWork.app;
/**
 * @author YuyanZhou
 */
public class LevelAdapter {

private int level;
    public LevelAdapter(int level) {
      this.level = level;
    }

    public InputStream getMapPath(){
        return this.getClass().getResourceAsStream("/world/World"+level+".txt");
    }
    public void gotoNextLevel() throws IOException {
       app.setRoot("game0"+(level+1)+"-view");
    }
    public void win()throws IOException{
        app.setRoot("win-view");
    }
}
