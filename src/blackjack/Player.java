package blackjack;
import java.awt.*;
/**
 * Created by xiebin on 16/10/16.
 */
public class Player {
    private Point location;   //玩家的位置
    private int bank;   //玩家的账户金额
    private boolean insurance = false;   //玩家是否保险了
    private Strategy playerStrategy;   //玩家使用的策略
    private CountMethod playerMethod;   //玩家使用的算牌方法
    private int currentHandIndex = 0;   //玩家所有的手牌中当前的手牌序号
    private Hand[] hands;   //玩家所有的手牌
    private int numberOfHands = 1;   //玩家最多有2手牌，界面大小有限
    public PlayerType playerType;   //玩家的类型（因为策略和算牌方法还有些不足，AI还未完全实现）
    private boolean active = true;   //当前玩家是否参与游戏
    private int playerBet;   //赌注

    //输出结果控件中的内容对应的值的类型
    public enum LabelType {
        None, DrawToHand, BothHands, Outcome;
    }

    //玩家种类（因为策略和算牌方法还有些不足，AI还未完全实现，所以其实电脑玩家没用==）
    public enum PlayerType {
        Computer, Human;
    }

    //玩家的构造函数
    public Player(Point pointLocation, int dbbank, PlayerType type, Strategy strategy, CountMethod method) {
        hands = new Hand[2];
        hands[0] = new Hand(new Point(0,0));
        hands[1] = new Hand(new Point(50,0));

        location = pointLocation;
        bank = dbbank;
        playerStrategy = strategy;
        playerMethod = method;
        playerType = type;

        numberOfHands = 1;
    }

    //玩家获得所有的手牌
    public Hand[] GetHands() {
        return hands;
    }

    //玩家获得有几手牌
    public int NumberOfHands() {
        return numberOfHands;
    }

    //获得游戏进行中玩家正在操作的手牌
    public Hand CurrentHand() {
        return hands[currentHandIndex];
    }

    //获得玩家账户金额
    public int GetBank() {
        return bank;
    }

    //玩家胜利，账户金额变化
    public void Won(Hand hand) {
        bank += hand.Wager() * 2;
    }

    //玩家是Blackjack获胜后账户金额变化
    public void Blackjack(Hand hand) {
        bank += hand.Wager() * 2.5;
    }

    //玩家和庄家平局
    public void Push(Hand hand) {
        bank += hand.Wager();
    }

    //获得玩家是否保险的情况
    public boolean IsInsurance() {
        return insurance;
    }

    //设置玩家是否保险
    public void SetInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    //玩家保险失败账户金额变化
    public void LostInsurance() {
        if (insurance)
            bank -= hands[0].Wager();
    }

    //玩家保险成功账户金额变化
    public void WonInsurance() {
        if (insurance)
            bank += hands[0].Wager();
    }

    //玩家两手牌的重置
    public void Reset() {
        hands = new Hand[2];
        hands[0] = new Hand(new Point(0,0));
        hands[1] = new Hand(new Point(50,0));
        numberOfHands = 1;
        currentHandIndex = 0;
        insurance = false;
    }

    //获得玩家的赌注
    public void GetWager() {
        if (playerType.equals(PlayerType.Computer) && playerMethod != null)
            hands[0].SetWager(playerMethod.GetWager(playerBet));
        else
            hands[0].SetWager(playerBet);

        bank -= hands[0].Wager();
    }

    //玩家在发牌后因一些特殊操作改变赌注
    public void SetWagerAfterDeal(int bet){
        playerBet=bet;
    }

    //判断玩家的手牌是否可以加倍下注
    public boolean CanDouble(Hand hand) {
        return (((hand.Total() ==10 || hand.Total() ==11)) && hand.Count() == 2);
    }

    //判断玩家的手牌是否进行了双倍下注
    public boolean DoubleDown(Hand hand) {
        if (CanDouble(hand)) {
            bank -= hand.Wager();
            hand.SetWager(hand.Wager() * 2);
            hand.SetDoubled(true);
            return true;
        }

        return false;
    }

    //判断玩家的手牌是否可以分牌
    public boolean CanSpilt() {
        return (hands[0].IsPair() && numberOfHands == 1);
    }

    public boolean Spilt(Hand hand) {
        if (CanSpilt()) {
            hands[1].Add(hands[0].cards[1]);
            hands[0].RemoveAt(hands[0].Count());
            hands[1].SetWager(hands[0].Wager());
            bank -= hands[1].Wager();
            numberOfHands ++;
            return true;
        }
        return  false;
    }

    //玩家获得第二手牌
    public void NextHand() {
        currentHandIndex ++;
    }

    //判断是是否是玩家的最后一手牌
    public boolean LastHand() {
        return currentHandIndex + 1 == numberOfHands;
    }

    //计算两手牌的赌注之和
    public double TotalWager() {
        double wager = 0;

        for (Hand hand:hands) {
            wager += hand.Wager();
        }

        return wager;
    }

    //判断该玩家是否参与游戏中
    public boolean Active() {
        return active;
    }

    public CountMethod Method () {
        return playerMethod;
    }

    public Strategy CardStrategy() {
        return playerStrategy;
    }


    public Strategy.AdviceType GetAdvice(Card dealerCard) {
        return hands[currentHandIndex].GetAdvice(dealerCard, playerStrategy, !(numberOfHands == 2), playerMethod != null ? playerMethod.Count() : 0);
    }

    public boolean GetInsuranceAdvice(int decks) {
        boolean advice = false;

        if (playerStrategy != null) {
            if (playerMethod != null)
                advice = playerStrategy.GetInsuranceAdvice(playerMethod.InsuranceTenCount(), playerMethod.Count(), decks);
            else
                advice = playerStrategy.GetInsuranceAdvice(0, 0, decks);
        }

        return advice;
    }

    public void ResetCount(int decks) {
        if (playerMethod != null)
            playerMethod.Reset(decks);
    }

    public void CountCard(Card newCard) {
        if(playerMethod != null)
            playerMethod.CountCard(newCard);
    }

    public Point getLocation(){
        return location;
    }

}


