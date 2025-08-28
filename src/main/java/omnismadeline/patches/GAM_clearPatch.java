package omnismadeline.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = "clear")
public class GAM_clearPatch {
    public static void Postfix(GameActionManager __instance) {
        GAM_fieldPatch.totalDashPlayedThisTurn = 0;
        GAM_fieldPatch.totalEnvrPlayedThisTurn = 0;
        GAM_fieldPatch.totalJumpPlayedThisTurn = 0;
        GAM_fieldPatch.totalMovePlayedThisTurn = 0;
        GAM_fieldPatch.totalMovedAsUsedThisTurn = 0;
        GAM_fieldPatch.totalMovedAsDiscardedThisTurn = 0;
        GAM_fieldPatch.totalMomentumGainedThisTurn = 0;
    }
}
