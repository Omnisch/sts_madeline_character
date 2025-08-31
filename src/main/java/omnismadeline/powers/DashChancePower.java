package omnismadeline.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static omnismadeline.MadelineMod.makeID;

public class DashChancePower extends BasePower {
    public static final String POWER_ID = makeID("DashChance");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public static final int baseMaxAmount = 1;
    public static final int upgradedMaxAmount = 2;
    public int maxAmount = baseMaxAmount;

    public DashChancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[3] + maxAmount + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3] + maxAmount + DESCRIPTIONS[4];
        }
    }
}
