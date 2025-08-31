package omnismadeline.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import omnismadeline.cards.BaseCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.ClimbPower;
import omnismadeline.powers.MomentumPower;
import omnismadeline.util.CardStats;

public class Climb extends BaseCard {
    public static final String ID = makeID(Climb.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            2
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 2;

    public Climb() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        final int currMomentumAmount = p.hasPower(MomentumPower.POWER_ID) ? p.getPower(MomentumPower.POWER_ID).amount : 0;
        final int amountToApply = currMomentumAmount + this.magicNumber;
        if (amountToApply > 0) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, amountToApply)));
        }
        if (!p.hasPower(ClimbPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new ClimbPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Climb();
    }
}
