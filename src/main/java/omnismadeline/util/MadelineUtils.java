package omnismadeline.util;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import omnismadeline.powers.DashChancePower;
import omnismadeline.stances.SoarStance;

import java.util.Objects;

public class MadelineUtils {

    public static boolean DEPRECATEDcanMove(AbstractPlayer p, int gap) {
        return p.hand.group.size() > gap;
    }

    public static boolean canJump(AbstractPlayer p) {
        return !Objects.equals(p.stance.ID, SoarStance.STANCE_ID);
    }

    public static boolean hasDashChances(AbstractPlayer p) {
        return p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0;
    }
}
