package omnismadeline.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.colorless.DeepBreath;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import omnismadeline.character.MadelineCharacter;

import static omnismadeline.MadelineMod.makeID;

public class ClimbingBackpackRelic extends BaseRelic {
    private static final String NAME = "ClimbingBackpack"; // The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); // This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; // The relic's rarity
    private static final LandingSound SOUND = LandingSound.FLAT; // The sound played when the relic is clicked.

    public ClimbingBackpackRelic() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new DeepBreath()));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ClimbingBackpackRelic();
    }
}
