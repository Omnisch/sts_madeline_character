package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.actions.MadelineRefillAction;

import static omnismadeline.MadelineMod.makeID;

public class RefillAtNextTurnImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("RefillAtNextTurn");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public RefillAtNextTurnImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new MadelineRefillAction());

        AbstractPlayer p = AbstractDungeon.player;
        if (this.amount == 1) {
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        } else {
            this.addToBot(new ReducePowerAction(p, p, this, 1));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
