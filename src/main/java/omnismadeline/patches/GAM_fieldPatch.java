package omnismadeline.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = SpirePatch.CLASS)
public class GAM_fieldPatch {
    public static int totalDashPlayedThisTurn = 0;
    public static int totalEnvrPlayedThisTurn = 0;
    public static int totalJumpPlayedThisTurn = 0;
    public static int totalMovePlayedThisTurn = 0;
    public static int totalMovedAsUsedThisTurn = 0;
    public static int totalMovedAsDiscardedThisTurn = 0;

    public static int totalDashPlayedThisCombat = 0;
    public static int totalEnvrPlayedThisCombat = 0;
    public static int totalJumpPlayedThisCombat = 0;
    public static int totalMovePlayedThisCombat = 0;
    public static int totalMovedAsUsedThisCombat = 0;
    public static int totalMovedAsDiscardedThisCombat = 0;
}
