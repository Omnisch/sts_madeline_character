package omnismadeline.cards.dashes;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.actions.MadelinePendAndFlushAction;
import omnismadeline.cards.BaseCard;
import omnismadeline.enums.CustomTags;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.powers.DashChancePower;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

import static omnismadeline.MadelineMod.characterPath;

public abstract class BaseDashCard extends BaseCard {
    private static final UIStrings uiStrings;
    private static final String CANT_DASH_MESSAGE;
    protected static final int GAP = 1;

    public BaseDashCard(String ID, CardStats info) {
        super(ID, info);
        final String cardType =
                info.cardType == CardType.ATTACK ? "_attack" :
                        info.cardType == CardType.SKILL ? "_skill" :
                                info.cardType == CardType.POWER ? "_power" : "";
        setBackgroundTexture(characterPath("cardback/bg"+ cardType +"_dash.png"), characterPath("cardback/bg"+ cardType + "_dash_p.png"));
        this.tags.add(CustomTags.MOVE);
        this.tags.add(CustomTags.DASH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!Objects.equals(p.stance.ID, LandStance.STANCE_ID)) {
            if (p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0) {
                this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, -1), -1));
            }
        }
        onUse(p, m);

        if (!this.autoPlayed) {
            this.addToBot(new MadelineMoveAction(m, GAP, CustomTags.DASH, false, true, true));
        }

        this.addToBot(new MadelineGainMomentumAction(1));

        if (!this.autoPlayed) {
            this.addToBot(new MadelinePendAndFlushAction());
        }

        GAM_fieldPatch.totalDashPlayedThisTurn++;
        GAM_fieldPatch.totalDashPlayedThisCombat++;

        this.autoPlayed = false;
        this.movedFromCardTag = null;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (this.autoPlayed) {
            return true;
        } else if (!hasDashChances(p)) {
            this.cantUseMessage = CANT_DASH_MESSAGE;
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasDashChances(AbstractPlayer p) {
        return p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BaseDashCard.class.getSimpleName()));
        CANT_DASH_MESSAGE = uiStrings.TEXT[0];
    }
}
