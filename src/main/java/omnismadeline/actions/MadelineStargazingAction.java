package omnismadeline.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.List;
import java.util.function.Consumer;

import static omnismadeline.MadelineMod.modID;

public class MadelineStargazingAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String SELECT_SCREEN_MESSAGE;
    private final int amount;

    public MadelineStargazingAction(int amount) {
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        Consumer<List<AbstractCard>> cb = (list) -> {
            for (AbstractCard card : list) {
                p.drawPile.moveToDeck(card, false);
            }
        };
        this.addToBot(new SelectCardsAction(p.drawPile.group, this.amount, SELECT_SCREEN_MESSAGE, cb));
        this.isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(modID + ":" + MadelineStargazingAction.class.getSimpleName());
        SELECT_SCREEN_MESSAGE = uiStrings.TEXT[0];
    }
}
