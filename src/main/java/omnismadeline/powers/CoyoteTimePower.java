package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import omnismadeline.enums.CustomTags;
import omnismadeline.stances.SoarStance;

import java.util.Objects;

import static omnismadeline.MadelineMod.makeID;

public class CoyoteTimePower extends BasePower {
    public static final String POWER_ID = makeID("CoyoteTime");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public CoyoteTimePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(CustomTags.JUMP) && Objects.equals(AbstractDungeon.player.stance.ID, SoarStance.STANCE_ID)) {
            if (!card.purgeOnUse) {
                this.flash();
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
