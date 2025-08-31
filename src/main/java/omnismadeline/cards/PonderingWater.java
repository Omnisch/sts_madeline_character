package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.PonderingWaterPower;
import omnismadeline.util.CardStats;

public class PonderingWater extends BaseCard {
    public static final String ID = makeID(PonderingWater.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    public PonderingWater() {
        super(ID, info);
        this.setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(PonderingWaterPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new PonderingWaterPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PonderingWater();
    }
}
