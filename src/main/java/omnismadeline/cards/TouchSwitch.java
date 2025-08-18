package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.util.CardStats;

public class TouchSwitch extends BaseCard {
    public static final String ID = makeID(TouchSwitch.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            3
    );

    private static final int BLOCK = 4;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public TouchSwitch() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null) {
            this.setCostForTurn(this.costForTurn - GAM_fieldPatch.totalMovePlayedThisTurn);
        }
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard c) {
        if (c.tags.contains(CustomTags.MOVE)) {
            this.setCostForTurn(this.costForTurn - 1);
        }
    }

    @Override
    public void atTurnStart() {
        this.resetAttributes();
        this.applyPowers();
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TouchSwitch();
    }
}
