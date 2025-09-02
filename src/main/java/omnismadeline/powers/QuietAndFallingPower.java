package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static omnismadeline.MadelineMod.makeID;

public class QuietAndFallingPower extends BasePower {
    public static final String POWER_ID = makeID("QuietAndFalling");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractPlayer p;

    public QuietAndFallingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        p = AbstractDungeon.player;
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (p.hand.isEmpty()) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.amount), this.amount));
            this.flash();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
