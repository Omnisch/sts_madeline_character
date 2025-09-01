package omnismadeline.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineUpgradeAndMakeCopiesAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Cassette extends BaseCard {
    public static final String ID = makeID(Cassette.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    //private static final int MAGIC = 3;

    public Cassette() {
        super(ID, info);
        //this.setMagic(MAGIC);
        this.setExhaust(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.addToBot(new MadelineUpgradeAndMakeCopiesAction(99, false, true, true));
        } else {
            this.addToBot(new MadelineUpgradeAndMakeCopiesAction(99));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cassette();
    }
}
