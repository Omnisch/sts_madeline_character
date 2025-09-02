package omnismadeline.stances;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import omnismadeline.actions.MadelineLoseMomentumAction;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
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
    public void onEnterStance() {
        this.checkLand();
    }

    @Override
    public void atStartOfTurn() {
        this.checkLand();
    }

    public void checkLand() {
        AbstractDungeon.actionManager.addToBottom(new MadelineRefillAction());
        if (!p.hasPower(HeartOfTheMountainPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new MadelineLoseMomentumAction());
        }
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (p.hasPower(DashChancePower.POWER_ID)) {
            final int dashChanceMaxAmount = ((DashChancePower) p.getPower(DashChancePower.POWER_ID)).maxAmount;
            this.description = stanceString.DESCRIPTION[0] + dashChanceMaxAmount + stanceString.DESCRIPTION[1];
        } else {
            final int dashChanceMaxAmount = 1;
            this.description = stanceString.DESCRIPTION[0] + dashChanceMaxAmount + stanceString.DESCRIPTION[1];
        }

        if (!p.hasPower(HeartOfTheMountainPower.POWER_ID)) {
            this.description = this.description + stanceString.DESCRIPTION[2];
        }
    }

    @Override
    public void updateAnimation() {
        final MadelineCharacter madeline = (MadelineCharacter) AbstractDungeon.player;
        int dashChanceAmount = 0;
        if (madeline.hasPower(DashChancePower.POWER_ID)) {
            dashChanceAmount = madeline.getPower(DashChancePower.POWER_ID).amount;
        }
        madeline.changeAnimation(dashChanceAmount);
    }
}
