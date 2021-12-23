package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

import pers.yuyanzhou.bubblebobble.support.Set;

/**
 * State of enemy when it was bubbled by hero projectile
 * @author YuyanZHOU
 */
public class BubbledState implements EnemyState {
    private  Enemy enemy;

    /**
     * Constructor of bubbledState, bind to an enemy
     * @param enemy which was bound to.
     */
    public BubbledState(Enemy enemy) {
        this.enemy = enemy;
    }
    /**
     * when enemy in bubbled state, its y velocity set to 0, y accel set to 0
     */
    @Override
    public void collideWithCeiAndFloor() {
        enemy.setyVelocity(0);
        enemy.setyAccel(0);
    }
    /**
     * when enemy in bubbled state, its y velocity set to 0, y accel set to 0
     */
    @Override
    public void collideWithWall() {
        enemy.setxVelocity(0);
        enemy.setyAccel(0);
    }
    /**
     * when enemy in bubbled state, its y velocity set to 0.
     */
    @Override
    public void collideWithPlatform() {
        enemy.setyVelocity(0);
    }
    /**
     * when enemy in bubbled state, nothing happen
     */
    @Override
    public void collideWithShield() {
    }

    /**
     * handle enemy was bubbled
     */
    @Override
    public void beBubbled() {
        enemy.setyVelocity(0);
        enemy.setxAccel(0);
        enemy.setyAccel(-0.1);
    }

    /**
     * update enemy when it is bubbled
     */
    @Override
    public void update() {
        enemy.setTimer(enemy.getTimer()-1);
        if(enemy.getTimer()<=0){
            enemy.setTimer(Set.ENEMY_BUBBLED_FRAMES.getInt());
            enemy.setxAccel(1.5);
            enemy.setDirection(1);
            if (Math.random() < 0.5) {
                enemy.reverseDirection();
            }
            enemy.setyAccel(Set.GRAVITY.getInt());
            enemy.state=new NormalState(this.enemy);
        }
    }
    /**
     * when enemy in bubbled state, nothing happen
     */
    @Override
    public void jump() {
    }
}
