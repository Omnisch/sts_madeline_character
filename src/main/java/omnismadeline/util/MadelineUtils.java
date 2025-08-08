package omnismadeline.util;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.powers.DashChancePower;

public class MadelineUtils {

    public static boolean canDash(AbstractPlayer p) {
        return p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0;
    }

    public static void refillDashes(AbstractPlayer p) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DashChancePower(p, 1)));
    }
}
