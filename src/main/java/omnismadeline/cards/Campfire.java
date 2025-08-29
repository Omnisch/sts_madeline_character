package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

public class Campfire extends BaseCard {
    public static final String ID = makeID(Campfire.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.SELF,
            1
    );

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    public Campfire() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new LandStance()));
        this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Campfire();
    }
}
