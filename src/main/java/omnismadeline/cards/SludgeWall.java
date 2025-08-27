package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.MomentumPower;
import omnismadeline.util.CardStats;

public class SludgeWall extends BaseEnvironmentCard {
    public static final String ID = makeID(SludgeWall.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 1;

    public SludgeWall() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (p.hasPower(MomentumPower.POWER_ID)) {
            final int momentumAmount = p.getPower(MomentumPower.POWER_ID).amount;
            for (int i = 0; i < momentumAmount; ++i) {
                this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SludgeWall();
    }
}
