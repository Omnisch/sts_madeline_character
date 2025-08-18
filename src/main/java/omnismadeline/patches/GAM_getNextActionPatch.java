package omnismadeline.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")
public class GAM_getNextActionPatch {
    public static void Postfix(GameActionManager __instance) {
        if (__instance.actions.isEmpty() &&
            __instance.preTurnActions.isEmpty() &&
            __instance.cardQueue.isEmpty() &&
            __instance.monsterAttacksQueued &&
            __instance.monsterQueue.isEmpty() &&
            __instance.turnHasEnded && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            GAM_fieldPatch.totalDashPlayedThisTurn = 0;
            GAM_fieldPatch.totalEnvrPlayedThisTurn = 0;
            GAM_fieldPatch.totalJumpPlayedThisTurn = 0;
            GAM_fieldPatch.totalMovePlayedThisTurn = 0;
            GAM_fieldPatch.totalMovedAsUsedThisTurn = 0;
            GAM_fieldPatch.totalMovedAsDiscardedThisTurn = 0;
        }
    }
}
