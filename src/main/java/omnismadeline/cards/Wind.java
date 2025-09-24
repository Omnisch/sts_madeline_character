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
            -2
    );

    private static final int VULNERABLE = 3;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public Wind() {
        super(ID, info);
        this.setMagic(MAGIC, UPG_MAGIC);
        this.setExhaust(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster em : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(em, p, new VulnerablePower(em, VULNERABLE, false), VULNERABLE));
        }
        this.addToBot(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wind();
    }
}
