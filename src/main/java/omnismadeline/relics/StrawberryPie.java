package omnismadeline.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import omnismadeline.character.MadelineCharacter;

import static omnismadeline.MadelineMod.makeID;

public class StrawberryPie extends BaseRelic {
    private static final String NAME = "StrawberryPie";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.SOLID;

    private static final int DAMAGE = 8;
    private static final int ENERGIES_PER_ACT = 6;

    public StrawberryPie() {
        super(ID, NAME, MadelineCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.costForTurn > 0) {
            this.counter += c.costForTurn;
            while (this.counter >= ENERGIES_PER_ACT) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DAMAGE, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.counter -= ENERGIES_PER_ACT;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + ENERGIES_PER_ACT + this.DESCRIPTIONS[1] + DAMAGE + this.DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new StrawberryPie();
    }
}
