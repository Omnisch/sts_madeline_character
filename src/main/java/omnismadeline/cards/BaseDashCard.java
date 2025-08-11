package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelinePendedAction;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.DashChancePower;
import omnismadeline.util.CardStats;

import static omnismadeline.util.MadelineUtils.hasDashChances;

public abstract class BaseDashCard extends BaseCard {
    public BaseDashCard(String ID, CardStats info) {
        super(ID, info);
        this.tags.add(CustomTags.DASH);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ReducePowerAction(abstractPlayer, abstractPlayer, DashChancePower.POWER_ID, 1));
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
