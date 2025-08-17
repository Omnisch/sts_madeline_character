package omnismadeline.cards.colorless;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseCard;
import omnismadeline.util.CardStats;

public class Strawberry extends BaseCard {
    public static final String ID = makeID(Strawberry.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    private static final int UPG_COST = 0;
    private static final int MAGIC = 2;

    public Strawberry() {
        super(ID, info);
        setCostUpgrade(UPG_COST);
        setMagic(MAGIC);
        setExhaust(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strawberry();
    }
}
