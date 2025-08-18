package omnismadeline.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static omnismadeline.MadelineMod.makeID;

public class HeartOfTheMountainPower extends BasePower {
    public static final String POWER_ID = makeID("HeartOfTheMountain");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HeartOfTheMountainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
