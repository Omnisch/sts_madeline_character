package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.cards.BaseEnvironmentCard;
import omnismadeline.enums.CustomTags;
import omnismadeline.stances.LandStance;

import static omnismadeline.MadelineMod.makeID;

public class DemoDashImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("DemoDash");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DemoDashImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.hasTag(CustomTags.ENVIRONMENT)) {
                ((BaseEnvironmentCard) card).ReduceTmpCost(1);
            }
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(CustomTags.ENVIRONMENT)) {
            ((BaseEnvironmentCard) card).ReduceTmpCost(1);
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(CustomTags.ENVIRONMENT)) {
            this.flash();
            this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
        }
    }

    @Override
    public void onRemove() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.hasTag(CustomTags.ENVIRONMENT)) {
                ((BaseEnvironmentCard) card).RevertTmpCost();
            }
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
