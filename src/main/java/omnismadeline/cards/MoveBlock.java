package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineStargazingAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

public class MoveBlock extends BaseCard {
    public static final String ID = makeID(MoveBlock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            0
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public MoveBlock() {
        super(ID, info);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new LandStance()));
        this.addToBot(new MadelineStargazingAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MoveBlock();
    }
}
