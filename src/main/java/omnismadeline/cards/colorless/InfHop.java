package omnismadeline.cards.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.cards.BaseJumpCard;
import omnismadeline.util.CardStats;

public class InfHop extends BaseJumpCard {
    public static final String ID = makeID(InfHop.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.SPECIAL, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            0
    );

    public InfHop() {
        super(ID, info);
        setInnate(true);
        setSelfRetain(true);
        this.returnToHand = true;
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineMoveAction(m, GAP));
    }

    @Override
    public AbstractCard makeCopy() {
        return new InfHop();
    }
}
