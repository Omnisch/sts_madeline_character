package omnismadeline.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineWinCombatAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Farewell extends BaseCard {
    public static final String ID = makeID(Farewell.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            4
    );

    public Farewell() {
        super(ID, info);
        setCostUpgrade(3);
        setExhaust(true);
        setEthereal(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineWinCombatAction());

        AbstractCard thisInMasterDeck = null;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid == this.uuid) {
                thisInMasterDeck = c;
            }
        }
        if (thisInMasterDeck != null) {
            p.masterDeck.removeCard(thisInMasterDeck);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Farewell();
    }
}
