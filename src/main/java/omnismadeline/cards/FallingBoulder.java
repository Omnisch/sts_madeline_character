package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;
import omnismadeline.stances.StayStance;
import omnismadeline.util.CardStats;

public class FallingBoulder extends BaseCard {
    public static final String ID = makeID(FallingBoulder.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 1;

    public FallingBoulder() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new LandStance()));
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -magicNumber), -magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FallingBoulder();
    }
}
