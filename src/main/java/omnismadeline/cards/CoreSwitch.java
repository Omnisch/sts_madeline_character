package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class CoreSwitch extends BaseCard {
    public static final String ID = makeID(CoreSwitch.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 3;
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 2;

    public CoreSwitch() {
        super(ID, info);
        this.setDamage(DAMAGE);
        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        int magmaAndIce = this.magicNumber;
        for (AbstractCard c : p.exhaustPile.group) {
            if (c.tags.contains(CustomTags.MAGMA) || c.tags.contains(CustomTags.ICE)) {
                ++magmaAndIce;
            }
        }

        for (int i = 0; i < magmaAndIce; ++i) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

        this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();

        int magmaAndIce = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.tags.contains(CustomTags.MAGMA) || c.tags.contains(CustomTags.ICE)) {
                ++magmaAndIce;
            }
        }

        this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + magmaAndIce + cardStrings.EXTENDED_DESCRIPTION[1];

        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new CoreSwitch();
    }
}
