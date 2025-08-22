package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static omnismadeline.MadelineMod.makeID;

public class ClimbPower extends BasePower {
    public static final String POWER_ID = makeID("Climb");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public ClimbPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new RemoveSpecificPowerAction(p, p, MomentumPower.POWER_ID));
            this.flash();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
