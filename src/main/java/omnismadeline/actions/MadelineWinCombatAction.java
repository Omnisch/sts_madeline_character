package omnismadeline.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class MadelineWinCombatAction extends AbstractGameAction {
    public MadelineWinCombatAction() {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m != null && m.type == AbstractMonster.EnemyType.BOSS) {
                this.isDone = true;
                return;
            }
        }
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m != null && !m.isDeadOrEscaped()) {
                addToTop(new InstantKillAction(m));
            }
        }
        addToTop(new VFXAction(new BorderLongFlashEffect(Color.SKY)));
        AbstractDungeon.overlayMenu.endTurnButton.disable();

        this.isDone = true;
    }
}
