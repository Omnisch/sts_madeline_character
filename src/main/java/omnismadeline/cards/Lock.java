package omnismadeline.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import omnismadeline.actions.MadelineExhaustAndMakeAttacksAction;
import omnismadeline.character.MadelineCharacter;
import omnismadeline.util.CardStats;

import static omnismadeline.MadelineMod.characterPath;

public class Lock extends BaseEnvironmentCard {
    public static final String ID = makeID(Lock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MadelineCharacter.Meta.CARD_COLOR,
            CardType.SKILL, // ATTACK / SKILL / POWER / CURSE / STATUS
            CardRarity.SPECIAL, // BASIC / COMMON / UNCOMMON / RARE / SPECIAL / CURSE
            CardTarget.NONE,
            -2
    );

    public Lock() {
        super(ID, info);
        setExhaust(true);

        setBackgroundTexture(characterPath("cardback/bg_skill_env_colorless.png"), characterPath("cardback/bg_skill_env_colorless_p.png"));
    }

    @Override
    protected void onUse(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MadelineExhaustAndMakeAttacksAction(this.upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Lock();
    }
}
