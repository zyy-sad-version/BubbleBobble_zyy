package pers.yuyanzhou.bubblebobble.support;

/**
 * Set used to setting any GameObject property.
 * Simply use this property by call Set.ENUM_PARAMETER.getInt() or Set.ENUM_PARAMETER.getDou()
 * @author YuyanZhou
 */
public enum Set {
    //Values for the size of the game window and the overall size of the game
    UNIT_SIZE (20),
    WIDTH(40),
    HEIGHT(34),
    //About the hero data attributes
    HERO_JUMP_SPEED(18),
    HERO_TERMINAL_VELOCITY_X(6),
    HERO_SIZE(40),
    HERO_WALK(2),
    HERO_RUN(4),
    HERO_RUN_ACCEL(6),
    HERO_SHIELD_TIME(100),
    HERO_STUNNED_TIME(250),
    HERO_SHOOT_DELAY(1),
    //About the enemy data attributes
    ENEMY_WIDTH(40),
    ENEMY_HEIGHT(40),
    ENEMY_JUMP_SPEED(20),
    ENEMY_TERMINAL_VELOCITY_X(4),
    ENEMY_BUBBLED_FRAMES(300),
    ENEMY_CHANGE_MOVEMENT_CHANCE(0.01),
    ENEMY_SHOOT_PROJECTILE_CHANCE(0.005),
    //About the boss data attributes
    BOSS_HEALTH_TIMER(30),
    BOSS_WIDTH(200),
    BOSS_HEIGHT(200),
    BOSS_SHOOT_PROJECTILE_CHANCE(0.01),
    BOSS_CHANG_MOVEMENT_CHANCE(0.01),
    //Enemy projectile
    ENEMY_PROJECTILE_SIZE(20),
    ENEMY_PROJECTILE_SPEED(15),
    PROJECTILE_RATE(0.25),
    PROJECTILE_Y_RATE(25),
    PROJECTILE_TERMINAL_VELOCITY_Y(5),
    PROJECTILE_DISAPPEAR_TIME(-200),
    //Hero projectile
    HERO_PROJECTILE_SIZE(20),
    HERO_PROJECTILE_SPEED(15),

    //Fire setting
    FIRE_SIZE(50),
    FIRE_SPEED(10),
    FIRE_ACTIVE_TIME(-100),
    FIRE_DISAPPEAR_TIME(-120),
//Game data setting
    STATIC_FRICTION(1),
    GRAVITY(1),
    TERMINAL_FALL_SPEED(20),
    //Fruit Setting
    FRUIT_SIZE(20),
    FRUIT_TERMINAL_VELOCITY_Y(8),

    //GoldCoin Setting
    GOLD_SIZE(80),
    GOLD_TERMINAL_VELOCITY_Y(10),
    //Bubble set
    BUBBLE_CHARGE_TIME(500_000);

    private int dataInt;
    private double dataDou;
    Set(int dataInt){
    this.dataInt = dataInt;
}
    Set(double dataDou){
    this.dataDou = dataDou;
}

    /**
     * Get int type property
     * @return int type property
     */
    public int getInt() {
        return dataInt;
    }

    /**
     * Get double type property
     * @return double type property
     */
    public double getDou() {
        return dataDou;
    }
}
