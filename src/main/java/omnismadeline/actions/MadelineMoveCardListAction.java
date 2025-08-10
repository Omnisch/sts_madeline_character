package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.enums.CustomTags;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MadelineMoveCardListAction extends AbstractGameAction {
    private final ArrayDeque<AbstractCard> queue = new ArrayDeque<>();
    private final AbstractMonster m;

    public MadelineMoveCardListAction(List<AbstractCard> cards, AbstractMonster m) {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        for (AbstractCard c : cards) {
            queue.addLast(c);
        }
        this.m = m;
    }

    @Override
    public void update() {
        if (queue.isEmpty()) {
            isDone = true;
            return;
        }

        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard c = queue.pollFirst();

        if (c.tags.contains(CustomTags.ENVIRONMENT)) {
            ((BaseEnvironmentCard)c).isAboutToMove = true;
        }

        if (c.canUse(p, m)) {
            this.addToBot(new NewQueueCardAction(c, m, false, false));
        } else {
            p.limbo.moveToDiscardPile(c);
        }

        this.addToBot(new MadelineMoveCardListAction(new ArrayList<>(this.queue), this.m));
        this.isDone = true;
    }
}
