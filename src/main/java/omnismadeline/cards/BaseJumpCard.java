package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelinePendedAction;
import omnismadeline.enums.CustomTags;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import static omnismadeline.util.MadelineUtils.canJump;

public abstract class BaseJumpCard extends BaseCard {
    public BaseJumpCard(String ID, CardStats info) {
        super(ID, info);
        this.tags.add(CustomTags.JUMP);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ChangeStanceAction(new SoarStance()));
        onUse(abstractPlayer, abstractMonster);
        this.addToBot(new MadelinePendedAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else if (!canJump(p)) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return true;
        }
    }
}
