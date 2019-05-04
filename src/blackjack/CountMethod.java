package blackjack;

/**
 * Created by xiebin on 16/10/16.
 */
//算牌方法类，因为我们是牌快发完才洗牌，所以每局游戏都可以记牌，需要算牌方法
public class CountMethod {
    protected CountMethod (int numberOfDecks) {
        decks = numberOfDecks;
    }

    protected int decks;
    protected double runnningTotal;
    protected int cardCount;
    protected int acesCount;
    protected int twosCount;
    protected int threesCount;
    protected int foursCount;
    protected int fivesCount;
    protected int sixesCount;
    protected int sevensCount;
    protected int eightsCount;
    protected int ninesCount;
    protected int tensCount;

    protected double[] GetCounts() {
        return counts;
    }

    protected double[] counts = new double[] {-1, 1, 1, 1, 1, 1, 0, 0, 0, -1};

    protected int SideCount(Card.CardType cardType) {
        int count = 0;

        switch (cardType) {
            case Ace:
                count = acesCount;
                break;
            case Two:
                count = twosCount;
                break;
            case Three:
                count = threesCount;
                break;
            case Four:
                count = foursCount;
                break;
            case Five:
                count = fivesCount;
                break;
            case Six:
                count = sixesCount;
                break;
            case Seven:
                count = sevensCount;
                break;
            case Eight:
                count = eightsCount;
                break;
            case Nine:
                count = ninesCount;
                break;
            case Ten:
                count = tensCount;
                break;
        }

        return count;
    }

    public double InsuranceTenCount() {
        double count = acesCount + twosCount + threesCount + foursCount + fivesCount + sixesCount + sevensCount + eightsCount + ninesCount + (tensCount * -2);

        return count;
    }

    public double Count() {
        return runnningTotal/((decks * 52 - cardCount) /52);
    }

    public double GetWager(double normalBet) {
        double wager = 0;
        double trueCount = Count();

        if (trueCount > 0)
            wager = normalBet * trueCount;
        else if (trueCount == 0)
            wager = normalBet;
        else if (trueCount < 0)
            wager = normalBet * trueCount;

        wager = Math.max(10, wager);

        return wager;
    }

    public void CountCard(Card card) {
        runnningTotal += GetCounts()[card.TrueValue()];
        cardCount ++;

        switch (card.FaceValue()) {
            case Ace:
                acesCount ++;
                break;
            case Two:
                twosCount ++;
                break;
            case Three:
                threesCount ++;
                break;
            case Four:
                foursCount ++;
                break;
            case Five:
                fivesCount ++;
                break;
            case Six:
                sixesCount ++;
                break;
            case Seven:
                sevensCount ++;
                break;
            case Eight:
                eightsCount ++;
                break;
            case Nine:
                ninesCount ++;
                break;
            case Ten:
                tensCount++;
                break;
            case Jack:
                tensCount ++;
                break;
            case Queen:
                tensCount ++;
                break;
            case King:
                tensCount ++;
                break;
        }
    }

    public void Reset(int decks) {
        cardCount = 0;
        runnningTotal = 0;
        acesCount = 0;
        twosCount = 0;
        threesCount = 0;
        foursCount = 0;
        fivesCount = 0;
        sixesCount = 0;
        sevensCount = 0;
        eightsCount = 0;
        ninesCount = 0;
        tensCount = 0;
        this.decks = decks;
    }
}

