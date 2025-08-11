package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.actions.MadelinePendedAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import static omnismadeline.util.MadelineUtils.*;

public class Hop extends BaseCard {
    public static final String ID = makeID(Hop.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR, // The card color.
            CardType.SKILL,     // The type. ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC,   // Rarity. BASIC is for starting cards, then there's COMMON / UNCOMMON / RARE,
            // and then SPECIAL is for cards you only get from events.
            // Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY,   // The target. Single target is ENEMY, all enemies is ALL_ENEMY.
            // Look at cards similar to what you want to see what to use.
            1                   // The card's base cost. -1 is X cost, -2 is no-cost for unplayable cards like curses, or Reflex.
    );

    // These will be used in the constructor. Technically you can just use the values directly,
    // but constants at the top of the file are easy to adjust.
    private static final int GAP = 1;
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Hop() {
        super(ID, info); // Pass the required information to the BaseCard constructor.
        this.setBlock(BLOCK, UPG_BLOCK); // Sets the card's damage and how much it changes when upgraded.

        this.tags.add(CardTags.STARTER_DEFEND);
        this.tags.add(CustomTags.JUMP);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new SoarStance()));

        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new MadelineMoveAction(m, GAP));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (!canJump(p)) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Hop();
    }
}
