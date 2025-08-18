package omnismadeline.stances;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.powers.DashChancePower;
import omnismadeline.powers.HeartOfTheMountainPower;
import omnismadeline.powers.MomentumPower;

import static omnismadeline.MadelineMod.makeID;

public class LandStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Land");
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    //private static long sfxId;
    private final AbstractPlayer p;

    public LandStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.p = AbstractDungeon.player;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (p.hasPower(HeartOfTheMountainPower.POWER_ID)) {
            this.description = stanceString.DESCRIPTION[0] + DashChancePower.maxAmount + stanceString.DESCRIPTION[2];
        } else {
            this.description = stanceString.DESCRIPTION[0] + DashChancePower.maxAmount + stanceString.DESCRIPTION[1];
        }
    }

    @Override
    public void onEnterStance() {
        AbstractDungeon.actionManager.addToBottom(new MadelineRefillAction());
        if (!p.hasPower(HeartOfTheMountainPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, p.getPower(MomentumPower.POWER_ID)));
        }
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new MadelineRefillAction());
        if (!p.hasPower(HeartOfTheMountainPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, p.getPower(MomentumPower.POWER_ID)));
        }
        this.updateDescription();
    }
}
