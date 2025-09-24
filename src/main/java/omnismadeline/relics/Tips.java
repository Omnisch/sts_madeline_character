package omnismadeline.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;

import static omnismadeline.MadelineMod.makeID;

public class Tips extends BaseRelic implements ClickableRelic {
    private static final String NAME = "Tips";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public Tips() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onRightClick() {
        AbstractRoom currRoom = AbstractDungeon.getCurrRoom();
        if (currRoom == null
                || currRoom.phase != AbstractRoom.RoomPhase.COMBAT
                || currRoom.monsters == null
                || currRoom.monsters.areMonstersBasicallyDead()) {
            return;
        }

        if (!this.grayscale) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ChangeStanceAction(new LandStance()));
        }
        this.grayscale = true;
    }

    @Override
    public void atBattleStart() {
        this.grayscale = false;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = true;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Tips();
    }
}
