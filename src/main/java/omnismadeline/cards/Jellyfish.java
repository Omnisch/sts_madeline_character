package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.JellyfishImplicitPower;
import omnismadeline.util.CardStats;

public class Jellyfish extends BaseCard {
    public static final String ID = makeID(Jellyfish.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    public Jellyfish() {
        super(ID, info);
        this.setExhaust(true);
        this.setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new JellyfishImplicitPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Jellyfish();
    }
}
