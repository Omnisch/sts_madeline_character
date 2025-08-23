package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import omnismadeline.cards.TransitionDash;

import static omnismadeline.MadelineMod.makeID;

public class ResurrectionsPower extends BasePower {
    public static final String POWER_ID = makeID("Resurrections");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final AbstractCard cardToMake = new TransitionDash();

    public ResurrectionsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new MakeTempCardInHandAction(cardToMake.makeStatEquivalentCopy(), this.amount));
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
