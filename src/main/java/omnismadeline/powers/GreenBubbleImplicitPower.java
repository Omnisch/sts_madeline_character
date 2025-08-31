package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomTags;

import static omnismadeline.MadelineMod.makeID;

public class GreenBubbleImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("GreenBubble");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private final int draw;

    public GreenBubbleImplicitPower(AbstractCreature owner, int amount, int draw) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.draw = draw;
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.tags.contains(CustomTags.DASH)) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new DrawCardAction(this.draw));
            this.addToBot(new ReducePowerAction(p, p, this, 1));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        }
    }

    public void updateDescription() {
        if (this.amount == 1 && this.draw == 1) {
            this.description = DESCRIPTIONS[0];
        } else if (this.amount == 1) {
            this.description = DESCRIPTIONS[1] + this.draw + DESCRIPTIONS[5];
        } else if (this.draw == 1) {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[4] + this.draw + DESCRIPTIONS[5];
        }
    }
}
