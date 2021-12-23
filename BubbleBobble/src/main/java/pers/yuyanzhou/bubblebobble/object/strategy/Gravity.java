package pers.yuyanzhou.bubblebobble.object.strategy;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import pers.yuyanzhou.bubblebobble.object.worlds.GameWorld;
import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * Gravity implements GameObject, Gravity is a game object whose coordinates change in the game world.
 * Gravity integrates some common methods.
 * @author YuyanZhou
 */
public abstract class Gravity implements GameObject{
    private GameWorld world;
    private int x,y;
    private int width,height;

    private double xVelocity, yVelocity;
    private double xAccel, yAccel;
    private int terminalXVelocity, terminalYVelocity;
    private boolean canRemove;
    private int direction;

    /**
     * Constructor of Gravity, pass GameWorld, size, coordinate to create Gravity
     * @param world Where the Gravity need to add
     * @param x x coordinate of Gravity
     * @param y y coordinate of Gravity
     * @param width width of Gravity
     * @param height height of Gravity
     */
    public Gravity(GameWorld world, int x, int y,int width, int height ) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        yAccel = Set.GRAVITY.getInt();
        xAccel = 0;
        xVelocity = 0;
        yVelocity = 0;
        canRemove = false;
        direction  =-1;
        terminalXVelocity = 0;
        setTerminalYVelocity(Set.TERMINAL_FALL_SPEED.getInt());
    }

    /**
     * Get Collision Size of Gravity.
     * @return Collision Size of Gravity.
     */
    @Override
    public Rectangle2D getHitbox(){
        return new Rectangle2D(x,y,width,height);
    }

    /**
     * Check whether the two GameObjects overlap
     * @param object object collide with
     * @return A boolean that true value indicate overlap happened, otherwise, overlap did not happen
     */
    @Override
    public boolean isOverlaps(GameObject object){
        return getHitbox().intersects(object.getHitbox());
    }
    /**
     * move game object to a coordinate
     */
    public void moveTo(Point2D point){
        x=(int) point.getX();
        y=(int) point.getY();
    }

    /**
     * Reverse the direction of the Gravity, change the x acceleration
     */
    public void reverseDirection(){
        xAccel *= -1;
        direction *= -1;
    }

    /**
     * Mark the Gravity as removable.
     *  true indicate this game object can be removed, otherwise it cannot be removed.
     */
    @Override
    public void markToRemove(){
        canRemove =true;
    }

    /**
     * check if something is offscreen
     * @return whether game object is out of screen
     */
    private boolean isOffScreen(){
        boolean xLow = getX() + getWidth() < 0;
        boolean xHigh = getX() > Set.UNIT_SIZE.getInt()*Set.WIDTH.getInt();
        boolean yHigh = getY()>Set.UNIT_SIZE.getInt()*Set.HEIGHT.getInt();
        boolean yLow = getY() + getHeight() < 0;
        return xLow || xHigh || yLow || yHigh;
    }
    /**
     * Handles collision with floor unit
     * */
    public abstract void collideWithFloor();

    /**
     * Handles collision with ceiling unit
     */
    public abstract void collideWithCeiling();

    /**
     * Handles collision with wall unit
     */
    public abstract void collideWithWall();

    /**
     * Handles collision with platform unit
     */
    public abstract void collideWithPlatform();

    /**
     * Handles collision with gravity.
     * @param gravity Object that collided with.
     */
    public abstract void collideWith(Gravity gravity);
    /**
     * update state of gravity object.
     */
    public void update(){
        if (Math.abs(xVelocity) < terminalXVelocity) {
            xVelocity +=xAccel;
        }
        if (Math.abs(xVelocity) > Set.STATIC_FRICTION.getInt()) {
            if (xVelocity < 0) {
              xVelocity+=1;
            } else {
                xVelocity-=1;
            }
            x+=xVelocity;
        }
        if (yVelocity < terminalYVelocity) {
            yVelocity +=yAccel;
        }
        y+=yVelocity;

        if (isOffScreen()) {
            if (y > Set.UNIT_SIZE.getInt()*Set.HEIGHT.getInt()) {
                y=0;
            } else if(y<0){
                y= (int) getWorld().getHeight();
            }if(x>Set.UNIT_SIZE.getInt()*Set.WIDTH.getInt()){
               x=0;
            }else if(x<0){
                x=((int) getWorld().getWidth());
            }
        }
    }
    public void setWorld(GameWorld world) {
        this.world = world;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setxAccel(double xAccel) {
        this.xAccel = xAccel;
    }

    public void setyAccel(double yAccel) {
        this.yAccel = yAccel;
    }

    public void setTerminalXVelocity(int terminalXVelocity) {
        this.terminalXVelocity = terminalXVelocity;
    }

    public void setTerminalYVelocity(int terminalYVelocity) {
        this.terminalYVelocity = terminalYVelocity;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public GameWorld getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public double getxAccel() {
        return xAccel;
    }

    public double getyAccel() {
        return yAccel;
    }

    public boolean isCanRemove() {
        return canRemove;
    }

    public int getDirection() {
        return direction;
    }
}
