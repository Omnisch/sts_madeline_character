package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

public class Snowball extends BaseEnvironmentCard {
    public static final String ID = makeID(Snowball.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            -2
    );

    private static final int DAMAGE = 8;
    private static final int MAGIC = 1;

    public Snowball() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC);
        this.cardsToPreview = new Wound();
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new DrawCardAction(magicNumber));
        if (Objects.equals(p.stance.ID, LandStance.STANCE_ID)) {
            this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy()));
        }
        if (this.upgraded) {
            this.addToBot(new MadelineRefillAction());
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Snowball();
    }
}
