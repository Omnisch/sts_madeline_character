package omnismadeline.cards.dashes;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelinePendAndFlushAction;
import omnismadeline.cards.jumps.Jump;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

public class Cornerboost extends BaseDashCard {
    public static final String ID = makeID(Cornerboost.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 1;

    public Cornerboost() {
        super(ID, info);
        this.setMagic(MAGIC);
        this.setExhaust(true, false);

        this.tags.add(CustomTags.JUMP);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) { }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineGainMomentumAction(this.magicNumber));

        AbstractCard instance = this.cardsToPreview.makeCopy();
        instance.current_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        instance.current_y = (float)Settings.HEIGHT / 2.0F;
        ((Dash) instance).m = m;

        instance.onChoseThisOption();
        AbstractDungeon.effectList.add(new ExhaustCardEffect(instance));

        MadelinePendAndFlushAction.actionsPended.addLast(new AbstractGameAction() {
            @Override
            public void update() {
                this.addToBot(new ChangeStanceAction(new LandStance()));

                this.addToBot(new MadelinePendAndFlushAction());
                this.isDone = true;
            }
        });
        MadelinePendAndFlushAction.actionsPended.addLast(new AbstractGameAction() {
            @Override
            public void update() {
                Jump instance = new Jump();
                instance.current_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                instance.current_y = (float)Settings.HEIGHT / 2.0F;
                instance.m = m;

                instance.onChoseThisOption();
                AbstractDungeon.effectList.add(new ExhaustCardEffect(instance));

                this.addToBot(new MadelinePendAndFlushAction());
                this.isDone = true;
            }
        });

        this.autoPlayed = false;
        this.movedFromCardTag = null;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cornerboost();
    }
}
