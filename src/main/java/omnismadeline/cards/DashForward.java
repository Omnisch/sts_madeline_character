package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.DashChancePower;
import omnismadeline.util.CardStats;

import static omnismadeline.util.MadelineUtils.canDash;

public class DashForward extends BaseDashCard {
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

    // These will be used in the constructor. Technically you can just use the values directly,
    // but constants at the top of the file are easy to adjust.
    private static final int GAP = 1;
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public DashForward() {
        super(ID, info); // Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);

        this.addToBot(new MadelineMoveAction(m, p, GAP));
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), 1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.canUseDash(p, m, GAP);
    }

    @Override
    public AbstractCard makeCopy() {
        return new DashForward();
    }
}
