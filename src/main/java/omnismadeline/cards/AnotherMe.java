package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.AnotherMeImplicitPower;
import omnismadeline.powers.AnotherMePlusImplicitPower;
import omnismadeline.util.CardStats;

public class AnotherMe extends BaseCard {
    public static final String ID = makeID(AnotherMe.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            2
    );

    public AnotherMe() {
        super(ID, info);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.addToBot(new ApplyPowerAction(p, p, new AnotherMePlusImplicitPower(p, 1), 1));
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new AnotherMeImplicitPower(p, 1), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AnotherMe();
    }
}
