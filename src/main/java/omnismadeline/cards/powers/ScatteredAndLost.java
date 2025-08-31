package omnismadeline.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.ScatteredAndLostPower;
import omnismadeline.util.CardStats;

public class ScatteredAndLost extends BaseCard {
    public static final String ID = makeID(ScatteredAndLost.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    private static final int MAGIC = 1;

    public ScatteredAndLost() {
        super(ID, info);
        this.setMagic(MAGIC);
        this.setEthereal(true, false);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ScatteredAndLostPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScatteredAndLost();
    }
}
