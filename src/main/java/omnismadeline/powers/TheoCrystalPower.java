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
    private int damageReceiveCount = 0;

    public TheoCrystalPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.upgraded = upgraded;
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (upgraded) {
            if (damageReceiveCount++ < 1) {
                return damageAmount;
            }
        }
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        return damageAmount;
    }

    @Override
    public void onVictory() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room == null) return;

        if (!(room instanceof MonsterRoom)) return;

        room.addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
    }

    public void updateDescription() {
        this.description = upgraded ? DESCRIPTIONS[1] : DESCRIPTIONS[0];
    }
}
