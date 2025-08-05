package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.utility.ReApplyPowersAction;
import com.megacrit.cardcrawl.actions.watcher.FlickerReturnToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.DashChancePower;
import omnismadeline.util.CardStats;

public class JumpInPlace extends BaseCard {
    public static final String ID = makeID(JumpInPlace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR, // The card color.
            CardType.SKILL,     // The type. ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC,   // Rarity. BASIC is for starting cards, then there's COMMON / UNCOMMON / RARE,
            // and then SPECIAL is for cards you only get from events.
            // Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF,    // The target. Single target is ENEMY, all enemies is ALL_ENEMY.
            // Look at cards similar to what you want to see what to use.
            0                   // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    // These will be used in the constructor. Technically you can just use the values directly,
    // but constants at the top of the file are easy to adjust.
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public JumpInPlace() {
        super(ID, info); // Pass the required information to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC); // Sets the card's damage and how much it changes when upgraded.
        this.returnToHand = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p, p, 1, false));

        if (!p.hasPower(DashChancePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, 1), 1));
        } else {
            int debt = p.getPower(DashChancePower.POWER_ID).amount;
            if (debt <= 0) {
                this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, -debt + 1), -debt + 1));
            }
        }

        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new JumpInPlace();
    }
}
