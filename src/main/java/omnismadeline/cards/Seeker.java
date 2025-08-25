package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Seeker extends BaseCard {
    public static final String ID = makeID(Seeker.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            1
    );

    private static final int STRENGTH = 4;
    private static final int VULNERABLE = 2;

    public Seeker() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(0);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH), STRENGTH));
        this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, VULNERABLE, false), VULNERABLE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Seeker();
    }
}
