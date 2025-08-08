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

        tags.add(CustomTags.JUMP);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ChangeStanceAction(new SoarStance()));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (!Objects.equals(p.stance.ID, SoarStance.STANCE_ID)) {
            return true;
        } else {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
            return false;
        }
    }
}
