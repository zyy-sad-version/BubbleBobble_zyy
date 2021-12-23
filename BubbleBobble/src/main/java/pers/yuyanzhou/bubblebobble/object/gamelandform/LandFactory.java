package pers.yuyanzhou.bubblebobble.object.gamelandform;

import pers.yuyanzhou.bubblebobble.object.strategy.LandformsUnit;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;

/**
 * LandFactory builds LandForm Units internally
 * @author YuyanZhou
 */
public class LandFactory {
     final String FLOOR="floor";
     final String WALL="wall";
     final String CEILING="ceiling";
     final String PLATFORM="platform";
    GameWorld world;

    /**
     * Constructor of LandFactory, pass GameWorld to define where to use factory
     * @param world
     */
    public LandFactory(GameWorld world) {
        this.world = world;
    }

    /**
     * Return a Landforms unit build in factory
     * @param type types of Landform unit
     * @param colNum x coordinate of create Landform unit
     * @param rowNum y coordinate of create Landform unit
     * @return A landform
     */
    public LandformsUnit getLand(String type, int colNum, int rowNum){
        if(type == null){
            return null;
        }
        if(FLOOR.equalsIgnoreCase(type)){
            return new FloorUnit(world,colNum,rowNum);
        }else if (WALL.equalsIgnoreCase(type)){
            return new WallUnit(world,colNum,rowNum);
        }else if (CEILING.equalsIgnoreCase(type)){
            return new CeilingUnit(world,colNum,rowNum);
        }else if(PLATFORM.equalsIgnoreCase(type)){
            return new PlatformUnit(world,colNum,rowNum);
        }
        return null;
    }
}
