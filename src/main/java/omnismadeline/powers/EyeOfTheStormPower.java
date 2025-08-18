package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import omnismadeline.patches.GAM_fieldPatch;

import static omnismadeline.MadelineMod.makeID;

public class EyeOfTheStormPower extends BasePower {
    public static final String POWER_ID = makeID("EyeOfTheStorm");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractPlayer p;

    public EyeOfTheStormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        p = AbstractDungeon.player;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (GAM_fieldPatch.totalJumpPlayedThisTurn == 0) {
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.amount), this.amount));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
