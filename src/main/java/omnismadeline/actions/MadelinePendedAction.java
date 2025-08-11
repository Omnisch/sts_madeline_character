package omnismadeline.actions;

import basemod.interfaces.OnStartBattleSubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayDeque;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static omnismadeline.MadelineMod.modID;

public class MadelinePendedAction extends AbstractGameAction implements OnStartBattleSubscriber {
    public static final Logger logger = LogManager.getLogger(modID);
    public static final ArrayDeque<AbstractGameAction> actionsPended = new ArrayDeque<>();

    @Override
    public void update() {
        if (actionsPended.isEmpty()) {
            this.isDone = true;
            return;
        }

        AbstractGameAction a = actionsPended.pollFirst();
        this.addToBot(a);
        logger.info("Add pended action {} to bottom.", a.getClass().getSimpleName());
        this.isDone = true;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        actionsPended.clear();
    }
}
