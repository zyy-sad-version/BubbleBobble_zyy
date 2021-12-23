package pers.yuyanzhou.bubblebobble.object.gameprops.propstates;

/**
 * A state interface, its method shall implement differently in implement class.
 * @author YuyanZhou
 */
public interface PropState {
    /**
     * handle collision with hero at the state
     */
    void collideWithHero();

    /**
     * handle collision with landform unit at this state
     */
    void collideWithLand();

}
