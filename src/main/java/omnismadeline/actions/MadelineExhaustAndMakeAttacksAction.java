package omnismadeline.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class MadelineExhaustAndMakeAttacksAction extends AbstractGameAction {
    private final boolean upgraded;

    public MadelineExhaustAndMakeAttacksAction(boolean upgraded) {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        final int count = p.hand.size();

        for (int i = 0; i < count; ++i) {
            if (Settings.FAST_MODE) {
                this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                this.addToTop(new ExhaustAction(1, true, true));
            }
        }

        for (int i = 0; i < count; ++i) {
            AbstractCard c;
            if (this.upgraded) {
                c = returnTrulyRandomNonZeroCostCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
            } else {
                c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
            }
            if (c.cost > 0) {
                c.modifyCostForCombat(-c.cost);
            }
            this.addToBot(new MakeTempCardInHandAction(c, true));
        }

        this.isDone = true;
    }

    public static AbstractCard returnTrulyRandomNonZeroCostCardInCombat(AbstractCard.CardType type) {
        ArrayList<AbstractCard> list = new ArrayList<>();

        for(AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
            if (c.type == type && !c.hasTag(AbstractCard.CardTags.HEALING) && c.cost != 0) {
                list.add(c);
            }
        }

        for(AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
            if (c.type == type && !c.hasTag(AbstractCard.CardTags.HEALING) && c.cost != 0) {
                list.add(c);
            }
        }

        for(AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
            if (c.type == type && !c.hasTag(AbstractCard.CardTags.HEALING) && c.cost != 0) {
                list.add(c);
            }
        }

        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}
