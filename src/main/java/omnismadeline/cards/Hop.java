package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Hop extends BaseJumpCard {
    public static final String ID = makeID(Hop.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int GAP = 1;
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Hop() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);

        this.tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new MadelineMoveAction(m, GAP));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Hop();
    }
}
