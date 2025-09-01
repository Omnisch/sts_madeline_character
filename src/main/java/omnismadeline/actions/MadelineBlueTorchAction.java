package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static omnismadeline.MadelineMod.modID;

public class MadelineBlueTorchAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String SELECT_SCREEN_MESSAGE;
    private final AbstractPlayer p;
    private final int plateArmorAmount;
    private final boolean isRandom;
    private final boolean anyNumber;
    private final boolean canPickZero;
    public static int numExhausted;

    private MadelineBlueTorchAction(int amount, int plateArmorAmount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.plateArmorAmount = plateArmorAmount;
        this.isRandom = isRandom;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public MadelineBlueTorchAction(int amount, int plateArmorAmount, boolean isRandom, boolean anyNumber) {
        this(amount, plateArmorAmount, isRandom, anyNumber, false);
    }

    public MadelineBlueTorchAction(int amount, int plateArmorAmount, boolean isRandom) {
        this(amount, plateArmorAmount, isRandom, false, false);
    }

    public MadelineBlueTorchAction(int amount, int plateArmorAmount) {
        this(amount, plateArmorAmount, false, false, false);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (!this.anyNumber && this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                numExhausted = this.amount;
                int tmp = this.p.hand.size();

                for(int i = 0; i < tmp; ++i) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(c);
                    if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                        this.addToBot(new ApplyPowerAction(this.p, this.p, new PlatedArmorPower(this.p, this.plateArmorAmount), this.plateArmorAmount));
                    }
                }

                return;
            }

            if (!this.isRandom) {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(SELECT_SCREEN_MESSAGE, this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            for(int i = 0; i < this.amount; ++i) {
                AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                this.p.hand.moveToExhaustPile(c);
                if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                    this.addToBot(new ApplyPowerAction(this.p, this.p, new PlatedArmorPower(this.p, this.plateArmorAmount), this.plateArmorAmount));
                }
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToExhaustPile(c);
                if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                    this.addToBot(new ApplyPowerAction(this.p, this.p, new PlatedArmorPower(this.p, this.plateArmorAmount), this.plateArmorAmount));
                }
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(modID + ":" + MadelineBlueTorchAction.class.getSimpleName());
        SELECT_SCREEN_MESSAGE = uiStrings.TEXT[0];
    }
}
