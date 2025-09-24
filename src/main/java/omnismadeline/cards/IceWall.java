package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

import static omnismadeline.MadelineMod.characterPath;

public class IceWall extends BaseEnvironmentCard {
    public static final String ID = makeID(IceWall.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.SPECIAL, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            -2
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;

    public IceWall() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC);
        this.setExhaust(true);
        this.tags.add(CustomTags.ICE);
        this.cardsToPreview = new MagmaWall(false);

        setBackgroundTexture(characterPath("cardback/bg_skill_env_colorless.png"), characterPath("cardback/bg_skill_env_colorless_p.png"));
    }
    public IceWall(boolean isPreview) {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
        this.setMagic(MAGIC);
        this.setExhaust(true);
        this.tags.add(CustomTags.ICE);
        if (isPreview) {
            this.cardsToPreview = new MagmaWall(false);
        }

        setBackgroundTexture(characterPath("cardback/bg_skill_env_colorless.png"), characterPath("cardback/bg_skill_env_colorless_p.png"));
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
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
        return new IceWall();
    }
}
