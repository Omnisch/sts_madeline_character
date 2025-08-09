package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomTags;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

public abstract class BaseJumpCard extends BaseMoveCard {

    public BaseJumpCard(String ID, CardStats info) {
        super(ID, info);

        this.tags.add(CustomTags.JUMP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new SoarStance()));
    }

    protected boolean canUseJump(AbstractPlayer p, AbstractMonster m, int gap) {
        boolean canUse = super.canUse(p, m) && this.canUseMove(p, m, gap);
        if (!canUse) {
            return false;
        } else if (!Objects.equals(p.stance.ID, SoarStance.STANCE_ID)) {
            return true;
        } else {
            this.cantUseMessage = this.cardStrings.EXTENDED_DESCRIPTION[1];
            return false;
        }
    }
}
