package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.FastfallImplicitPower;
import omnismadeline.util.CardStats;

public class Fastfall extends BaseCard {
    public static final String ID = makeID(Fastfall.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            0
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Fastfall() {
        super(ID, info);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineGainMomentumAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new FastfallImplicitPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fastfall();
    }
}
