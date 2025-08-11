package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.enums.CustomTags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static omnismadeline.MadelineMod.modID;

public class MadelineMoveOneCardAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(modID);
    private final AbstractCard c;
    private final AbstractMonster m;

    public MadelineMoveOneCardAction(AbstractCard c, AbstractMonster m) {
        this.c = c;
        this.m = m;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (c.tags.contains(CustomTags.ENVIRONMENT)) {
            ((BaseEnvironmentCard)c).isAboutToMove = true;
        }

        if (c.canUse(p, m)) {
            this.addToBot(new NewQueueCardAction(c, m, false, false));
            logger.info("Move card {} as played.", c.cardID);
        } else {
            p.limbo.moveToDiscardPile(c);
            logger.info("Move card {} as discarded.", c.cardID);
        }

        this.isDone = true;
    }
}
