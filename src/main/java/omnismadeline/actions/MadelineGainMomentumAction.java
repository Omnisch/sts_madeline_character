package omnismadeline.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.powers.HeartOfTheMountainPower;
import omnismadeline.powers.JellyfishImplicitPower;
import omnismadeline.powers.MomentumPower;
import omnismadeline.stances.LandStance;

import java.util.Objects;

public class MadelineGainMomentumAction extends AbstractGameAction {
    private final AbstractPlayer p = AbstractDungeon.player;
    private final int amount;

    public MadelineGainMomentumAction(int amount) {
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (canGainMomentum()) {
            this.addToBot(new ApplyPowerAction(p, p, new MomentumPower(p, this.amount), this.amount));

            GAM_fieldPatch.totalMomentumGainedThisTurn += this.amount;
            GAM_fieldPatch.totalMomentumGainedThisCombat += this.amount;

            this.addToBot(new MadelineCheckpointAction());
            if (p.hasPower(JellyfishImplicitPower.POWER_ID)) {
                boolean betterPossible = false;
                boolean possible = false;

                for(AbstractCard c : this.p.hand.group) {
                    if (c.costForTurn > 0) {
                        betterPossible = true;
                    } else if (c.cost > 0) {
                        possible = true;
                    }
                }

                if (betterPossible || possible) {
                    this.findCardAndReduceCost(betterPossible);
                }
            }
        }

        this.isDone = true;
    }

    private boolean canGainMomentum() {
        return !Objects.equals(p.stance.ID, LandStance.STANCE_ID) || p.hasPower(HeartOfTheMountainPower.POWER_ID);
    }

    private void findCardAndReduceCost(boolean better) {
        AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
        if (better) {
            if (c.costForTurn > 0) {
                c.cost = Math.max(c.cost - 1, 0);
                c.costForTurn = Math.max(c.costForTurn - 1, 0);
                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());
            } else {
                this.findCardAndReduceCost(true);
            }
        } else {
            if (c.cost > 0) {
                c.cost = Math.max(c.cost - 1, 0);
                c.costForTurn = Math.max(c.costForTurn - 1, 0);
                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());
            } else {
                this.findCardAndReduceCost(false);
            }
        }
    }
}
