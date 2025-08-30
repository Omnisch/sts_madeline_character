package omnismadeline.cards.colorless;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.cards.MagmaCube;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.MomentumPower;
import omnismadeline.util.CardStats;

import static omnismadeline.MadelineMod.characterPath;

public class IceCube extends BaseEnvironmentCard {
    public static final String ID = makeID(IceCube.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            -2
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;

    public IceCube() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC);
        this.setExhaust(true);
        this.tags.add(CustomTags.ICE);
        this.cardsToPreview = new MagmaCube(false);

        setBackgroundTexture(characterPath("cardback/bg_skill_env_colorless.png"), characterPath("cardback/bg_skill_env_colorless_p.png"));
    }
    public IceCube(boolean isPreview) {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC);
        this.setExhaust(true);
        this.tags.add(CustomTags.ICE);
        if (isPreview) {
            this.cardsToPreview = new MagmaCube(false);
        }

        setBackgroundTexture(characterPath("cardback/bg_skill_env_colorless.png"), characterPath("cardback/bg_skill_env_colorless_p.png"));
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        if (p.hasPower(MomentumPower.POWER_ID)) {
            this.addToBot(new ReducePowerAction(p, p, MomentumPower.POWER_ID, this.magicNumber));
        }
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
        return new IceCube();
    }
}
