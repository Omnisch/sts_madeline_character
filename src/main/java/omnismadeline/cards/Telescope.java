package omnismadeline.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineSkipIntentAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Telescope extends BaseCard {
    public static final String ID = makeID(Telescope.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    public Telescope() {
        super(ID, info);
        this.setExhaust(true);
        this.setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineSkipIntentAction(m));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Telescope();
    }
}
