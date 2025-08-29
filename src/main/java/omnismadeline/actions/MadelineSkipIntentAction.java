package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MadelineSkipIntentAction extends AbstractGameAction {
    private final AbstractMonster m;

    public MadelineSkipIntentAction(AbstractMonster monster) {
        this.m = monster;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (m != null && !m.isDeadOrEscaped()) {
            int former = (m.moveHistory == null ? 0 : m.moveHistory.size());

            m.rollMove();

            if (m.moveHistory != null && m.moveHistory.size() == former + 1 && former >= 1) {
                m.moveHistory.remove(former - 1);
            }

            m.createIntent();
            m.applyPowers();
        }
        this.isDone = true;
    }
}
