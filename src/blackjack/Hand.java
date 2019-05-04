package blackjack;

import org.omg.CORBA.SystemException;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by xiebin on 16/10/16.
 */
public class Hand extends ArrayList implements List {
    protected Card[] cards;  //一手牌中牌的集合
    private int cardPosition;   //一手牌中牌的序号
    private Point location;   //这手牌在界面中的位置
    private double wager;   //赌注
    private boolean doubled;   //是否有加倍
    protected final int maxCardPosition = 12;   //一手牌最多为12张
    private final int RESIZEAMOUNT = 4;   //初始的一手牌的数量

    //游戏结果的种类
    public enum OutcomeType {
        None, Won, Lose, Push, Blackjack;
    }

    //构造函数
    public Hand() {
        cards = new Card[RESIZEAMOUNT];
        doubled = false;
        cardPosition = 0;
        wager = 0;
    }

    public Hand(int size) {
        if(size > 0 && size < 13) {
            cards = new Card[size];
        }
        else {
            cards = new Card[RESIZEAMOUNT];
        }

        doubled = false;
        cardPosition = 0;
        wager = 0;
    }

    public Hand(Point pointLocation) {
        location = pointLocation;
        cards = new Card[RESIZEAMOUNT];
        doubled = false;
        cardPosition = 0;
        wager = 0;
    }

    //获得当前这手牌的牌
    public Card[] getCards(){
        return  cards;
    }

    //获得一手牌的赌注
    public double Wager() {
        return wager;
    }

    //设置赌注的值
    public void SetWager(double wager) {
        this.wager = wager;
    }

    //获得当前手牌是否是加倍下注
    public boolean Doubled() {
        return doubled;
    }

    //设置当前手牌是否加倍下注
    public void SetDoubled(boolean doubled) {
        this.doubled = doubled;
    }

    //判断玩家初始两张牌是不是点数相同，为分牌功能提供判断依据
    public boolean IsPair() {
        if (cardPosition == 2) {
            if(cards[0].TrueValue() == cards[1].TrueValue())
                return true;
            else
                return false;
        }
        else
            return false;
    }

    //计算点数之和
    public int Total() {
        int total = 0;
        int aceCount = 0;
        int cardCount = 0;

        for (Card card:cards) {
            if (card != null) {
                cardCount ++;
                switch (card.FaceValue()) {
                    case Ace:
                        total += 11;
                        aceCount ++;
                        break;
                    case Ten:
                        total += 10;
                        break;
                    default:
                        total += card.TrueValue() + 1;
                        break;
                }
            }
        }

        if (total > 21) {
            for (Card card:cards) {
                if (card != null) {
                    if (card.FaceValue().equals(Card.CardType.Ace)) {
                        aceCount -- ;
                        total -= 10;
                    }
                    if (total <= 21)
                        break;
                }
            }
        }

        return total;
    }

    //判断玩家初始的两张牌是不是Blackjack
    public boolean IsBlackjack() {
        return this.Total() == 21 && cardPosition == 2;
    }

    //获取显示点数的控件中应赋予的值
    public String Label(boolean possibleBJ) {
        int total = 0;
        int aceCount = 0;
        int cardCount = 0;

        for (Card card:cards) {
            if (card != null) {
                cardCount ++;
                switch (card.FaceValue()) {
                    case Ace:
                        total += 11;
                        aceCount ++;
                        break;
                    case Ten:
                        total += 10;
                        break;
                    default:
                        total += card.TrueValue() + 1;
                        break;
                }
            }
        }

        if (total > 21) {
            for (Card card:cards) {
                if (card != null) {
                    if (card.FaceValue().equals(Card.CardType.Ace)) {
                        aceCount -- ;
                        total -= 10;
                    }
                    if (total <= 21)
                        break;
                }
            }
        }

        String returnValue = "";
        if (total == 21 && cardCount == 2 && possibleBJ)
            returnValue = "Blackjack";
        else if (aceCount > 0 && cardCount > 1)
            returnValue = "soft" + total;
        else if (total > 21)
            returnValue = "Bust";
        else
            returnValue = "" + total;

        return returnValue;
    }

    //获得这手牌中的牌数
    public int Count() {
        return cardPosition;
    }

    //获得游戏的输赢结果
    public OutcomeType Outcome(Hand dealerHand, int numberofHands) {
        OutcomeType returnValue = OutcomeType.None;

        boolean dealerBlackjack = dealerHand.Total() == 21 && dealerHand.Count() == 2;
        if (this.Total() > 0) {
            if (Total() > 21)
                returnValue = OutcomeType.Lose;
            else if (IsBlackjack() && !dealerBlackjack && numberofHands == 1)
                returnValue = OutcomeType.Blackjack;
            else if (dealerHand.Total() > 21)
                returnValue = OutcomeType.Won;
            else if (Total() < dealerHand.Total())
                returnValue = OutcomeType.Lose;
            else if (Total() > dealerHand.Total())
                returnValue = OutcomeType.Won;
            else if (Total() == dealerHand.Total())
                returnValue = OutcomeType.Push;
        }

        return returnValue;
    }

    //获得策略推荐的操作
    public Strategy.AdviceType GetAdvice(Card dealerCard, Strategy playerStrategy, boolean isSpilt, double cardCount) {
        if (playerStrategy != null)
            return playerStrategy.GetAdvice(this, dealerCard, isSpilt, cardCount);
        else
            return Strategy.AdviceType.None;
    }

    //往手牌中加一张牌
    public int Add(Card card) {
        if (cardPosition == cards.length - 1) {
            int newSize = cardPosition + RESIZEAMOUNT;
            Card[] tmpCards = new Card[newSize];
            System.arraycopy(cards, 0, tmpCards, 0, cards.length);
            cards = tmpCards;
        }

        cards[cardPosition++] = card;

        return cardPosition-1;
    }

    //从一手牌中移走一张牌，分牌功能需要
    public void RemoveAt(int index) {
        Card[] tmp = new Card[cards.length];

        if (index > 0)
            System.arraycopy(cards, 0, tmp, 0, index);

        int newStart = index + 1;
        if (newStart < cards.length)
            System.arraycopy(cards, index, tmp, index, cards.length - index);

        cardPosition --;

        cards = tmp;
    }

}
