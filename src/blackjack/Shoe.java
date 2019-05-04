package blackjack;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * Created by xiebin on 16/10/16.
 */
public class Shoe {
    private final int CARDSPERDECK = 52;  //一副牌的数量
    private Card[] cards;
    private int numberOfDecks = 1;  //默认牌盒中是一副牌
    private static ImageIcon backImage;  //牌的图片
    private BackTypes backType;  //牌背的种类
    private int shoeLocation;  //牌盒中第几张牌

    public enum BackTypes {
        Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
    }

    public void SetNumberOfDecks(int value) {
        numberOfDecks = value;
    }

    public Shoe() {
        backType = BackTypes.Jan;
    }

    public void Init() {
        shoeLocation = 0;

        cards = new Card[numberOfDecks * CARDSPERDECK];
        int current = 0;
        for (int i = 0; i < numberOfDecks; i ++) {
            for (int j = 0; j < 4; j ++) {
                for (int k = 0; k < 13; k ++) {
                    cards[current ++] = new Card(Card.CardType.values()[k], Card.Suits.values()[j]);
                }
            }
        }
    }

    //判断是否要洗牌
    public boolean EndDeck() {
        return shoeLocation >= cards.length * 0.66;
    }

    //洗牌的功能
    public void Shuffle() {
        Random random = new Random(LocalDateTime.now().getSecond());

       for (int i = 0; i < random.nextInt(10) + 10; i ++) { //洗随机次牌
            Card[] shuffleCards = new Card[numberOfDecks * CARDSPERDECK];

            Random index = new Random(LocalDateTime.now().getSecond());
            int upperLimit = cards.length;

            for (int j = 0; j < numberOfDecks * CARDSPERDECK; j ++) { //洗一次牌
                int k = index.nextInt(upperLimit);
                shuffleCards[j] = cards[k];
                cards[k] = cards[-- upperLimit];
            }

            cards = shuffleCards;
       }

        shoeLocation = 0;
    }

    //牌盒中下一张牌
    public Card Next() {
        if (shoeLocation < cards.length) {
            return cards[shoeLocation ++];
        }
        else {
            Shuffle();
            return cards[shoeLocation++];
        }
    }

}
