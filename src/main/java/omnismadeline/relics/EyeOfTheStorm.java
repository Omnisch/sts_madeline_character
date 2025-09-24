package omnismadeline.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.patches.GAM_fieldPatch;

import static omnismadeline.MadelineMod.makeID;

public class EyeOfTheStorm extends BaseRelic {
    private static final String NAME = "EyeOfTheStorm";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    private static final int BLOCK = 10;

    public EyeOfTheStorm() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onPlayerEndTurn() {
        if (GAM_fieldPatch.totalDashPlayedThisTurn + GAM_fieldPatch.totalJumpPlayedThisTurn == 0) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, BLOCK));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + BLOCK + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EyeOfTheStorm();
    }
}
