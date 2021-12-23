package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

import pers.yuyanzhou.bubblebobble.support.SoundEffect;

/**
 * State of enemy when it was on a platform.In this state, enemy turning away from hero (if hero equipped shield) until getTurningAwayCount = 0.
 * @author YuyanZhou
 *
 */
public class TurnAwayState implements EnemyState{
    private Enemy enemy;
    /**
     * Constructor of turnAwayState, bind to an enemy
     * @param enemy which was bound to.
     */
    public TurnAwayState(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Handle turning away count change
     */
    @Override
    public void collideWithShield() {
        if (enemy.getTurningAwayCount()<= 0) {
            enemy.setTurnAwayCount(10);
            enemy.state = new NormalState(enemy);
        }
        enemy.setTurnAwayCount(enemy.getTurningAwayCount()-1);
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
     * When enemy in this state, nothing happen
     */
    @Override
    public void update() {
    }
    /**
     * When enemy in turingaway state, nothing happen
     */
    @Override
    public void jump() {
    }
    /**
     * When enemy in turingaway state, nothing happen
     */
    @Override
    public void collideWithCeiAndFloor() {
    }
    /**
     * When enemy in turingaway state, nothing happen
     */
    @Override
    public void collideWithWall() {
    }

    /**
     * When enemy in turingaway state, set enemy y velocity to 0. Change state to onPlatform state
     */
    @Override
    public void collideWithPlatform() {
        enemy.setyVelocity(0);
        enemy.state = new OnPlatformState(this.enemy);
    }
}
