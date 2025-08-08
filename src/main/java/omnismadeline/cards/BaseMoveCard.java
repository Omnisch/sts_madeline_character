package omnismadeline.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.util.CardStats;

public abstract class BaseMoveCard extends BaseCard {

    public BaseMoveCard(String ID, CardStats info) {
        super(ID, info);
    }

    protected boolean canUseMove(AbstractPlayer p, AbstractMonster m, int gap) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (p.hand.group.size() > gap) {
            return true;
        } else {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }
}
