package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelinePendAndFlushAction;
import omnismadeline.enums.CustomTags;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

import static omnismadeline.MadelineMod.characterPath;

public abstract class BaseJumpCard extends BaseCard {
    private static final UIStrings uiStrings;
    private static final String CANT_JUMP_MESSAGE;
    protected static final int GAP = 1;

    public BaseJumpCard(String ID, CardStats info) {
        super(ID, info);
        final String cardType =
                info.cardType == CardType.ATTACK ? "_attack" :
                        info.cardType == CardType.SKILL ? "_skill" :
                                info.cardType == CardType.POWER ? "_power" : "";
        setBackgroundTexture(characterPath("cardback/bg"+ cardType +"_jump.png"), characterPath("cardback/bg"+ cardType + "_jump_p.png"));
        this.tags.add(CustomTags.JUMP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new SoarStance()));
        onUse(p, m);
        this.addToBot(new MadelineGainMomentumAction(1));
        this.addToBot(new MadelinePendAndFlushAction());
        GAM_fieldPatch.totalJumpPlayedThisTurn++;
        GAM_fieldPatch.totalJumpPlayedThisCombat++;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (Objects.equals(p.stance.ID, SoarStance.STANCE_ID)) {
            this.cantUseMessage = CANT_JUMP_MESSAGE;
            return false;
        } else {
            return true;
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BaseJumpCard.class.getSimpleName()));
        CANT_JUMP_MESSAGE = uiStrings.TEXT[0];
    }
}
