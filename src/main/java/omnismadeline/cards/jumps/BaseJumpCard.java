package omnismadeline.cards.jumps;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.actions.MadelinePendAndFlushAction;
import omnismadeline.cards.BaseCard;
import omnismadeline.enums.CustomTags;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.powers.GrannysCabinImplicitPower;
import omnismadeline.powers.PonderingWaterImplicitPower;
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
        this.tags.add(CustomTags.MOVE);
        this.tags.add(CustomTags.JUMP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(PonderingWaterImplicitPower.POWER_ID)) {
            this.addToBot(new ChangeStanceAction(new SoarStance()));
        }
        onUse(p, m);

        if (!this.autoPlayed) {
            this.addToBot(new MadelineMoveAction(m, GAP, CustomTags.JUMP, false, true, true));
        }

        this.addToBot(new MadelineGainMomentumAction(1));

        if (!this.autoPlayed) {
            this.addToBot(new MadelinePendAndFlushAction());
        }

        GAM_fieldPatch.totalJumpPlayedThisTurn++;
        GAM_fieldPatch.totalJumpPlayedThisCombat++;

        this.autoPlayed = false;
        this.movedFromCardTag = null;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (this.autoPlayed) {
            return true;
        } else if (Objects.equals(p.stance.ID, SoarStance.STANCE_ID)) {
            this.cantUseMessage = CANT_JUMP_MESSAGE;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(GrannysCabinImplicitPower.POWER_ID)) {
            doubleDamageAndBlock();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if (AbstractDungeon.player.hasPower(GrannysCabinImplicitPower.POWER_ID)) {
            doubleDamageAndBlock();
        }
    }

    private void doubleDamageAndBlock() {
        if (this.baseDamage > 0) {
            this.damage *= 2;
            if (this.isMultiDamage && this.multiDamage != null) {
                for (int i = 0; i < this.multiDamage.length; ++i) {
                    this.multiDamage[i] *= 2;
                }
            }
            this.isDamageModified = (this.damage != this.baseDamage);
        }
        if (this.baseBlock > 0) {
            this.block *= 2;
            this.isBlockModified = (this.block != this.baseBlock);
        }
        this.initializeDescription();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BaseJumpCard.class.getSimpleName()));
        CANT_JUMP_MESSAGE = uiStrings.TEXT[0];
    }
}
