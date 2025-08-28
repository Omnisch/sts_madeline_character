package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.stances.LandStance;

import java.util.Objects;

import static omnismadeline.MadelineMod.makeID;

public class OshiroBossImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("OshiroBoss");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public OshiroBossImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer p = AbstractDungeon.player;
            if (Objects.equals(p.stance.ID, LandStance.STANCE_ID)) {
                this.addToBot(new DamageAction(p, new DamageInfo(p, this.amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
            }
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
