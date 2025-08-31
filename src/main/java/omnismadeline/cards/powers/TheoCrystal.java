package omnismadeline.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.TheoCrystalPlusPower;
import omnismadeline.powers.TheoCrystalPower;
import omnismadeline.util.CardStats;

public class TheoCrystal extends BaseCard {
    public static final String ID = makeID(TheoCrystal.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.POWER, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            3
    );

    public TheoCrystal() {
        super(ID, info);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        final boolean stillCanGetAward = p.damagedThisCombat <= (this.upgraded ? 1 : 0);
        if (stillCanGetAward) {
            if (this.upgraded) {
                this.addToBot(new ApplyPowerAction(p, p, new TheoCrystalPlusPower(p, 1), 1));
            } else {
                this.addToBot(new ApplyPowerAction(p, p, new TheoCrystalPower(p, 1), 1));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheoCrystal();
    }
}
