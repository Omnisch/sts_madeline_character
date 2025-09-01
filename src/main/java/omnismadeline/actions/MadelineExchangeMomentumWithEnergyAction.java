package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import omnismadeline.powers.MomentumPower;

public class MadelineExchangeMomentumWithEnergyAction extends AbstractGameAction {

    public MadelineExchangeMomentumWithEnergyAction() {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        final int currEnergy = EnergyPanel.totalCount;
        final int currMomentum = p.hasPower(MomentumPower.POWER_ID) ? p.getPower(MomentumPower.POWER_ID).amount : 0;

        if (currEnergy > currMomentum) {
            p.loseEnergy(currEnergy - currMomentum);
            this.addToBot(new MadelineGainMomentumAction(currEnergy - currMomentum));
        } else if (currEnergy < currMomentum) {
            p.gainEnergy(currMomentum - currEnergy);
            if (currEnergy == 0) {
                this.addToBot(new RemoveSpecificPowerAction(p, p, MomentumPower.POWER_ID));
            } else {
                this.addToBot(new ReducePowerAction(p, p, MomentumPower.POWER_ID, currMomentum - currEnergy));
            }
        }

        this.isDone = true;
    }
}
