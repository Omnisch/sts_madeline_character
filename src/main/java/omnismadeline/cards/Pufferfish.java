package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.powers.MomentumPower;
import omnismadeline.util.CardStats;

public class Pufferfish extends BaseCard {
    public static final String ID = makeID(Pufferfish.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            4
    );

    private static final int DAMAGE = 22;
    private static final int UPG_DAMAGE = 4;

    public Pufferfish() {
        super(ID, info);
        this.setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(MomentumPower.POWER_ID)) {
            final int momentumAmount = AbstractDungeon.player.getPower(MomentumPower.POWER_ID).amount;
            this.setCostForTurn(this.cost - momentumAmount);
        }
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        this.addToBot(new MadelineRefillAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pufferfish();
    }
}
