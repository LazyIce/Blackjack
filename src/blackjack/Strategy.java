package blackjack;

/**
 * Created by xiebin on 16/10/16.
 */
//策略类，参考了网上的21点赌博策略表，这里主要是单副牌策略和多副牌策略
public abstract class Strategy {
    public final int S = 0;
    public final int H = 1;
    public final int D = 3;
    public final int P = 4;
    public final int N = 5;
    public final int L = 6;

    public enum AdviceType {
        Stand, Hit, Double, Spilt, None, Null;
    }

    protected int [][] Pairs;
    protected int [][] Aces;
    protected int [][] Hand;

    protected Strategy() {};
    public abstract AdviceType GetAdvice(Hand hand, Card card, boolean isSpilt, double cardCount);
    public abstract boolean GetInsuranceAdvice(double count, double cardCount, int decks);
}

class BasicSingleDeck extends Strategy {
    public BasicSingleDeck()
    {
        //Pair		    Dealer 	       A,2,3,4,5,6,7,8,9,10
        Pairs = new int[][] {{P,P,P,P,P,P,P,P,P,P},  // A
                                      {H,H,P,P,P,P,P,H,H,H},  // 2
                                      {H,H,H,P,P,P,P,H,H,H},  // 3
                                      {H,H,H,H,D,D,H,H,H,H}, // 4
                                      {H,D,D,D,D,D,D,D,D,H}, // 5
                                      {H,P,P,P,P,P,H,H,H,H},  // 6
                                      {H,P,P,P,P,P,P,H,H,H},  // 7
                                      {P,P,P,P,P,P,P,P,P,P},   // 8
                                      {S,P,P,P,P,P,S,P,P,S},   // 9
                                      {S,S,S,S,S,S,S,S,S,S}};  // 10

        //An Ace		Dealer 	   A,2,3,4,5,6,7,8,9,10
        Aces  = new int[][]  {{H,H,H,D,D,D,H,H,H,H}, // 2
                                        {H,H,H,D,D,D,H,H,H,H}, // 3
                                        {H,H,H,D,D,D,H,H,H,H}, // 4
                                        {H,H,H,D,D,D,H,H,H,H}, // 5
                                        {H,D,D,D,D,D,H,H,H,H}, // 6
                                        {S,D,D,D,D,S,S,H,H,H},  // 7
                                        {S,S,S,S,S,S,S,S,S,S},   // 8
                                        {S,S,S,S,S,S,S,S,S,S},   // 9
                                        {S,S,S,S,S,S,S,S,S,S}};  // 10

        //No Ace    Dealer      A,2,3,4,5,6,7,8,9,10
        Hand  = new int[][] {{H,H,H,H,H,H,H,H,H,H}, // 3
                                        {H,H,H,H,H,H,H,H,H,H}, // 4
                                        {H,H,H,H,H,H,H,H,H,H}, // 5
                                        {H,H,H,H,H,H,H,H,H,H}, // 6
                                        {H,H,H,H,H,H,H,H,H,H}, // 7
                                        {H,H,H,H,H,H,H,H,H,H}, // 8
                                        {H,D,D,D,D,D,H,H,H,H}, // 9
                                        {H,D,D,D,D,D,D,D,D,H}, // 10
                                        {D,D,D,D,D,D,D,D,D,D}, // 11
                                        {H,H,H,S,S,S,H,H,H,H}, // 12
                                        {H,S,S,S,S,S,H,H,H,H}, // 13
                                        {H,S,S,S,S,S,H,H,H,H}, // 14
                                        {H,S,S,S,S,S,H,H,H,H}, // 15
                                        {H,S,S,S,S,S,H,H,H,H}};// 16
    }

    public  boolean GetInsuranceAdvice(double count, double cardCount, int decks) {
        return cardCount >= 3;
    }

    public AdviceType GetAdvice(Hand playerHand, Card dealerCard, boolean isSplit, double cardCount )
    {
        AdviceType Advice = AdviceType.Null;
        try {
            if (dealerCard != null  && playerHand.Count() > 0 )
            {
                if( playerHand.Count() == 2 && isSplit )
                {
                    if( playerHand.cards[0].FaceValue() == playerHand.cards[1].FaceValue() )
                    {
                        Advice = AdviceType.values()[Pairs[playerHand.cards[0].TrueValue()][dealerCard.TrueValue()]];
                    }
                    else if( playerHand.cards[0].FaceValue() == Card.CardType.Ace )
                    {
                        Advice = AdviceType.values()[Aces[playerHand.cards[1].TrueValue() - 1][dealerCard.TrueValue()]];
                    }
                    else if( playerHand.cards[1].FaceValue() == Card.CardType.Ace )
                    {
                        Advice = AdviceType.values()[Aces[playerHand.cards[0].TrueValue() - 1][dealerCard.TrueValue()]];
                    }
                    else if( playerHand.Total() >= 17 )
                    {
                        Advice = AdviceType.Stand;
                    }
                    else
                    {
                        Advice = AdviceType.values()[Hand[ playerHand.Total() - 3][dealerCard.TrueValue()]];
                    }
                }
                else if( playerHand.Total() >= 17 )
                {
                    Advice = AdviceType.Stand;
                }
                else {
                    Advice = AdviceType.values()[Hand[ playerHand.Total() - 3][dealerCard.TrueValue()]];
                    if( Advice == AdviceType.Double )
                        Advice = AdviceType.Hit;
                }
            }
        }
        catch (Exception Ex) {
            String fault = Ex.getMessage();
        }

        return Advice;
    }
}

