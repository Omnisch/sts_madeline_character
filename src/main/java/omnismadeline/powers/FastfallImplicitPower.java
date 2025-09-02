package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.stances.LandStance;

import static omnismadeline.MadelineMod.makeID;

public class FastfallImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("Fastfall");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public FastfallImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ChangeStanceAction(new LandStance()));
        this.addToBot(new ReducePowerAction(p, p, this, 1));
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
