package blackjack;

import java.awt.*;

/**
 * Created by xiebin on 16/10/16.
 */
//庄家类，主要是往手牌中加牌的操作和计算手牌的点数
public class Dealer {
    public Hand dealerHand;

    public Dealer() {
        dealerHand = new Hand(new Point(0,0));
    }

    public void Reset() {
        dealerHand = new Hand(new Point(0,0));
    }

    public void AddCard(Card card) {
        dealerHand.Add(card);
    }

    public int Total() {
        return dealerHand.Total();
    }

    public Hand GetHand() {
        return dealerHand;
    }

}
