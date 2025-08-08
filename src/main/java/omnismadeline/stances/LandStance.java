package omnismadeline.stances;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static omnismadeline.MadelineMod.makeID;
import static omnismadeline.util.MadelineUtils.*;

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
        this.description = stanceString.DESCRIPTION[0];
        System.out.println("[DEBUG] stance name = " + this.name);
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
}
