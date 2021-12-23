# AE2DMS-CW-20217124

##### To execute the program:

1. click on `Run -> Edit Configurations..` and add these VM options:

   for Linux/Mac

   ````
   --module-path /path/to/javafx-sdk-17/lib --add-modules javafx.controls,javafx.fxml,javafx.media
   ````

   for Windows

   ````
   --module-path "\path\to\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml,javafx.media
   ````
then click `Apply`
2. Then run the program by run *GameEntry* class

##### To run junit test:

click on `Maven -> Lifecycle -> test` **[please do not move mouse cursor until test finish]**

------

*Design pattern used: **Strategy pattern, Template pattern, Adapter pattern,  Observer pattern, State pattern, Factory pattern, Decorator pattern, MVC pattern,  Singleton pattern***

### Maintenance and Refactor: 

1. **Changed** the *GameObject* **to an interface** and **implement it with two abstract classes**(*Gravity*, *LandformUnit*). 
2. Some methods(*reverseDirection()*, *isOffScreen()*,*update()*, *moveTo(Point2D point)* ) have been **moved into Gravity.**

* **Reason**: *GameObject* are divided into *Gravity* that can change coordinates, and *LandFormUnit* that can't change coordinates. Removing never called methods about changing coordinates from *LanformUnit*.

  **Strategy Pattern**: *Gravity* and *LandformUnit* can switch freely, scale well.

  ```java
  public interface GameObject {..}
  public abstract class Gravity implements GameObject{..}
  public abstract class LandformUnit implements GameObject{..}
  ```


3. **Changed** *InteracterWorld* **to abstract ** *GameWorld*. Use  *<u>**Template pattern**</u>* for designing *update()*.  **Extract the member variables into ** *GameWorld* and **change their access modifiers to *protected***, only *GameWorld* can use them. 

* **Reason** : Creating multiple GameWorld is duplicated.

* **Template Pattern**: Designing *updateObjects()*.  

  ```java
  public abstract class GameWorld extends Pane{
      //...
      public final void updateObjects() throws IOException{
      gravitiesUpdate();
      gameCharUpdate();
      reDrawOn();
      collideWithLand();
      collideInGravities();
      isPass();
      removing();
  }
      //Override these method in sub class
  abstruct void gravitiesUpdate();
  abstruct void gameCharUpdate();
  abstruct void reDrawOn();
  abstruct void collideWithLand();
  abstruct void collideInGravities();
  abstruct void isPass();
  abstruct void removing();
      //...
  }
  ```
  
  **Adapter pattern**: Connecting *GameWorld*, *InputStream*, *fxmlLoader*.
  
  **Observer pattern:** Subject: *GameWorld*;  Observer: *Gravity*. When subject calls *updateObjects()*, observers would execute their *update()*.
  
  ```java
  public final class GameWorld{
      ...
      //add observers    
      public void addOtherGravity(Gravity gravity){
          gravities.add(gravity);
          ImageView imageView = new ImageView();
          gravity.drawOn(imageView);
          this.getChildren().add(imageView);
          changeImageManage.put(gravity,imageView);
      }
      ...
  }
  public class NormalWorld{
      ...
      //notify observers
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
      ...    
  }
  public abstruct class Gravity implements GameObject{
      ...
      //Observers update
  public void update(){
          //...update method
      }  
      ...
  }
  ```

4. Using **Java polymorphism**, original *GameWorld* ArrayList with data types of *Hero*, *Enemy*, *Fruit*, *WallUnit* etc, is **upcasting** to 3 ArrayLists with *GameChar*, *Gravity*, *Landform* data types. 

5. Some methods are redundant and overlong. Following principle :"one method, one responsibility", **they are split into submethods**.

6. Detailed classify *Gravity*, **3 abstract classes** *GameChar* (subclass: *Hero, Enemy, Boss*), *GameProp* (subclass: *Fruit, GoldCoin*), and *Projectile* *(subclass: HeroProjectile, EnemyProjectile, Bubble)* **were created**. **Common methods and the same member variables are extracted into these 3 abstract classes**. 

  **State pattern:** Simplify if-else statement in *Enemy*, *GameProp*.

