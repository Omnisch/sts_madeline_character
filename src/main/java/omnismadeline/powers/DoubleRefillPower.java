package omnismadeline.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static omnismadeline.MadelineMod.makeID;

public class DoubleRefillPower extends BasePower {
    public static final String POWER_ID = makeID("DoubleRefill");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public DoubleRefillPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void onInitialApplication() {
        ++DashChancePower.maxAmount;
    }

    @Override
    public void onRemove() {
        --DashChancePower.maxAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
