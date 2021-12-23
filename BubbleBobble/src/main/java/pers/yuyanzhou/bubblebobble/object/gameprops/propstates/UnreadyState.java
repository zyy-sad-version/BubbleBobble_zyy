package pers.yuyanzhou.bubblebobble.object.gameprops.propstates;
import pers.yuyanzhou.bubblebobble.object.gameprops.GameProp;

/**
 *UnreadyState class handles game props at unready to collect state.
 * @author YuyanZhou
 */
public class UnreadyState implements PropState{
private GameProp prop;

    /**
     * Constructor of unready state.
     * @param prop The game prop bounds to this state.
     */
    public UnreadyState(GameProp prop) {
    this.prop = prop;
    }

    /**
     * At unready state, nothing happen
     */
    @Override
    public void collideWithHero() {
    }
    /**
     * At unready state, if game props collide with land(floor/platform), its xVelocity shall be set as 0.
     * Then, game props state change to readyToCollect state.
     */
    @Override
    public void collideWithLand() {
        prop.setxVelocity(0);
        if(!prop.isCanRemove()){
            prop.setState(new ReadyToCollectState(prop));
        }
    }
}