​	Following code uses *GameProp* as example. *Enemy* uses similar state patterns.

```java
//State interface
public interface PropState{
    void collideWithHero();
    void collideWithLand();
}
//The one of entity State class implements the State interface
public class ReadyToCollectState implements PropState{
    private GameProp prop;
    public ReadyToCollectState(GameProp prop){
        this.prop = prop;
    }
    @Override
    public void collideWithHero(){
        //...
    }
    @Override
    public void collideWithLand(){
        //...
    }
}
//GameProp with state
public abstract class GameProp extends Gravity{
    private PropState state;
    public GameProp(GameWorld world, int x, int y, int width, int height) {
        super(world,x,y,width,height);
        state = new UnreadyState(this);
    } 
    //...
}
```

* **Factory pattern**: To create *LandformUnit*.

  ```java
  //Factory class
  public class LandFactory {
       final String FLOOR="floor";
       final String WALL="wall";
       final String CEILING="ceiling";
       final String PLATFORM="platform";
      GameWorld world;
      public LandFactory(GameWorld world) {
          this.world = world;
      }
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
  //one of use cases of using the factory class
  public class LevelFourWorld extends GameWorld{
      public LevelFourWorld(int width, int height) {
         //...
           landFactory = new LandFactory(this);
      }
      //...
      @Override
      public void readMap() {
          //...
        //get LandFormUnit from factory
           if (currentLine.charAt(col) == '*') {
                      addLandformUnit(landFactory.getLand("floor",col,row));
                  } 
          //...
  }
  ```
  

- **Decorator pattern: ** Extend functionality of superclasses

  Following code uses *Hero* as example. Other classes use similar decorator patterns.

  ```java
  public class Hero extend GameChar{
      //...
      @Override
      public void update(){
          super.update();
          if(chargeTimer==0){
              setChargeToReady();
          }
          chargeTimer--;
          if(isShielding){
              shieldTimer -= 1;
              if(shieldTimer <= 0){
                  shieldTimer = 0;
                  isShielding = false;
                  isStunned = true;
              }
          }
          else {
              if(shieldTimer < Set.HERO_SHIELD_TIME.getInt()&& isStunned)
              {shieldTimer += 1;}
          }
          if(isStunned){
              stunTimer -= 1;
              if(stunTimer <=0){
                  isStunned = false;
                  stunTimer = 250;
                  shieldTimer = Set.HERO_SHIELD_TIME.getInt();
              }
          }
      }
      //...
  }
  ```

* **MVC pattern**: To manage pages and switch between pages.

Model: *GameWorld* ,its subclass

View: **.fxml* files under resources directory

Controller:  Each class name ends with "Controller" in *controller* package. e.g.  *Game01Controller*

```java
//Controller
public class Game01Controller {
    @FXML
    AnchorPane screen;
    NormalLevel levelOne;
    @FXML
    public void initialize() {
   //...
    }
}
//Model
public class LevelOneWorld extends NormalLevel{
    public LevelOneWorld(int width, int height) {
        //...
    }
    //...
}
//View
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.AnchorPane?>
<AnchorPane fx:id="screen" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/16" xmlns:fx="http://javafx.com/fxml/1" fx:controller="pers.yuyanzhou.bubblebobble.controllers.Game01Controller" />
```

7. Extract *GameObject*data (coordinates, magnitude, velocity, acceleration, etc) into an enumeration for easy modification, maintenance.

```java
public enum Set {
    //game page seting
    UNIT_SIZE (20),
    WIDTH(40),
    HEIGHT(34),
	//...
    private int dataInt;
    private double dataDou;
    Set(int dataInt){
    this.dataInt = dataInt;
	}
    Set(double dataDou){
    this.dataDou = dataDou;
	}
    public int getInt() {
        return dataInt;
    }
    public double getDou() {
        return dataDou;
    }
}
```

------

### Extensions

1. Increase level to **four levels**, three normal levels, one Boss level

2. Change all graphics display of *GameObject* to **picture display**

3. Add **background music** and could mute on start page.

4. Add **lives** to hero(3 times)

5. Implemented hero's **special move action** and add sound effect

6. Added shooting function for **enemies**

7. Add **Boss enemy** 

