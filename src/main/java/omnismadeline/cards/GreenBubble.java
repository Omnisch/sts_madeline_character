package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.GreenBubbleImplicitPower;
import omnismadeline.util.CardStats;

public class GreenBubble extends BaseEnvironmentCard {
    public static final String ID = makeID(GreenBubble.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            -2
    );

    private static final int BLOCK = 4;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public GreenBubble() {
        super(ID, info);
        this.setBlock(BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new GreenBubbleImplicitPower(p, this.magicNumber, 1), this.magicNumber));
        this.addToBot(new MadelineRefillAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new GreenBubble();
    }
}
