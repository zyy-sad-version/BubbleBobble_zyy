package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

import pers.yuyanzhou.bubblebobble.support.SoundEffect;

/**
 *State of enemy when it is normal
 * @author YuyanZhou
 */
public class NormalState implements EnemyState{
    private Enemy enemy;

    /**
     * Constructor of bubbledState, bind to an enemy
     * @param enemy which was bound to.
     */
    public NormalState(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * when enemy in normal state, nothing happen
     */
    @Override
    public void collideWithCeiAndFloor() {
    }
    /**
     * when enemy in normal state, nothing happen
     */
    @Override
    public void collideWithWall() {
    }
    /**
     * When enemy in normal state, set y velocity to 0, change state to on platform state
     */
    @Override
    public void collideWithPlatform() {
        enemy.setyVelocity(0);
        enemy.state = new OnPlatformState(enemy);
    }

    /**
     * When enemy in normal state, reverse enemy. Change state to turn away state
     */
    @Override
    public void collideWithShield() {
        enemy.reverseDirection();
        enemy.state = new TurnAwayState(enemy);
    }

    /**
     * When enemy in normal state, it attacked by hero projectile. A sound effect play and change state to bubbled
     */
    @Override
    public void beBubbled() {
        SoundEffect.BUBBLED.play();
        enemy.state = new BubbledState(this.enemy);
    }

    /**
     * When enemy in update state, nothing happen.
     */
    @Override
    public void update() {
    }

    /**
     * When enemy in normal state, change enemy coordinate.
     */
    @Override
    public void jump() {
        enemy.setY(enemy.getY()-1);
        enemy.setyVelocity(-enemy.jumpSpeed);
    }
}
