package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomTags;

import static omnismadeline.MadelineMod.makeID;

public class ChaserImplicitPower extends BasePower {
    public static final String POWER_ID = makeID("Chaser");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ChaserImplicitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.tags.contains(CustomTags.MOVE)) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new DrawCardAction(this.amount));
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
