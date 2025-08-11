package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static omnismadeline.MadelineMod.makeID;

public class ScatteredAndLostPower extends BasePower {
    public static final String POWER_ID = makeID("ScatteredAndLost");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ScatteredAndLostPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        --AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.addToBot(new BetterDiscardPileToHandAction(1));
    }

    @Override
    public void onRemove() {
        ++AbstractDungeon.player.gameHandSize;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
