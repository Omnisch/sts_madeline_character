package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.HeartOfTheMountainPower;
import omnismadeline.util.CardStats;

public class HeartOfTheMountain extends BaseCard {
    public static final String ID = makeID(HeartOfTheMountain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            3
    );

    public HeartOfTheMountain() {
        super(ID, info);
        setCostUpgrade(2);
        setEthereal(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(HeartOfTheMountainPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new HeartOfTheMountainPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeartOfTheMountain();
    }
}
