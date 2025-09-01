package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGeneratorFollowUpAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Generator extends BaseCard {
    public static final String ID = makeID(Generator.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int DAMAGE = 10;
    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 3;

    public Generator() {
        super(ID, info);
        this.setDamage(DAMAGE);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        int toDraw = this.magicNumber - p.hand.size();
        if (toDraw > 0) {
            this.addToTop(new DrawCardAction(toDraw, new MadelineGeneratorFollowUpAction()));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Generator();
    }
}
