package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.DashChancePower;
import omnismadeline.util.CardStats;

public class DashForward extends BaseCard {
    public static final String ID = makeID(DashForward.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR, // The card color.
            CardType.SKILL,     // The type. ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC,   // Rarity. BASIC is for starting cards, then there's COMMON / UNCOMMON / RARE,
            // and then SPECIAL is for cards you only get from events.
            // Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY,    // The target. Single target is ENEMY, all enemies is ALL_ENEMY.
            // Look at cards similar to what you want to see what to use.
            1                   // The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public DashForward() {
        super(ID, info); // Pass the required information to the BaseCard constructor.
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, -1), -1));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false), 1));
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (this.canDash()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (!this.canDash()) {
            this.cantUseMessage = cardStrings.UPGRADE_DESCRIPTION;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DashForward();
    }

    private boolean canDash() {
        AbstractPlayer p = AbstractDungeon.player;
        return p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0;
    }
}
