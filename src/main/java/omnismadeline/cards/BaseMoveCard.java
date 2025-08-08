package omnismadeline.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.util.CardStats;

public abstract class BaseMoveCard extends BaseCard {

    public BaseMoveCard(String ID, CardStats info) {
        super(ID, info);
        setCustomVar("gap", 1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (p.hand.group.size() > customVar("gap")) {
            return true;
        } else {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }
}
