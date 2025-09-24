package omnismadeline.cards.jumps;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
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
import omnismadeline.powers.PonderingWaterImplicitPower;
import omnismadeline.stances.SoarStance;
import omnismadeline.util.CardStats;

public class Jump extends BaseCard {
    public static final String ID = makeID(Jump.class.getSimpleName());
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

    public Jump() {
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

        if (!p.hasPower(PonderingWaterImplicitPower.POWER_ID)) {
            this.addToBot(new ChangeStanceAction(new SoarStance()));
        }

        if (!this.autoPlayed) {
            this.addToBot(new MadelineMoveAction(this.m, GAP, CustomTags.JUMP, false, true, true));
        }

        this.addToBot(new MadelineGainMomentumAction(this.magicNumber));

        GAM_fieldPatch.totalJumpPlayedThisTurn++;
        GAM_fieldPatch.totalJumpPlayedThisCombat++;
    }

    @Override
    public void upgrade() { }

    @Override
    public AbstractCard makeCopy() {
        return new Jump();
    }
}
