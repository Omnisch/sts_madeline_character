package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Wind extends BaseEnvironmentCard {
    public static final String ID = makeID(Wind.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            0
    );

    private static final int VULNERABLE = 3;
    private static final int WEAK = 2;

    public Wind() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster em : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(em, p, new VulnerablePower(em, VULNERABLE, false), VULNERABLE));
        }
        if (!this.upgraded) {
            this.addToBot(new ApplyPowerAction(p, p, new WeakPower(p, WEAK, false), WEAK));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wind();
    }
}
