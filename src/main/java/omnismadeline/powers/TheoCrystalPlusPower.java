package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import static omnismadeline.MadelineMod.makeID;

public class TheoCrystalPlusPower extends BasePower {
    public static final String POWER_ID = makeID("TheoCrystalPlus");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private boolean showWarning = false;

    public TheoCrystalPlusPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        if (AbstractDungeon.player.damagedThisCombat == 1) {
            this.showWarning = true;
        }
        this.updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount == 0) {
            return damageAmount;
        }

        AbstractPlayer p = AbstractDungeon.player;
        if (p.damagedThisCombat == 0) {
            this.showWarning = true;
            this.updateDescription();
            this.flash();
            return damageAmount;
        }
        this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        return damageAmount;
    }

    @Override
    public void onVictory() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room == null) return;

        if (!(room instanceof MonsterRoom)) return;

        for (int i = 0; i < this.amount; ++i) {
            room.addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (this.showWarning) {
            this.description += DESCRIPTIONS[1];
        }
    }
}
