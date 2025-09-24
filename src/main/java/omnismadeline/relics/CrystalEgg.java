package omnismadeline.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;

import static omnismadeline.MadelineMod.makeID;

public class CrystalEgg extends BaseRelic {
    private static final String NAME = "CrystalEgg";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public CrystalEgg() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        for(RewardItem reward : AbstractDungeon.combatRewardScreen.rewards) {
            if (reward.cards != null) {
                for(AbstractCard c : reward.cards) {
                    this.onPreviewObtainCard(c);
                }
            }
        }
    }

    @Override
    public void onPreviewObtainCard(AbstractCard c) {
        this.onObtainCard(c);
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.tags.contains(CustomTags.ENVIRONMENT) && c.canUpgrade() && !c.upgraded) {
            c.upgrade();
        }
    }

    @Override
    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CrystalEgg();
    }
}
