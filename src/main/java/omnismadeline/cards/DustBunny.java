package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class DustBunny extends BaseEnvironmentCard {
    public static final String ID = makeID(DustBunny.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;

    public DustBunny() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        for (AbstractMonster em : AbstractDungeon.getMonsters().monsters) {
            if (em.getIntentBaseDmg() >= 0) {
                this.addToBot(new DamageAction(em, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DustBunny();
    }
}
