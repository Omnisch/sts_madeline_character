package omnismadeline.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelinePendAndFlushAction;
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
                                info.cardType == CardType.POWER ? "_power" : "";
        setBackgroundTexture(characterPath("cardback/bg"+ cardType +"_env.png"), characterPath("cardback/bg"+ cardType + "_env_p.png"));
        tags.add(CustomTags.ENVIRONMENT);
    }

    @Override
    public void onMoveToDiscard() {
        isAboutToMove = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onUse(abstractPlayer, abstractMonster);

        this.addToBot(new MadelinePendAndFlushAction());

        GAM_fieldPatch.totalEnvrPlayedThisTurn++;
        GAM_fieldPatch.totalEnvrPlayedThisCombat++;

        movedFromCardTag = null;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (!isAboutToMove) {
            this.cantUseMessage = NOT_MOVED_MESSAGE;
            return false;
        } else {
            return true;
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BaseEnvironmentCard.class.getSimpleName()));
        NOT_MOVED_MESSAGE = uiStrings.TEXT[0];
    }
}
