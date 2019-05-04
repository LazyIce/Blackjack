package blackjack;

import java.awt.*;
import javax.swing.*;

/**
 * Created by xiebin on 16/10/16.
 */
public class Card {
    private CardType cardType;   //牌的种类
    private Suits cardSuit;   //牌的花色
    private Image image;   //牌的图片
    private int value;    //每张牌对应的序号
    private int trueValue;   //每张牌的面值

    public enum CardType {
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King;
    }

    public enum Suits {
        Clubs, Diamonds, Hearts, Spades;
    }

    public Card(CardType type, Suits suit) {
        cardSuit = suit;
        cardType = type;
        value = suit.ordinal() * 13 + cardType.ordinal() + 1;
        image = GetImage();
        trueValue = cardType.ordinal();
        if(trueValue > 9)
            trueValue = 9;   //J.Q.K和10是一样的点数
    }

    //获得牌真实点数
    public int TrueValue() {
        return trueValue;
    }


    //获得牌的内容
    public CardType FaceValue () {
        return cardType;
    }

    //获得牌的图片
    public Image GetImage() {
        return new ImageIcon("card/"+String.valueOf(value)+".gif").getImage();
    }
}
