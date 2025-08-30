package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class Clouds extends BaseEnvironmentCard {
    public static final String ID = makeID(Clouds.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            -2
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public Clouds() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        if (this.movedFromCardTag == CustomTags.DASH) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else if (this.movedFromCardTag == CustomTags.JUMP) {
            this.addToBot(new GainBlockAction(p, this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Clouds();
    }
}
