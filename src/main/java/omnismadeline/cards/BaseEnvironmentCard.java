package omnismadeline.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomTags;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.util.CardStats;

import static omnismadeline.MadelineMod.characterPath;

public abstract class BaseEnvironmentCard extends BaseCard {
    private static final UIStrings uiStrings;
    private static final String NOT_MOVED_MESSAGE;
    public boolean isAboutToMove = false;

    public BaseEnvironmentCard(String ID, CardStats info) {
        super(ID, info);
        final String cardType =
                info.cardType == CardType.ATTACK ? "_attack" :
                        info.cardType == CardType.SKILL ? "_skill" :
                                info.cardType == CardType.POWER ? "_power" : null;
        if (cardType != null) {
            setBackgroundTexture(characterPath("cardback/bg"+ cardType +"_env.png"), characterPath("cardback/bg"+ cardType + "_env_p.png"));
        }
        tags.add(CustomTags.ENVIRONMENT);
    }

    @Override
    public void onMoveToDiscard() {
        isAboutToMove = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onUse(abstractPlayer, abstractMonster);

        GAM_fieldPatch.totalEnvrPlayedThisTurn++;
        GAM_fieldPatch.totalEnvrPlayedThisCombat++;

        this.autoPlayed = false;
        this.movedFromCardTag = null;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (this.autoPlayed) {
            return true;
        } else if (!this.isAboutToMove) {
            this.cantUseMessage = NOT_MOVED_MESSAGE;
            return false;
        } else {
            return true;
        }
    }

    // used in DemoDashImplicitPower

    private boolean hasCostModifiedForTurn = false;
    private int lastCostForTurn = 0;

    public boolean ReduceTmpCost(int byNumber) {
        if (this.costForTurn <= 0) {
            return false;
        } else {
            this.hasCostModifiedForTurn = this.isCostModifiedForTurn;
            this.isCostModifiedForTurn = true;

            this.lastCostForTurn = this.costForTurn;
            if (this.costForTurn <= byNumber) {
                this.costForTurn = 0;
            } else {
                this.costForTurn -= byNumber;
            }

            return true;
        }
    }

    public void RevertTmpCost() {
        if (this.lastCostForTurn != 0) {
            this.isCostModifiedForTurn = this.hasCostModifiedForTurn;
            this.costForTurn = this.lastCostForTurn;

            this.lastCostForTurn = 0;
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        this.RevertTmpCost();
    }

    // !used in DemoDashImplicitPower

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BaseEnvironmentCard.class.getSimpleName()));
        NOT_MOVED_MESSAGE = uiStrings.TEXT[0];
    }
}
