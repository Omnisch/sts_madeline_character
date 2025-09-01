package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static omnismadeline.MadelineMod.makeID;

public class JellyfishImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("Jellyfish");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public JellyfishImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
