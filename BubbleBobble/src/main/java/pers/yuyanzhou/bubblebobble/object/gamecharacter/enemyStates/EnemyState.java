package pers.yuyanzhou.bubblebobble.object.gamecharacter.enemyStates;

/**
 *
 */
public interface EnemyState {
        /**
         * Handled collision with ceiling, floor unit
         */
        void collideWithCeiAndFloor();

        /**
         * Handled collision with wall unit
         */
        void collideWithWall();

        /**
         * Handled collision with platform
         */
        void collideWithPlatform();

        /**
         * Handled collision with shield
         */
        void collideWithShield();

        /**
         * Handle action when enemy is bubbled
         */
        void beBubbled();

        /**
         * Handle enemy update
         */
        void update();

        /**
         * Handle enemy jump action
         */
        void jump();
}
