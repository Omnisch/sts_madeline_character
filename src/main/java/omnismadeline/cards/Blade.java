package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

public class Blade extends BaseCard {
    public static final String ID = makeID(Blade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 2;

    public Blade() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Objects.equals(p.stance.ID, SoarStance.STANCE_ID)) {
            this.addToBot(new DamageAction(p, new DamageInfo(p, 2, DamageInfo.DamageType.HP_LOSS)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Blade();
    }
}
