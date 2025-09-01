package omnismadeline.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.powers.DashChancePower;
import omnismadeline.powers.MomentumPower;

public class MadelineExchangeMomentumToBlockAction extends AbstractGameAction {
    private final float scale;

    public MadelineExchangeMomentumToBlockAction(float scale) {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.scale = scale;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        int momentumAmount = 0;
        if (p.hasPower(MomentumPower.POWER_ID)) {
            momentumAmount += p.getPower(MomentumPower.POWER_ID).amount;
        }

        if (momentumAmount == 0) {
            this.isDone = true;
            return;
        }

        this.addToBot(new RemoveSpecificPowerAction(p, p, MomentumPower.POWER_ID));
        this.addToBot(new GainBlockAction(p, MathUtils.floor(this.scale * momentumAmount)));

        this.isDone = true;
    }
}
