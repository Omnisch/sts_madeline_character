package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import static omnismadeline.MadelineMod.makeID;

public class TheoCrystalPower extends BasePower {
    public static final String POWER_ID = makeID("TheoCrystal");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private final boolean upgraded;
    private boolean showAlert = false;

    public TheoCrystalPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.upgraded = upgraded;

        if (AbstractDungeon.player.damagedThisCombat == 1) {
            showAlert = true;
        }
        this.updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount == 0) {
            return damageAmount;
        }

        AbstractPlayer p = AbstractDungeon.player;
        if (this.upgraded) {
            if (p.damagedThisCombat == 0) {
                this.showAlert = true;
                this.updateDescription();
                this.flash();
                return damageAmount;
            }
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
        if (this.upgraded) {
            this.description = DESCRIPTIONS[1];
            if (showAlert) {
                this.description += DESCRIPTIONS[2];
            }
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }
}
