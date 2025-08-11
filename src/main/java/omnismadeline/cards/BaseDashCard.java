package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelinePendedAction;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.DashChancePower;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

import static omnismadeline.util.MadelineUtils.hasDashChances;

public abstract class BaseDashCard extends BaseCard {
    protected static final int GAP = 1;

    public BaseDashCard(String ID, CardStats info) {
        super(ID, info);
        this.tags.add(CustomTags.DASH);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!Objects.equals(abstractPlayer.stance.ID, LandStance.STANCE_ID)) {
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DashChancePower(abstractPlayer, -1), -1));
        }
        onUse(abstractPlayer, abstractMonster);
        this.addToBot(new MadelinePendedAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (!hasDashChances(p)) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return true;
        }
    }
}
