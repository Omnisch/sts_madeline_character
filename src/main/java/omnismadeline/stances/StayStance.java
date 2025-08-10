package omnismadeline.stances;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import omnismadeline.powers.DashChancePower;

import static omnismadeline.MadelineMod.makeID;
import static omnismadeline.util.MadelineUtils.*;

public class StayStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Stay");
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    //private static long sfxId;
    private final AbstractPlayer p;

    public StayStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.p = AbstractDungeon.player;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0];
    }

    @Override
    public void onEnterStance() {
        if (!hasDashChances(p)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DashChancePower(p, 1), 1));
        }
    }
}
