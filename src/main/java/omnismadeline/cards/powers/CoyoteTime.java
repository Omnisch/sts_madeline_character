package omnismadeline.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.CoyoteTimePower;
import omnismadeline.util.CardStats;

public class CoyoteTime extends BaseCard {
    public static final String ID = makeID(CoyoteTime.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    public CoyoteTime() {
        super(ID, info);
        setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(CoyoteTimePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new CoyoteTimePower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CoyoteTime();
    }
}
