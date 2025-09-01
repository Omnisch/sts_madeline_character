package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineBlueTorchAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class BlueTorch extends BaseCard {
    public static final String ID = makeID(BlueTorch.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    public BlueTorch() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new MadelineBlueTorchAction(1, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlueTorch();
    }
}
