package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class RedBubble extends BaseCard {
    public static final String ID = makeID(RedBubble.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int MAGIC = 2;

    public RedBubble() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC);
        setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new DiscardAction(p, p, 1, false));
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new MadelineRefillAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new RedBubble();
    }
}
