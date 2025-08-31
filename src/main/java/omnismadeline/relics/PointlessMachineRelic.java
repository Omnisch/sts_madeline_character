package omnismadeline.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;

import static omnismadeline.MadelineMod.makeID;

public class PointlessMachineRelic extends BaseRelic {
    private static final String NAME = "PointlessMachine"; // The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); // This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; // The relic's rarity
    private static final LandingSound SOUND = LandingSound.CLINK; // The sound played when the relic is clicked.

    private static final int BLOCK = 2;
    private static final int DASHES_BEFORE_UPGRADE = 24;

    public PointlessMachineRelic() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.tags.contains(CustomTags.DASH)) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, BLOCK));

            ++this.counter;
            if (this.counter == DASHES_BEFORE_UPGRADE) {
                AbstractRelic me = this;
                this.addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractPlayer p = AbstractDungeon.player;
                        AbstractRelic relic = RelicLibrary.getRelic(HeavyAndFrailRelic.ID).makeCopy();
                        relic.instantObtain(p, p.relics.indexOf(me), true);
                        this.isDone = true;
                    }
                });
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + BLOCK + this.DESCRIPTIONS[1] + DASHES_BEFORE_UPGRADE + this.DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PointlessMachineRelic();
    }
}
