package omnismadeline.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static omnismadeline.MadelineMod.makeID;

public class DashChancePower extends BasePower {
    public static final String POWER_ID = makeID("DashChance");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public static int maxAmount = 1;

    public DashChancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + maxAmount + DESCRIPTIONS[2];
    }
}
