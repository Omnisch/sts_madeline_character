package omnismadeline.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import omnismadeline.actions.MadelineLoseMomentumAction;

import static omnismadeline.MadelineMod.makeID;

public class PinkSunrisePower extends BasePower {
    public static final String POWER_ID = makeID("PinkSunrise");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractPlayer p;
    private final int triggerLayer;
    private int currLayer = 0;

    public PinkSunrisePower(AbstractCreature owner, int amount, int triggerLayer) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.p = AbstractDungeon.player;
        this.triggerLayer = triggerLayer;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (this.p.hasPower(MomentumPower.POWER_ID)) {
            this.currLayer += Math.min(this.amount, this.p.getPower(MomentumPower.POWER_ID).amount);
            this.addToBot(new MadelineLoseMomentumAction(this.amount));
        }
        this.addToBot(new GainEnergyAction(this.amount));
        this.flash();
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.currLayer == this.triggerLayer) {
            this.currLayer = 0;
            this.addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
            this.addToBot(new SkipEnemiesTurnAction());
            this.flash();
        }
    }

    public void updateDescription() {
        if (this.currLayer == triggerLayer) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[5];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3] + (this.triggerLayer - this.currLayer) + DESCRIPTIONS[4];
        }
    }
}
