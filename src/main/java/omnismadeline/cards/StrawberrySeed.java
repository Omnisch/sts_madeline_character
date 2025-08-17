package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.colorless.Strawberry;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class StrawberrySeed extends BaseEnvironmentCard {
    public static final String ID = makeID(StrawberrySeed.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            -2
    );

    private static final int MAGIC = 1;

    public StrawberrySeed() {
        super(ID, info);
        setMagic(MAGIC);
        this.cardsToPreview = new Strawberry();
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
        this.addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardsToPreview.upgrade();
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrawberrySeed();
    }
}
