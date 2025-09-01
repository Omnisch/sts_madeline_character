package omnismadeline.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineExchangeMomentumWithEnergyAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class GoldenStrawberry extends BaseCard {
    public static final String ID = makeID(GoldenStrawberry.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    public GoldenStrawberry() {
        super(ID, info);
        this.setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineExchangeMomentumWithEnergyAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new GoldenStrawberry();
    }
}
