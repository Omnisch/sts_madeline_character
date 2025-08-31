package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.dashes.TransitionDash;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.ResurrectionsPower;
import omnismadeline.util.CardStats;

public class Resurrections extends BaseCard {
    public static final String ID = makeID(Resurrections.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            2
    );

    public Resurrections() {
        super(ID, info);
        setCostUpgrade(1);
        this.cardsToPreview = new TransitionDash();
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ResurrectionsPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Resurrections();
    }
}
