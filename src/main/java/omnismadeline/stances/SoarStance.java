package omnismadeline.stances;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static omnismadeline.MadelineMod.modID;

public class SoarStance extends AbstractStance {
    public static final String STANCE_ID = modID + ":" + "Soar";
    private static final StanceStrings stanceStrings;
    //private static long sfxId;

    public SoarStance() {
        this.ID = STANCE_ID;
        this.name = stanceStrings.NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = stanceStrings.DESCRIPTION[0];
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        //sfxId = -1L;
    }
}
