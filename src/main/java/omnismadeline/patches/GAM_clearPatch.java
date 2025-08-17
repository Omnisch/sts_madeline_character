package omnismadeline.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = "clear")
public class GAM_clearPatch {
    public static void Postfix(GameActionManager __instance) {
        GAM_fieldPatch.totalMoveTagPlayedThisTurn = 0;
        GAM_fieldPatch.totalDashTagPlayedThisTurn = 0;
        GAM_fieldPatch.totalJumpTagPlayedThisTurn = 0;
        GAM_fieldPatch.totalEnvrTagPlayedThisTurn = 0;
    }
}
