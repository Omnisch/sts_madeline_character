package omnismadeline.cards.colorless;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.cards.MagmaBall;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class IceBall extends BaseEnvironmentCard {
    public static final String ID = makeID(IceBall.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            -2
    );

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    public IceBall() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setExhaust(true);
        this.tags.add(CustomTags.ICE);
        this.cardsToPreview = new MagmaBall(false);
    }
    public IceBall(boolean isPreview) {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setExhaust(true);
        this.tags.add(CustomTags.ICE);
        if (isPreview) {
            this.cardsToPreview = new MagmaBall(false);
        }
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(), 1, true, true));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (this.cardsToPreview != null) {
            this.cardsToPreview.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IceBall();
    }
}
