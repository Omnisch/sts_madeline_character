package omnismadeline.cards.jumps;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import omnismadeline.actions.MadelineGainMomentumAction;
import omnismadeline.actions.MadelineMoveAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.enums.CustomTags;
import omnismadeline.util.CardStats;

public class Cornerkick extends BaseJumpCard {
    public static final String ID = makeID(Cornerkick.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public Cornerkick() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));

        AbstractCard instance = this.cardsToPreview.makeCopy();
        instance.current_x = (float) Settings.WIDTH / 2.0F + 300.0F * Settings.scale;
        instance.current_y = (float) Settings.HEIGHT / 2.0F;
        ((Jump) instance).m = m;

        instance.onChoseThisOption();
        AbstractDungeon.effectList.add(new ExhaustCardEffect(instance));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cornerkick();
    }
}
