package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.enums.CustomTags;
import omnismadeline.powers.DashChancePower;
import omnismadeline.util.CardStats;

import static omnismadeline.util.MadelineUtils.*;

public abstract class BaseDashCard extends BaseMoveCard {
    public BaseDashCard(String ID, CardStats info) {
        super(ID, info);

        this.tags.add(CustomTags.DASH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, -1), -1));
    }

    protected boolean canUseDash(AbstractPlayer p, AbstractMonster m, int gap) {
        boolean canUse = super.canUse(p, m) && this.canUseMove(p, m, gap);
        if (!canUse) {
            return false;
        } else if (canDash(p)) {
            return true;
        } else {
            this.cantUseMessage = this.cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (canDash(AbstractDungeon.player)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}
