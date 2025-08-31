package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.AnotherMe;
import omnismadeline.cards.BaseCard;

import static omnismadeline.MadelineMod.makeID;

public class AnotherMePlusImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("AnotherMePlus");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AnotherMePlusImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer p = AbstractDungeon.player;

            for (int i = 0; i < this.amount; ++i) {
                for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                    if (card instanceof AnotherMe) {
                        continue;
                    }

                    AbstractMonster m = AbstractDungeon.getRandomMonster();

                    AbstractCard tmp = card.makeSameInstanceOf();
                    if (tmp instanceof BaseCard) {
                        ((BaseCard) tmp).autoPlayed = true;
                    }
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.calculateCardDamage(m);
                    tmp.purgeOnUse = true;
                    AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), false);
                }
            }
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
