package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;

import static omnismadeline.MadelineMod.modID;

public class DEPRECATEDMadelineTrueBottomAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(modID);
    public static final ArrayDeque<AbstractGameAction> actionQueue = new ArrayDeque<>();

    @Override
    public void update() {
        if (actionQueue.isEmpty()) {
            isDone = true;
            return;
        }

        GameActionManager am = AbstractDungeon.actionManager;
        boolean othersPending =
                !am.actions.isEmpty()
                || !AbstractDungeon.player.limbo.isEmpty();

        if (othersPending) {
            am.addToBottom(new DEPRECATEDMadelineTrueBottomAction());
            this.isDone = true;
            return;
        }

        AbstractGameAction a = actionQueue.pollFirst();
        if (a != null) {
            this.addToBot(a);
            logger.info("Poll action {}.", a.getClass().getSimpleName());
            this.addToBot(new DEPRECATEDMadelineTrueBottomAction());
        }
        isDone = true;
    }
}
