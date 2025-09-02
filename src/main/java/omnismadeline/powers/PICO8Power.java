package omnismadeline.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RunicDome;
import omnismadeline.relics.HeavyAndFrailRelic;

import static omnismadeline.MadelineMod.makeID;

public class PICO8Power extends BasePower {
    public static final String POWER_ID = makeID("PICO8");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractPlayer p;

    public PICO8Power(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.p = AbstractDungeon.player;
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPlayer p = AbstractDungeon.player;
                AbstractRelic relic = RelicLibrary.getRelic(RunicDome.ID).makeCopy();
                relic.instantObtain(p, p.relics.size(), false);
                this.isDone = true;
            }
        });
    }

    @Override
    public void onRemove() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractRelic former = p.getRelic(RunicDome.ID);
        if (former != null) {
            p.relics.remove(former);
        }
    }

    @Override
    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractRelic former = p.getRelic(RunicDome.ID);
        if (former != null) {
            p.relics.remove(former);
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new GainBlockAction(p, this.amount));
        this.flash();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
