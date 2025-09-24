package omnismadeline.cards.dashes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import omnismadeline.cards.BaseCard;
import omnismadeline.cards.jumps.Jump;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.DashChancePower;
import omnismadeline.powers.GrannysCabinImplicitPower;
import omnismadeline.util.CardStats;

import static omnismadeline.MadelineMod.characterPath;

public abstract class BaseDashCard extends BaseCard {
    private static final UIStrings uiStrings;
    private static final String CANT_DASH_MESSAGE;

    public BaseDashCard(String ID, CardStats info) {
        super(ID, info);
        final String cardType =
                info.cardType == CardType.ATTACK ? "_attack" :
                        info.cardType == CardType.SKILL ? "_skill" :
                                info.cardType == CardType.POWER ? "_power" : "";
        setBackgroundTexture(characterPath("cardback/bg"+ cardType +"_dash.png"), characterPath("cardback/bg"+ cardType + "_dash_p.png"));

        this.cardsToPreview = new Dash();

        this.tags.add(CustomTags.MOVE);
        this.tags.add(CustomTags.DASH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onUse(p, m);

        AbstractCard instance = this.cardsToPreview.makeCopy();
        instance.current_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        instance.current_y = (float)Settings.HEIGHT / 2.0F;
        ((Dash) instance).m = m;

        instance.onChoseThisOption();
        AbstractDungeon.effectList.add(new ExhaustCardEffect(instance));

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

    private static boolean hasDashChances(AbstractPlayer p) {
        return p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BaseDashCard.class.getSimpleName()));
        CANT_DASH_MESSAGE = uiStrings.TEXT[0];
    }
}