class BasicMultiDeck extends Strategy {
    public BasicMultiDeck() {
        //Pair		Dealer       A,2,3,4,5,6,7,8,9,10
        Pairs = new int[][]{{P, P, P, P, P, P, P, P, P, P}, // A
                                     {H, H, H, P, P, P, P, H, H, H}, // 2
                                     {H, H, H, P, P, P, P, H, H, H}, // 3
                                     {H, H, H, H, H, H, H, H, H, H}, // 4
                                     {H, D, D, D, D, D, D, D, D, H}, // 5
                                     {H, H, P, P, P, P, H, H, H, H}, // 6
                                     {H, P, P, P, P, P, P, H, H, H}, // 7
                                     {P, P, P, P, P, P, P, P, P, P}, // 8
                                     {S, P, P, P, P, P, S, P, P, S}, // 9
                                     {S, S, S, S, S, S, S, S, S, S}};// 10

        //An Ace		Dealer     A,2,3,4,5,6,7,8,9,10
        Aces = new int[][]{{H, H, H, H, D, D, H, H, H, H}, // 2
                                     {H, H, H, H, D, D, H, H, H, H}, // 3
                                     {H, H, H, D, D, D, H, H, H, H}, // 4
                                     {H, H, H, D, D, D, H, H, H, H}, // 5
                                     {H, H, D, D, D, D, H, H, H, H}, // 6
                                     {H, S, D, D, D, D, S, S, H, H}, // 7
                                     {S, S, S, S, S, S, S, S, S, S}, // 8
                                     {S, S, S, S, S, S, S, S, S, S}, // 9
                                     {S, S, S, S, S, S, S, S, S, S}};// 10

        //No Ace     Dealer     A,2,3,4,5,6,7,8,9,10
        Hand = new int[][]{{H, H, H, H, H, H, H, H, H, H}, // 3
                                     {H, H, H, H, H, H, H, H, H, H}, // 4
                                     {H, H, H, H, H, H, H, H, H, H}, // 5
                                     {H, H, H, H, H, H, H, H, H, H}, // 6
                                     {H, H, H, H, H, H, H, H, H, H}, // 7
                                     {H, H, H, H, H, H, H, H, H, H}, // 8
                                     {H, H, D, D, D, D, H, H, H, H}, // 9
                                     {H, D, D, D, D, D, D, D, D, H}, // 10
                                     {H, D, D, D, D, D, D, D, D, D}, // 11
                                     {H, H, H, S, S, S, H, H, H, H}, // 12
                                     {H, S, S, S, S, S, H, H, H, H}, // 13
                                     {H, S, S, S, S, S, H, H, H, H}, // 14
                                     {H, S, S, S, S, S, H, H, H, H}, // 15
                                     {H, S, S, S, S, S, H, H, H, H}};// 16
    }

    public boolean GetInsuranceAdvice( double count, double cardCount, int decks ) {
        return cardCount >= 3;
    }

    public AdviceType GetAdvice( Hand playerHand, Card dealerCard, boolean isSplit, double cardCount ) {
        AdviceType Advice = AdviceType.Null;
        try {
            if (dealerCard != null  && playerHand.Count() > 0 )
            {
                if( playerHand.Count() == 2 && isSplit )
                {
                    if( playerHand.cards[0].FaceValue() == playerHand.cards[1].FaceValue() )
                    {
                        Advice = AdviceType.values()[Pairs[ playerHand.cards[0].TrueValue()][dealerCard.TrueValue()]];
                    }
                    else if( playerHand.cards[0].FaceValue() == Card.CardType.Ace )
                    {
                        Advice = AdviceType.values() [Aces[ playerHand.cards[1].TrueValue() - 1][dealerCard.TrueValue()]];
                    }
                    else if( playerHand.cards[1].FaceValue() == Card.CardType.Ace )
                    {
                        Advice = AdviceType.values()[Aces[ playerHand.cards[0].TrueValue() - 1][dealerCard.TrueValue()]];
                    }
                    else if( playerHand.Total() >= 17 )
                    {
                        Advice = AdviceType.Stand;
                    }
                    else
                    {
                        Advice = AdviceType.values()[Hand[ playerHand.Total() - 3][dealerCard.TrueValue()]];
                    }
                }
                else if( playerHand.Total() >= 17 )
                {
                    Advice = AdviceType.Stand;
                }
                else {
                    Advice = AdviceType.values()[Hand[ playerHand.Total() - 3][dealerCard.TrueValue()]];
                    if( Advice == AdviceType.Double )
                        Advice = AdviceType.Hit;
                }
            }
        }
        catch (Exception Ex) {
            String fault = Ex.getMessage();
        }

        return Advice;
        }
}
