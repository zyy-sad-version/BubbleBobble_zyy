package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

import pers.yuyanzhou.bubblebobble.support.Set;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

/**
 * State of enemy when it was on a platform
 * @author YuyanZhou
 */
public class OnPlatformState implements EnemyState{
    private Enemy enemy;

    /**
     * Constructor of onPlatformState, bind to an enemy
     * @param enemy which was bound to.
     */
    public OnPlatformState(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * When enemy in this state, nothing happen
     */
    @Override
    public void collideWithCeiAndFloor() {
    }
    /**
     * When enemy in this state, nothing happen
     */
    @Override
    public void collideWithWall() {
    }
    /**
     * When enemy in this state, y accel set to 0
     */
    @Override
    public void collideWithPlatform() {
        enemy.setyAccel(0);
    }
    /**
     * When enemy in this state, reverse enemy
     */
    @Override
    public void collideWithShield() {
        enemy.reverseDirection();
        enemy.state = new TurnAwayState(this.enemy);
    }

    /**
     * When enemy in this state, it attacked by hero projectile. A sound effect play and change state to bubbled
     */
    @Override
    public void beBubbled() {
        SoundEffect.BUBBLED.play();
        enemy.state = new BubbledState(this.enemy);
    }
    /**
     * update enemy when it is on platform
     */
    @Override
    public void update() {
        if(Math.random()< Set.ENEMY_CHANGE_MOVEMENT_CHANCE.getDou()){
            enemy.jump();
        }
        if(Math.random()<Set.ENEMY_CHANGE_MOVEMENT_CHANCE.getDou()){
            enemy.reverseDirection();
        }
        if(Math.random()<Set.ENEMY_SHOOT_PROJECTILE_CHANCE.getDou()){
            enemy.shootProjectile();
        }
        enemy.state = new NormalState(this.enemy);
    }

    /**
     * Handles jump action, when enemy state is onPlatform state
     */
    @Override
    public void jump() {
        enemy.setY(enemy.getY()-1);
        enemy.setyVelocity(-enemy.jumpSpeed);
        enemy.state = new NormalState(this.enemy);
    }
}
