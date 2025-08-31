package omnismadeline.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(DashChancePower.POWER_ID)) {
            ((DashChancePower) p.getPower(DashChancePower.POWER_ID)).maxAmount = DashChancePower.upgradedMaxAmount;
        }
    }

    @Override
    public void onRemove() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(DashChancePower.POWER_ID)) {
            ((DashChancePower) p.getPower(DashChancePower.POWER_ID)).maxAmount = DashChancePower.baseMaxAmount;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
