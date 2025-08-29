package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseCard;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.patches.GAM_fieldPatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static omnismadeline.MadelineMod.modID;

public class MadelineMoveOneCardAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(modID);
    private final AbstractCard c;
    private final AbstractMonster m;
    private final AbstractCard.CardTags fromCardTag;

    public MadelineMoveOneCardAction(AbstractCard c, AbstractMonster m, AbstractCard.CardTags fromCardTag) {
        this.c = c;
        this.m = m;
        this.fromCardTag = fromCardTag;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        // The clearance of c.movedFromCardTag is in BaseCard.use()
        if (c instanceof BaseCard) {
            ((BaseCard) c).movedFromCardTag = this.fromCardTag;
        }

        if (c instanceof BaseEnvironmentCard) {
            ((BaseEnvironmentCard)c).isAboutToMove = true;
        }

        AbstractMonster actualTarget = m.isDead ? AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true) : m;

        if (c.canUse(p, actualTarget)) {
            this.addToBot(new NewQueueCardAction(c, actualTarget, false, false));
            // the PendAndFlushAction here is performed at BaseCard.use().
            GAM_fieldPatch.totalMovedAsUsedThisTurn++;
            GAM_fieldPatch.totalMovedAsUsedThisCombat++;
            logger.info("Move card {} as played.", c.cardID);
        } else {
            p.limbo.moveToDiscardPile(c);
            this.addToBot(new MadelinePendAndFlushAction());
            GAM_fieldPatch.totalMovedAsDiscardedThisTurn++;
            GAM_fieldPatch.totalMovedAsDiscardedThisCombat++;
            logger.info("Move card {} as discarded.", c.cardID);
        }

        this.isDone = true;
    }
}
