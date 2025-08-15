package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.powers.MomentumPower;
import omnismadeline.stances.LandStance;

import java.util.Objects;

public class MadelineGainMomentumAction extends AbstractGameAction {
    private final int amount;

    public MadelineGainMomentumAction(int amount) {
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (!Objects.equals(p.stance.ID, LandStance.STANCE_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new MomentumPower(p, amount), amount));
        }

        this.isDone = true;
    }
}
