package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.powers.MomentumPower;

public class MadelineLoseMomentumAction extends AbstractGameAction {
    private final AbstractPlayer p = AbstractDungeon.player;

    public MadelineLoseMomentumAction() {
        this.amount = 0;
        if (p.hasPower(MomentumPower.POWER_ID)) {
            this.amount = p.getPower(MomentumPower.POWER_ID).amount;
        }
    }
    public MadelineLoseMomentumAction(int amount) {
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (!p.hasPower(MomentumPower.POWER_ID)) {
            this.isDone = true;
            return;
        }

        if (this.amount >= p.getPower(MomentumPower.POWER_ID).amount) {
            this.addToBot(new RemoveSpecificPowerAction(p, p, MomentumPower.POWER_ID));
        } else {
            this.addToBot(new ReducePowerAction(p, p, MomentumPower.POWER_ID, this.amount));
        }

        this.isDone = true;
    }
}
