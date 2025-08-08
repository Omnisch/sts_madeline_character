package omnismadeline.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public abstract class BaseEnvironmentCard extends BaseCard {
    public boolean isAboutToMove = false;

    public BaseEnvironmentCard(String ID, CardStats info) {
        super(ID, info); // Pass the required information to the BaseCard constructor.
        tags.add(CustomTags.ENVIRONMENT);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        isAboutToMove = false;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return isAboutToMove;
    }
}
