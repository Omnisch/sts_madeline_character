package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.actions.MadelineRefillAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.RefillAtNextTurnImplicitPower;
import omnismadeline.util.CardStats;

public class TransitionDash extends BaseDashCard {
    public static final String ID = makeID(TransitionDash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.COMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public TransitionDash() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new MadelineMoveAction(m, GAP));
        this.addToBot(new PressEndTurnButtonAction());
        this.addToBot(new ApplyPowerAction(p, p, new RefillAtNextTurnImplicitPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TransitionDash();
    }
}
