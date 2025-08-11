package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.StayStance;
import omnismadeline.util.CardStats;

public class Spring extends BaseEnvironmentCard {
    public static final String ID = makeID(Spring.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            -2
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;

    public Spring() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new StayStance()));
        this.addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Spring();
    }
}
