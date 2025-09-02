package omnismadeline.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.PinkSunrisePower;
import omnismadeline.util.CardStats;

public class PinkSunrise extends BaseCard {
    public static final String ID = makeID(PinkSunrise.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    private static final int MAGIC = 6;

    public PinkSunrise() {
        super(ID, info);
        this.setMagic(MAGIC);
        this.setInnate(false, true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PinkSunrisePower(p, 1, this.magicNumber), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PinkSunrise();
    }
}
