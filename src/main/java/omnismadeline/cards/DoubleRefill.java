package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.DoubleRefillPower;
import omnismadeline.util.CardStats;

public class DoubleRefill extends BaseCard {
    public static final String ID = makeID(DoubleRefill.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    public DoubleRefill() {
        super(ID, info);
        setInnate(false, true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(DoubleRefillPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new DoubleRefillPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleRefill();
    }
}
