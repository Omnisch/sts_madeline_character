package omnismadeline.stances;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.DashChancePower;

import static omnismadeline.MadelineMod.makeID;

public class SoarStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Soar");
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    //private static long sfxId;

    public SoarStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0];
    }

    @Override
    public void updateAnimation() {
        if (AbstractDungeon.player instanceof MadelineCharacter) {
            final MadelineCharacter madeline = (MadelineCharacter) AbstractDungeon.player;
            int dashChanceAmount = 0;
            if (madeline.hasPower(DashChancePower.POWER_ID)) {
                dashChanceAmount = madeline.getPower(DashChancePower.POWER_ID).amount;
            }
            madeline.changeAnimation(dashChanceAmount);
        }
    }
}
