package omnismadeline.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.stances.LandStance;
import omnismadeline.util.CardStats;

public class CassetteBlock extends BaseCard {
    public static final String ID = makeID(CassetteBlock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.ATTACK, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.UNCOMMON, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    public CassetteBlock() {
        super(ID, info);
        this.setDamage(DAMAGE, UPG_DAMAGE);
        this.setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new LandStance()));
        if (GameActionManager.turn % 2 == 1) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else {
            this.addToBot(new GainBlockAction(p, this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CassetteBlock();
    }
}
