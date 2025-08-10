package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.enums.CustomActions;
import omnismadeline.enums.CustomTags;

import static omnismadeline.MadelineMod.modID;

public class MadelineMoveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractPlayer p;
    private final boolean isRandom;
    private final boolean anyNumber;
    private final boolean canPickZero;
    public static int numMoved;

    private MadelineMoveAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.isRandom = isRandom;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = CustomActions.MADELINE_MOVE;
    }

    public MadelineMoveAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.target = target;
        this.source = source;
    }

    public MadelineMoveAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
        this.target = target;
        this.source = source;
    }

    public MadelineMoveAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(amount, isRandom, false, false);
        this.target = target;
        this.source = source;
    }

    public MadelineMoveAction(AbstractCreature target, AbstractCreature source, int amount) {
        this(amount, false, false, false);
        this.target = target;
        this.source = source;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (!this.anyNumber && this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                numMoved = this.amount;
                int tmp = this.p.hand.size();

                for(int i = 0; i < tmp; ++i) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.madelineMove(c);
                }

                this.tickDuration();
                return;
            }

            if (!this.isRandom) {
                numMoved = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            for(int i = 0; i < this.amount; ++i) {
                this.madelineMove(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.madelineMove(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    private void madelineMove(AbstractCard c) {
        if (c.costForTurn <= p.energy.energy) {
            if (c.tags.contains(CustomTags.ENVIRONMENT)) {
                ((BaseEnvironmentCard)c).isAboutToMove = true;
            }
            this.addToTop(new NewQueueCardAction(c, true, false, false));
        } else {
            this.p.hand.moveToDiscardPile(c);
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(modID + ":" + MadelineMoveAction.class.getSimpleName());
        TEXT = uiStrings.TEXT;
    }
}
