package omnismadeline.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

public class TutorialBoard extends BaseCard {
    public static final String ID = makeID(TutorialBoard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.RARE, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            2
    );

    public TutorialBoard() {
        super(ID, info);
        this.setExhaust(true);
        this.setCostUpgrade(1);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        switch (m.intent) {
            case ATTACK:
            case ATTACK_BUFF:
            case ATTACK_DEBUFF:
            case ATTACK_DEFEND:
                final int monsterIntentDmg = m.getIntentDmg();
                if (monsterIntentDmg < 0) return;
                final boolean monsterIsMultiDmg = ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg");
                if (monsterIsMultiDmg) {
                    final int monsterMultiAmt = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt");
                    for (int i = 0; i < monsterMultiAmt; i++) {
                        this.addToBot(new DamageAction(m, new DamageInfo(p, monsterIntentDmg, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    }
                } else {
                    this.addToBot(new DamageAction(m, new DamageInfo(p, monsterIntentDmg, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TutorialBoard();
    }
}
