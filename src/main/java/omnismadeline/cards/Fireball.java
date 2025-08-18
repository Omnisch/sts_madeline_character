package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class Fireball extends BaseEnvironmentCard {
    public static final String ID = makeID(Fireball.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            -2
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;

    public Fireball() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);
        this.tags.add(CustomTags.FIRE);
        this.cardsToPreview = new IceBall(false);
    }
    public Fireball(boolean isPreview) {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);
        this.tags.add(CustomTags.FIRE);
        if (isPreview) {
            this.cardsToPreview = new IceBall(false);
        }
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (this.cardsToPreview != null) {
            this.cardsToPreview.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fireball();
    }
}
