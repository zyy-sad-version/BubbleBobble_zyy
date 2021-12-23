package pers.yuyanzhou.bubblebobble.object.gameprops.propstates;

import pers.yuyanzhou.bubblebobble.object.gameprops.Fruit;
import pers.yuyanzhou.bubblebobble.object.gameprops.GameProp;
import pers.yuyanzhou.bubblebobble.object.gameprops.GoldCoin;
import pers.yuyanzhou.bubblebobble.support.Score;
import pers.yuyanzhou.bubblebobble.support.SoundEffect;

/**
 *ReadyToCollectState class handles game props at ready to collect state.
 * @author YuyanZhou
 */
public class ReadyToCollectState implements PropState{
    private GameProp prop;
    /**
     * Constructor of ready state
     * @param prop The game prop bounds to this state.
     */
    public ReadyToCollectState(GameProp prop) {
        this.prop = prop;
    }

    /**
     * At ready state, if game props collide with hero, a sound effect plays, score shall increase, and game props mark to remove.
     * Then, game props state change to unready state.
     */
    @Override
    public void collideWithHero() {
        SoundEffect.FRUIT.play();
        if (prop instanceof GoldCoin){
            Score.getInstance().getGold();
        }else if(prop instanceof Fruit)
        {
            Score.getInstance().getFruit();
        }
        prop.markToRemove();
        prop.setState(new UnreadyState(prop));
    }

    /**
     * At ready to collect state, if game props collide with land(floor/platform), its xVelocity shall be set as 0.
     */
    @Override
    public void collideWithLand() {
            prop.setxVelocity(0);
    }
}
