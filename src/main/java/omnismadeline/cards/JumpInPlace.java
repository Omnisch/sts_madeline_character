package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class JumpInPlace extends BaseJumpCard {
    public static final String ID = makeID(JumpInPlace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            0
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    private static final int WEAK = 1;

    public JumpInPlace() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, WEAK, false), WEAK));
    }

    @Override
    public AbstractCard makeCopy() {
        return new JumpInPlace();
    }
}
