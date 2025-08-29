package omnismadeline.cards.colorless;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.cards.BaseJumpCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public abstract class InfHop extends BaseJumpCard {
    public static final String ID = makeID(InfHop.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;

    public InfHop() {
        super(ID, info);
        setInnate(true);
        setSelfRetain(true);
        setBlock(BLOCK, UPG_BLOCK);
        this.returnToHand = true;
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public void onMoveToDiscard() {
        this.addToBot(new DiscardToHandAction(this));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

//    @Override
//    public AbstractCard makeCopy() {
//        return new InfHop();
//    }
}
