package omnismadeline.cards.colorless;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.cards.BaseDashCard;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public abstract class InfDash extends BaseDashCard {
    public static final String ID = makeID(InfDash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 2;

    public InfDash() {
        super(ID, info);
        setInnate(true);
        setSelfRetain(true);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.returnToHand = true;
        this.tags.add(CustomTags.MOVE);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new MadelineMoveAction(m, GAP));
    }

    @Override
    public void onMoveToDiscard() {
        this.addToBot(new DiscardToHandAction(this));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

//    @Override
//    public AbstractCard makeCopy() {
//        return new InfDash();
//    }
}
