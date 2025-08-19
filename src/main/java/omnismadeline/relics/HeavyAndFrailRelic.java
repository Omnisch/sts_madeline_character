package omnismadeline.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;

import static omnismadeline.MadelineMod.makeID;

public class HeavyAndFrailRelic extends BaseRelic {
    private static final String NAME = "HeavyAndFrail"; // The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); // This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; // The relic's rarity
    private static final LandingSound SOUND = LandingSound.CLINK; // The sound played when the relic is clicked.

    private static final int BLOCK = 12;
    private static final int DASHES_PER_ACT = 3;

    public HeavyAndFrailRelic() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractRelic former = p.getRelic(PointlessMachineRelic.ID);
        if (former != null) {
            former.onUnequip();
            p.relics.remove(former);
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.tags.contains(CustomTags.DASH)) {
            ++this.counter;
            if (this.counter == DASHES_PER_ACT) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new GainBlockAction(AbstractDungeon.player, BLOCK));
                this.counter = 0;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DASHES_PER_ACT + this.DESCRIPTIONS[1] + BLOCK + this.DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HeavyAndFrailRelic();
    }
}