8. Added **gold items** that drop after defeating Boss

9. Added **game loading animation**

10. Added **pause** and **resume** functions

11. Added game **start menu page**, **set name page**, **death page**, **win page**, **score page**, **background image selection page**. And implemented **logical switch** between pages

12. Every level and each round(Whatever lose or win) pop up **high score**

13. To implement *Score* (Singleton pattern):

    ```java
    public class Score {
        private static final Score INSTANCE = new Score();
        private static int score;
        private static String name;
        private Score(){}
    
        public static Score getInstance(){
            return INSTANCE;
        }
        public String getName(){
            return name;
        }
        public void setName(String name){
            Score.name = name;
        }
        public void getFruit(){
            score +=20;
        }
        public void getGold(){
            score += 50;
        }
        public void heroDie(){
            score-=10;
        }
        public String getAllScore() throws IOException {
            //...
        }
        public String getThisScore(){
          //...
        }
    	public void reStart(){
            //...
    }
        public void storeScore()throws IOException{
            //...
        }
    
    }
    ```

------

word counts: 488

### Test case: 

| Contents                                             |
| ---------------------------------------------------- |
| [Test Plan](#test-plan)                              |
| [controllersTest](#class-deathcontroller)            |
| [object.gamecharacter.enemyStatesTest](#class-enemy) |
| [object.gamecharacterTest](#class-boss)              |
| [object.gamelandformTest](#class-ceilingunit)        |
| [object.gameprojectile](#class-bubble)               |
| [object.strategy](#class-landformunit)               |
| [object.worlds](#class-leveladapter)                 |



### Test Plan:



#### Class DeathController

------

##### Function: initialize

| What the test suppose to do | To test initialization of death view |
| --------------------------- | ------------------------------------ |

| Test | Input | Except Outcome                      | Test Outcome                        | Result |
| ---- | ----- | ----------------------------------- | ----------------------------------- | ------ |
| 1    | null  | Text has text "Name: null Score: 0" | Text has text "Name: null Score: 0" | Pass   |



#### Class GameStartController

------

##### Function: initialize

| What the test suppose to do | To test initialization of game start view |
| --------------------------- | ----------------------------------------- |

| Test | Input       | Except Outcome           | Test Outcome             | Result |
| ---- | ----------- | ------------------------ | ------------------------ | ------ |
| 1    | "#startBtn" | Labeled has text "START" | Labeled has text "Start" | Pass   |
| 2    | "#helpBtn"  | Labeled has text "HELP"  | Labeled has text "HELP"  | Pass   |
| 3    | "#setUpBtn" | Labeled has text "SETUP" | Labeled has text "SETUP" | Pass   |
| 4    | "#scoreBtn" | Labeled has text "SCORE" | Labeled has text "SCORE" | Pass   |
| 5    | "#exitBtn"  | Labeled has text "QUIT"  | Labeled has text "QUIT"  | Pass   |



#### Class SetNameController

------

##### Function: initialize

| What the test suppose to do | Test initialization of set name view |
| --------------------------- | ------------------------------------ |

| Test | Input        | Except Outcome                            | Test Outcome                              | Result |
| ---- | ------------ | ----------------------------------------- | ----------------------------------------- | ------ |
| 1    | "#sureBtn"   | Labeled has text "Yes, I'm sure"          | Labeled has text "Yes, I'm sure"          | Pass   |
| 2    | "#returnBtn" | Labeled has text "I have no idea"         | Labeled has text "I have no idea"         | Pass   |
| 3    | "#label"     | Labeled has text "Please Enter your Name" | Labeled has text "Please Enter your Name" | Pass   |

##### Function: clickOnSureBtn

| What the test suppose to do | Test action that clicking sureBtn button |
| --------------------------- | ---------------------------------------- |

| Test | Input      | Except Outcome                                      | Test Outcome                                        | Result |
| ---- | ---------- | --------------------------------------------------- | --------------------------------------------------- | ------ |
| 1    | "#sureBtn" | Labeled has text "!Please input at least one char!" | Labeled has text "!Please input at least one char!" | Pass   |



#### Class SetUpController

------

##### Function: setBackground1

| What the test suppose to do | Test action that clicking one button |
| --------------------------- | ------------------------------------ |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 1 | True           | True         | Pass   |

##### Function: setBackground2

| What the test suppose to do | Test action that clicking two button |
| --------------------------- | ------------------------------------ |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 2 | True           | True         | Pass   |

##### Function: setBackground3

| What the test suppose to do | Test action that clicking three button |
| --------------------------- | -------------------------------------- |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 3 | True           | True         | Pass   |

##### Function: setBackground4

| What the test suppose to do | Test action that clicking four button |
| --------------------------- | ------------------------------------- |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 4 | True           | True         | Pass   |

##### Function: setBackground5

| What the test suppose to do | Test action that clicking five button |
| --------------------------- | ------------------------------------- |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 5 | True           | True         | Pass   |

##### Function: setBackground6

| What the test suppose to do | Test action that clicking six button |
| --------------------------- | ------------------------------------ |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 6 | True           | True         | Pass   |

##### Function: setBackground7

| What the test suppose to do | Test action that clicking seven button |
| --------------------------- | -------------------------------------- |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 7 | True           | True         | Pass   |

##### Function: setBackground8

| What the test suppose to do | Test action that clicking eight button |
| --------------------------- | -------------------------------------- |

| Test | Input                          | Except Outcome | Test Outcome | Result |
| ---- | ------------------------------ | -------------- | ------------ | ------ |
| 1    | SetUpController.getbgNum() , 8 | True           | True         | Pass   |



#### Class WinController

------

##### Function: initialize

| What the test suppose to do | Test initialization of win view |
| --------------------------- | ------------------------------- |

| Test | Input      | Except Outcome                      | Test Outcome                        | Result |
| ---- | ---------- | ----------------------------------- | ----------------------------------- | ------ |
| 1    | "#winText" | Text has text "Name: null Score: 0" | Text has text "Name: null Score: 0" | Pass   |



#### Class Enemy

------

##### Function: collideWithWall

| What the test suppose to do | Test enemy collide with wall unit |
| --------------------------- | --------------------------------- |

| Test | Input                | Except Outcome        | Test Outcome          | Result |
| ---- | -------------------- | --------------------- | --------------------- | ------ |
| 1    | enemy.getDirection() | -enemy.getDirection() | -enemy.getDirection() | Pass   |

##### Function: collideWithFlorr

| What the test suppose to do | Test enemy collide with floor unit |
| --------------------------- | ---------------------------------- |

| Test | Input                | Except Outcome | Test Outcome | Result |
| ---- | -------------------- | -------------- | ------------ | ------ |
| 1    | enemy.getyVelocity() | 0              | 0            | Pass   |

##### Function: collideWithCeiling

| What the test suppose to do | Test enemy collide with seiling unit |
| --------------------------- | ------------------------------------ |

| Test | Input                | Except Outcome | Test Outcome | Result |
| ---- | -------------------- | -------------- | ------------ | ------ |
| 1    | enemy.getyVelocity() | 0              | 0            | Pass   |

##### Function: drawOn

| What the test suppose to do | Test coordinate of Imageview of enemy |
| --------------------------- | ------------------------------------- |

| Test | Input            | Except Outcome | Test Outcome | Result |
| ---- | ---------------- | -------------- | ------------ | ------ |
| 1    | imageView.getX() | enemy.getX()   | enemy.getX() | Pass   |
| 2    | imageView.getY() | enemy.getY()   | enemy.getY() | Pass   |

##### Function: jump

| What the test suppose to do | Test jump action of enemy |
| --------------------------- | ------------------------- |

| Test | Input        | Except Outcome | Test Outcome   | Result |
| ---- | ------------ | -------------- | -------------- | ------ |
| 1    | enemy.getY() | enemy.getY()-1 | enemy.getY()-1 | Pass   |

##### Function: collideWithProjectile

| What the test suppose to do | Test enemy collide with projectile |
| --------------------------- | ---------------------------------- |

| Test | Input                | Except Outcome | Test Outcome | Result |
| ---- | -------------------- | -------------- | ------------ | ------ |
| 1    | enemy.getyVelocity() | 0              | 0            | Pass   |
| 2    | enemy.getxAccel()    | 0              | 0            | Pass   |
| 3    | enemy.getyAccel()    | -0.1           | -0.1         | Pass   |

#### Class Boss

------

##### function: drawOn

| What the test suppose to do | Test coordinate imageview of boss |
| --------------------------- | --------------------------------- |

| Test | Input            | Except Outcome | Test Outcome | Result |
| ---- | ---------------- | -------------- | ------------ | ------ |
| 1    | imageView.getX() | boss.getX()    | boss.getX()  | Pass   |
| 2    | imageView.getY() | boss.getY()    | boss.getY()  | Pass   |



##### function: collideWithWall

| What the test suppose to do | Test action that Boss collides with wall |
| --------------------------- | ---------------------------------------- |

| Test | Input               | Except Outcome       | Test Outcome         | Result |
| ---- | ------------------- | -------------------- | -------------------- | ------ |
| 1    | boss.getDirection() | -boss.getDirection() | -boss.getDirection() | Pass   |

##### function: collideWithFloor

| What the test suppose to do | Test action that Boss collides with floor |
| --------------------------- | ----------------------------------------- |

| Test | Input               | Except Outcome | Test Outcome | Result |
| ---- | ------------------- | -------------- | ------------ | ------ |
| 1    | boss.getyVelocity() | 0              | 0            | Pass   |

##### function: collideWithCeiling

| What the test suppose to do | Test action that Boss collides with ceiling |
| --------------------------- | ------------------------------------------- |

| Test | Input               | Except Outcome | Test Outcome | Result |
| ---- | ------------------- | -------------- | ------------ | ------ |
| 1    | boss.getyVelocity() | 0              | 0            | Pass   |

##### function: collideWithCeiling

| What the test suppose to do | Test action that Boss collides with land |
| --------------------------- | ---------------------------------------- |

| Test | Input               | Except Outcome | Test Outcome | Result |
| ---- | ------------------- | -------------- | ------------ | ------ |
| 1    | boss.getxVelocity() | 0              | 0            | Pass   |
| 2    | boss.getyAccel()    | 0              | 0            | Pass   |



#### Class Hero

------

##### function: drawOn

| What the test suppose to do | Test coordinate imageview of hero |
| --------------------------- | --------------------------------- |

| Test | Input            | Except Outcome | Test Outcome | Result |
| ---- | ---------------- | -------------- | ------------ | ------ |
| 1    | imageView.getX() | hero.getX()    | hero.getX()  | Pass   |
| 2    | imageView.getY() | hero.getY()    | hero.getY()  | Pass   |

##### Function: jump

| What the test suppose to do | Test jump action of hero |
| --------------------------- | ------------------------ |

| Test | Input               | Except Outcome | Test Outcome | Result |
| ---- | ------------------- | -------------- | ------------ | ------ |
| 1    | hero.isOnPlatform   | False          | False        | Pass   |
| 2    | hero.getyVelocity() | -18            | -18          | Pass   |

##### function: collideWithCeiling

| What the test suppose to do | Test action that hero collides with land |
| --------------------------- | ---------------------------------------- |

| Test | Input               | Except Outcome | Test Outcome | Result |
| ---- | ------------------- | -------------- | ------------ | ------ |
| 1    | hero.getxVelocity() | 0              | 0            | Pass   |
| 2    | hero.getxAccel()    | 0              | 0            | Pass   |

##### function: collideWithFloor

| What the test suppose to do | Test action that hero collides with floor |
| --------------------------- | ----------------------------------------- |

| Test | Input               | Except Outcome | Test Outcome | Result |
| ---- | ------------------- | -------------- | ------------ | ------ |
| 1    | hero.getyVelocity() | 0              | 0            | Pass   |

##### function: setChargeToReady

| What the test suppose to do | Test set charge to ready method |
| --------------------------- | ------------------------------- |

| Test | Input            | Except Outcome | Test Outcome | Result |
| ---- | ---------------- | -------------- | ------------ | ------ |
| 1    | hero.chargeTimer | 500000         | 500000       | Pass   |

#### Class LandFactory

------

##### function: setChargeToReady

| What the test suppose to do | Test LandformUnits creation correctness |
| --------------------------- | --------------------------------------- |

| Test | Input                    | Except Outcome                              | Test Outcome                                | Result |
| ---- | ------------------------ | ------------------------------------------- | ------------------------------------------- | ------ |
| 1    | floorUnit.getHitbox()    | factory.getLand("floor",15,15).getHitbox()  | factory.getLand("floor",15,15).getHitbox()  | Pass   |
| 2    | wallUnit.getHitbox()     | factory.getLand("wall",1,1).getHitbox()     | factory.getLand("wall",1,1).getHitbox()     | Pass   |
| 3    | platformUnit.getHitbox() | factory.getLand("platform",1,1).getHitbox() | factory.getLand("platform",1,1).getHitbox() | Pass   |
| 4    | ceilingUnit.getHitbox()  | factory.getLand("ceiling",1,1).getHitbox()  | factory.getLand("ceiling",1,1).getHitbox()  | Pass   |

#### Class CeilingUnit

------

##### function:collideWith

| What the test suppose to do | Test ceiling unit collide with gravity |
| --------------------------- | -------------------------------------- |

| Test | Input                  | Except Outcome | Test Outcome | Result |
| ---- | ---------------------- | -------------- | ------------ | ------ |
| 1    | gravity.getyVelocity() | 0              | 0            | Pass   |

##### function:moveTo

| What the test suppose to do | Test move method, if moved game object coordinate change |
| --------------------------- | -------------------------------------------------------- |

| Test | Input          | Except Outcome                      | Test Outcome                        | Result |
| ---- | -------------- | ----------------------------------- | ----------------------------------- | ------ |
| 1    | gravity.getY() | ceiling.getY()+ gravity.getHeight() | ceiling.getY()+ gravity.getHeight() | Pass   |



#### Class FloorUnit

------

##### function:collideWith

| What the test suppose to do | Test floorunit collide with gravity |
| --------------------------- | ----------------------------------- |

| Test | Input                  | Except Outcome | Test Outcome | Result |
| ---- | ---------------------- | -------------- | ------------ | ------ |
| 1    | gravity.getyVelocity() | 0              | 0            | Pass   |

##### function: moveTo

| What the test suppose to do | Test move method, if moved game object coordinate change |
| --------------------------- | -------------------------------------------------------- |

| Test | Input          | Except Outcome                   | Test Outcome                     | Result |
| ---- | -------------- | -------------------------------- | -------------------------------- | ------ |
| 1    | gravity.getY() | floor.getY()-gravity.getHeight() | floor.getY()-gravity.getHeight() | Pass   |

#### Class PlatformUnit

------

##### function:collideWith

| What the test suppose to do | Test move method, if moved game object coordinate change |
| --------------------------- | -------------------------------------------------------- |

| Test | Input          | Except Outcome                        | Test Outcome                          | Result |
| ---- | -------------- | ------------------------------------- | ------------------------------------- | ------ |
| 1    | gravity.getY() | platform.getY()-gravity.getHeight()   | platform.getY()-gravity.getHeight()   | Pass   |
| 2    | gravity.getY() | platform.getY()+ platform.getHeight() | platform.getY()+ platform.getHeight() | Pass   |

#### Class WallUnit

------

##### function:collideWith

| What the test suppose to do | Test move method, if moved game object coordinate change |
| --------------------------- | -------------------------------------------------------- |

| Test | Input          | Except Outcome                 | Test Outcome                   | Result |
| ---- | -------------- | ------------------------------ | ------------------------------ | ------ |
| 1    | gravity.getX() | wall.getX()-gravity.getWidth() | wall.getX()-gravity.getWidth() | Pass   |
| 2    | gravity.getX() | wall.getX()+gravity.getWidth() | wall.getX()+gravity.getWidth() | Pass   |



#### Class Bubble

------

##### Function: drawOn

| What the test suppose to do | Test coordinate imageview of bubble |
| --------------------------- | ----------------------------------- |

| Test | Input            | Except Outcome | Test Outcome  | Result |
| ---- | ---------------- | -------------- | ------------- | ------ |
| 1    | imageView.getX() | bubble.getX()  | bubble.getX() | Pass   |
| 2    | imageView.getY() | bubble.getY()  | bubble.getY() | Pass   |

#### Class EnemyProjectile

------

##### function: drawOn

| What the test suppose to do | Test coordinate imageview of enemy projectile |
| --------------------------- | --------------------------------------------- |

| Test | Input            | Except Outcome    | Test Outcome      | Result |
| ---- | ---------------- | ----------------- | ----------------- | ------ |
| 1    | imageView.getX() | projectile.getX() | projectile.getX() | Pass   |
| 2    | imageView.getY() | projectile.getY() | projectile.getY() | Pass   |

#### Class Fire

------

##### function: drawOn

| What the test suppose to do | Test coordinate imageview of fire |
| --------------------------- | --------------------------------- |

| Test | Input            | Except Outcome | Test Outcome | Result |
| ---- | ---------------- | -------------- | ------------ | ------ |
| 1    | imageView.getX() | fire.getX()    | fire.getX()  | Pass   |
| 2    | imageView.getY() | fire.getY()    | fire.getY()  | Pass   |

#### Class HeroProjectile

------

##### function: drawOn

| What the test suppose to do | Test coordinate imageview of hero projectile |
| --------------------------- | -------------------------------------------- |

| Test | Input            | Except Outcome    | Test Outcome      | Result |
| ---- | ---------------- | ----------------- | ----------------- | ------ |
| 1    | imageView.getX() | projectile.getX() | projectile.getX() | Pass   |
| 2    | imageView.getY() | projectile.getY() | projectile.getY() | Pass   |

##### function: collideWithCeiling

| What the test suppose to do | Test hero projectile collide  with ceiling |
| --------------------------- | ------------------------------------------ |

| Test | Input                     | Except Outcome | Test Outcome | Result |
| ---- | ------------------------- | -------------- | ------------ | ------ |
| 1    | projectile.getyVelocity() | 0              | 0            | Pass   |
| 2    | projectile.getyAccel()    | 0              | 0            | Pass   |

#### Class LandformUnit

------

##### Function: getHitbox

| What the test suppose to do | Test hit box correctness |
| --------------------------- | ------------------------ |

| Test | Input            | Except Outcome                                               | Test Outcome                                                 | Result |
| ---- | ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ |
| 1    | land.getHitbox() | Rectangle2D [minX = 20.0, minY=20.0, maxX=40.0, maxY=40.0, width=20.0, height=20.0] | Rectangle2D [minX = 20.0, minY=20.0, maxX=40.0, maxY=40.0, width=20.0, height=20.0] | Pass   |

##### Function: isOverlaps

| What the test suppose to do | Test if landform overlap with other game object |
| --------------------------- | ----------------------------------------------- |

| Test | Input                        | Except Outcome | Test Outcome | Result |
| ---- | ---------------------------- | -------------- | ------------ | ------ |
| 1    | land.isOverlaps(collideLand) | True           | True         | Pass   |

##### Function: markToRemove

| What the test suppose to do | Test if landform is mark to remove state |
| --------------------------- | ---------------------------------------- |

| Test | Input              | Except Outcome | Test Outcome | Result |
| ---- | ------------------ | -------------- | ------------ | ------ |
| 1    | land.isCanRemove() | True           | True         | Pass   |

##### function: drawOn

| What the test suppose to do | Test coordinate imageview of hero projectile |
| --------------------------- | -------------------------------------------- |

| Test | Input            | Except Outcome | Test Outcome | Result |
| ---- | ---------------- | -------------- | ------------ | ------ |
| 1    | imageView.getX() | land.getX()    | land.getX()  | Pass   |
| 2    | imageView.getY() | land.getY()    | land.getY()  | Pass   |

#### Class LevelAdapter

------

##### Function: getMapPath

| What the test suppose to do | Test correctness of image path obtained |
| --------------------------- | --------------------------------------- |

| Test | Input                       | Except Outcome                                             | Test Outcome                                               | Result |
| ---- | --------------------------- | ---------------------------------------------------------- | ---------------------------------------------------------- | ------ |
| 1    | adapter.getMapPath().read() | getClass().getResourceAsStream("/world/World1.txt").read() | getClass().getResourceAsStream("/world/World1.txt").read() | Pass   |

