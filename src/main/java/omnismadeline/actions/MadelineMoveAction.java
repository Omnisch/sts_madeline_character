package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomActions;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.powers.MomentumPower;

import static omnismadeline.MadelineMod.modID;

public class MadelineMoveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String SELECT_SCREEN_MESSAGE;
    private final AbstractPlayer p;
    private final AbstractMonster m;
    private final AbstractCard.CardTags fromCardTag;
    private final boolean isRandom;
    private final boolean anyNumber;
    private final boolean canPickZero;
    public static int numMoved;

    public MadelineMoveAction(AbstractMonster target, int amount, AbstractCard.CardTags tag, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.p = AbstractDungeon.player;
        this.target = this.m = target;
        this.fromCardTag = tag;

        // Amount is affected by Momentum.
        int movedAmount;
        if (p.hasPower(MomentumPower.POWER_ID)) {
            movedAmount = amount + p.getPower(MomentumPower.POWER_ID).amount;
        } else {
            movedAmount = amount;
        }

        this.amount = movedAmount;
        this.isRandom = isRandom;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = CustomActions.MADELINE_MOVE;
    }

    public MadelineMoveAction(AbstractMonster target, int amount, AbstractCard.CardTags tag, boolean isRandom, boolean anyNumber) {
        this(target, amount, tag, isRandom, anyNumber, false);
    }

    public MadelineMoveAction(AbstractMonster target, int amount, AbstractCard.CardTags tag, boolean isRandom) {
        this(target, amount, tag, isRandom, false, false);
    }

    public MadelineMoveAction(AbstractMonster target, int amount, AbstractCard.CardTags tag) {
        this(target, amount, tag, false, false, false);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            GAM_fieldPatch.totalMovePlayedThisTurn++;
            GAM_fieldPatch.totalMovePlayedThisCombat++;

            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (!this.anyNumber && this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                numMoved = this.amount;
            }

            if (!this.isRandom) {
                numMoved = this.amount;
                AbstractDungeon.handCardSelectScreen.open(SELECT_SCREEN_MESSAGE, this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            for(int i = 0; i < this.amount; ++i) {
                MadelinePendAndFlushAction.actionsPended.addLast(new MadelineMoveOneCardAction(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng), m, this.fromCardTag));
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                MadelinePendAndFlushAction.actionsPended.addLast(new MadelineMoveOneCardAction(c, m, this.fromCardTag));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(modID + ":" + MadelineMoveAction.class.getSimpleName());
        SELECT_SCREEN_MESSAGE = uiStrings.TEXT[0];
    }
}
