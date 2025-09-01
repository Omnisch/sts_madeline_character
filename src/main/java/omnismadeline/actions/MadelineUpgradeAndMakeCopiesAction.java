package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

import static com.badlogic.gdx.math.Rectangle.tmp;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static omnismadeline.MadelineMod.modID;

public class MadelineUpgradeAndMakeCopiesAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String SELECT_SCREEN_MESSAGE;
    private final AbstractPlayer p;
    private final ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
    private final boolean isRandom;
    private final boolean anyNumber;
    private final boolean canPickZero;
    public static int numUpgraded;

    public MadelineUpgradeAndMakeCopiesAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.isRandom = isRandom;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public MadelineUpgradeAndMakeCopiesAction(int amount, boolean isRandom, boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }

    public MadelineUpgradeAndMakeCopiesAction(int amount, boolean isRandom) {
        this(amount, isRandom, false, false);
    }

    public MadelineUpgradeAndMakeCopiesAction(int amount) {
        this(amount, false, false, false);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            for (AbstractCard c : this.p.hand.group) {
                if (!c.canUpgrade()) {
                    this.cannotUpgrade.add(c);
                }
            }

            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (!this.anyNumber && this.p.hand.size() - this.cannotUpgrade.size() <= this.amount) {
                this.amount = this.p.hand.size() - this.cannotUpgrade.size();
                numUpgraded = this.amount;

                for (AbstractCard c : this.p.hand.group) {
                    if (c.canUpgrade()) {
                        c.upgrade();
                        c.superFlash();
                        c.applyPowers();
                    }
                    this.addToBot(new MakeTempCardInDiscardAction(c.makeStatEquivalentCopy(), 1));
                }

                this.isDone = true;
                return;
            }

            if (!this.isRandom) {
                numUpgraded = this.amount;
                AbstractDungeon.handCardSelectScreen.open(SELECT_SCREEN_MESSAGE, this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            final ArrayList<AbstractCard> tmp = new ArrayList<>();
            for (AbstractCard c : this.p.hand.group) {
                if (!this.cannotUpgrade.contains(c)) {
                    tmp.add(c);
                }
            }

            for (int i = 0; i < this.amount; ++i) {
                AbstractCard c = tmp.get(cardRandomRng.random(tmp.size() - 1));
                c.upgrade();
                c.superFlash();
                c.applyPowers();
                this.addToBot(new MakeTempCardInDiscardAction(c.makeStatEquivalentCopy(), 1));
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.canUpgrade()) {
                    c.upgrade();
                    c.superFlash();
                    c.applyPowers();
                }
                p.hand.addToTop(c);
                this.addToBot(new MakeTempCardInDiscardAction(c.makeStatEquivalentCopy(), 1));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(modID + ":" + MadelineUpgradeAndMakeCopiesAction.class.getSimpleName());
        SELECT_SCREEN_MESSAGE = uiStrings.TEXT[0];
    }
}
