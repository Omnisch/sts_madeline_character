package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.actions.MadelinePendedAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.DashChancePower;
import omnismadeline.util.CardStats;

import static omnismadeline.util.MadelineUtils.*;

public class DashForward extends BaseDashCard {
    public static final String ID = makeID(DashForward.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.BASIC, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public DashForward() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);

        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        this.addToBot(new MadelineMoveAction(m, GAP));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DashForward();
    }
}
