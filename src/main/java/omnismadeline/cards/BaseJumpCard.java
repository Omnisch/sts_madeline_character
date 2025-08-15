package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelinePendedAction;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.MomentumPower;
import omnismadeline.stances.LandStance;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

public abstract class BaseJumpCard extends BaseCard {
    protected static final int GAP = 1;

    public BaseJumpCard(String ID, CardStats info) {
        super(ID, info);
        this.tags.add(CustomTags.JUMP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new SoarStance()));
        onUse(p, m);
        this.addToBot(new MadelineGainMomentumAction(1));
        this.addToBot(new MadelinePendedAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (Objects.equals(p.stance.ID, SoarStance.STANCE_ID)) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return true;
        }
    }
}
