package omnismadeline.util;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import omnismadeline.powers.DashChancePower;

public class MadelineUtils {

    public static boolean canDash(AbstractPlayer p) {
        return p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0;
    }

    public static boolean canJump(AbstractPlayer p, int gap) {
        if (p.hand.group.size() > gap)
            return true;

        return false;
    }
}
