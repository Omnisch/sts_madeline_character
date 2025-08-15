package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.powers.DashChancePower;

public class MadelineRefillAction extends AbstractGameAction {
    public MadelineRefillAction() {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (!p.hasPower(DashChancePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, DashChancePower.maxAmount), DashChancePower.maxAmount));
        } else {
            int currDashChanceAmount = p.getPower(DashChancePower.POWER_ID).amount;
            if (currDashChanceAmount < DashChancePower.maxAmount) {
                this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, DashChancePower.maxAmount - currDashChanceAmount)));
            }
        }
        this.isDone = true;
    }
}
