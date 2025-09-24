package omnismadeline.cards.jumps;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class Cornerkick extends BaseJumpCard {
    public static final String ID = makeID(Cornerkick.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public Cornerkick() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));

        if (!this.autoPlayed) {
            this.addToBot(new MadelineMoveAction(m, GAP, CustomTags.JUMP, false, true, true));
        }

        this.addToBot(new MadelineGainMomentumAction(1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cornerkick();
    }
}
