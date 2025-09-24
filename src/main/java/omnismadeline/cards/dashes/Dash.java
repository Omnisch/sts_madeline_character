package omnismadeline.cards.dashes;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.cards.BaseCard;
import omnismadeline.enums.CustomTags;
import omnismadeline.patches.GAM_fieldPatch;
import omnismadeline.powers.DashChancePower;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

import java.util.Objects;

public class Dash extends BaseCard {
    public static final String ID = makeID(Dash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.SPECIAL, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            -2
    );

    public AbstractMonster m = null;

    private static final int GAP = 1;
    private static final int MAGIC = 1;

    public Dash() {
        super(ID, info);
        this.setMagic(MAGIC);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;

        // Make sure the character has DashChancePower to activate PendAndFlushAction.
        if (!p.hasPower(DashChancePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, 0)));
        }

        if (!Objects.equals(p.stance.ID, LandStance.STANCE_ID)) {
            if (p.hasPower(DashChancePower.POWER_ID) && p.getPower(DashChancePower.POWER_ID).amount > 0) {
                this.addToBot(new ApplyPowerAction(p, p, new DashChancePower(p, -1), -1));
            }
        }

        if (!this.autoPlayed) {
            this.addToBot(new MadelineMoveAction(m, GAP, CustomTags.DASH, false, true, true));
        }

        this.addToBot(new MadelineGainMomentumAction(this.magicNumber));

        GAM_fieldPatch.totalDashPlayedThisTurn++;
        GAM_fieldPatch.totalDashPlayedThisCombat++;
    }

    @Override
    public void upgrade() { }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dash();
    }
}
