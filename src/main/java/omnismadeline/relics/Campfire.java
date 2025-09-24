package omnismadeline.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import omnismadeline.character.MadelineCharacter;

import static omnismadeline.MadelineMod.makeID;

public class Campfire extends BaseRelic {
    private static final String NAME = "Campfire";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    private static final int MAGIC = 4;

    public Campfire() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(p, this));
        this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, MAGIC)));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + MAGIC + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Campfire();
    }
}
