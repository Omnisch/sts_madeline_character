package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class Bumper extends BaseEnvironmentCard {
    public static final String ID = makeID(Bumper.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            0
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public Bumper() {
        super(ID, info);
        this.setDamage(DAMAGE, UPG_DAMAGE);
        this.setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new MadelineRefillAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bumper();
    }
}
