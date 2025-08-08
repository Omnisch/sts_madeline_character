package omnismadeline.stances;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static omnismadeline.MadelineMod.modID;
import static omnismadeline.util.MadelineUtils.*;

public class LandStance extends AbstractStance {
    public static final String STANCE_ID = modID + ":" + "Land";
    private static final StanceStrings stanceStrings;
    //private static long sfxId;
    private final AbstractPlayer p;

    public LandStance() {
        this.ID = STANCE_ID;
        this.name = stanceStrings.NAME;
        this.p = AbstractDungeon.player;
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

    @Override
    public void onEnterStance() {
        if (!canDash(p)) {
            refillDashes(p);
        }
    }

    @Override
    public void atStartOfTurn() {
        if (!canDash(p)) {
            refillDashes(p);
        }
    }

    static {
        stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        //sfxId = -1L;
    }
}
