package omnismadeline.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = SpirePatch.CLASS)
public class GAM_fieldPatch {
    public static int totalMoveTagPlayedThisTurn = 0;
    public static int totalDashTagPlayedThisTurn = 0;
    public static int totalJumpTagPlayedThisTurn = 0;
    public static int totalEnvrTagPlayedThisTurn = 0;
}
