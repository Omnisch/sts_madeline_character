package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.stances.LandStance;

import static omnismadeline.MadelineMod.makeID;

public class PonderingWaterPower extends BasePower {
    public static final String POWER_ID = makeID("PonderingWater");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public PonderingWaterPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void onInitialApplication() {
        this.addToBot(new ChangeStanceAction(new LandStance()));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
