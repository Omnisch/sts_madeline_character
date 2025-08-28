package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.cards.Checkpoint;

public class MadelineCheckpointAction extends AbstractGameAction {

    public MadelineCheckpointAction() {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        for (AbstractCard c : p.discardPile.group) {
            if (c instanceof Checkpoint) {
                ((Checkpoint) c).updateDamage();
            }
        }

        for (AbstractCard c : p.drawPile.group) {
            if (c instanceof Checkpoint) {
                ((Checkpoint) c).updateDamage();
            }
        }

        for (AbstractCard c : p.hand.group) {
            if (c instanceof Checkpoint) {
                ((Checkpoint) c).updateDamage();
            }
        }

        this.isDone = true;
    }
}
