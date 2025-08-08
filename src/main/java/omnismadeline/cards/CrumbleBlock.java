package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

public class CrumbleBlock extends BaseEnvironmentCard {
    public static final String ID = makeID(CrumbleBlock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR, // The card color.
            CardType.SKILL,     // The type. ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON,  // Rarity. BASIC is for starting cards, then there's COMMON / UNCOMMON / RARE,
            // and then SPECIAL is for cards you only get from events.
            // Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF,    // The target. Single target is ENEMY, all enemies is ALL_ENEMY.
            // Look at cards similar to what you want to see what to use.
            -2                  // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public CrumbleBlock() {
        super(ID, info); // Pass the required information to the BaseCard constructor.
        setSelfRetain(false, true);

        this.shuffleBackIntoDrawPile = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);

        this.addToBot(new ChangeStanceAction(new LandStance()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CrumbleBlock();
    }
}
