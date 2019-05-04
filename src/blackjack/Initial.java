package blackjack;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.io.*;


public class Initial {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                InitialFrame frame = new InitialFrame();

                frame.setTitle("Blackjack@LazyIce");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);


                frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                    InitialFrame.height=frame.getHeight();
                    InitialFrame.width=frame.getWidth();
                    frame.changesize();
                }
                });

            }
        });
        //获取当前jre所支持的所有字体
        Font[] fonts = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getAllFonts();
        for (Font f : fonts) {
            System.out.println("Name:" + f.getFontName());
        }
    }


}



class InitialFrame extends JFrame implements ActionListener{
    BackgroundPanel backgroundPanel;
    ButtonGroup buttonGroup;
    CardLayerStart layerStart;
    CardLayerRules layerRules;
    CardLayerSet layerSet;
    CardLayerAbout layerAbout;
    CardLayout card;
    static int height;
    static int width;


    //将jframe下所有字体统一
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public InitialFrame() {

        InitGlobalFont(new SetFont().getFont("fonts/ALGER.TTF",30.0F));
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        height=(int)(0.9*screenSize.getHeight());width=(int)(screenSize.getWidth()*0.9);
        //height=1000;width=1700;
        setSize(width, height);
        setLocationByPlatform(true);

        buttonGroup = new ButtonGroup();
        layerStart=new CardLayerStart();
        layerRules=new CardLayerRules();
        layerSet=new CardLayerSet();
        layerAbout=new CardLayerAbout();

        backgroundPanel= new BackgroundPanel((new ImageIcon("images/initial.png")).getImage());
        card=new CardLayout();
        backgroundPanel.setLayout(card);
        backgroundPanel.add("start panel",layerStart);
        backgroundPanel.add("rules panel",layerRules);
        backgroundPanel.add("set panel",layerSet);
        backgroundPanel.add("about panel",layerAbout);

        backgroundPanel.add("buttons panel",buttonGroup);

        card.show(backgroundPanel,"buttons panel");
        add(backgroundPanel);

        setResizable(true);


        buttonGroup.about.addActionListener(this);
        buttonGroup.set.addActionListener(this);
        buttonGroup.rules.addActionListener(this);
        buttonGroup.start.addActionListener(this);
        layerRules.goback.addActionListener(this);
        layerAbout.goback.addActionListener(this);
        layerSet.goback.addActionListener(this);
        layerStart.jmusic.addActionListener(this);
        layerStart.jgoback.addActionListener(this);
        layerStart.jdealbutton.addActionListener(this);
        layerStart.jhitbutton.addActionListener(this);
        layerStart.jstandbutton.addActionListener(this);
        layerStart.jdoublebutton.addActionListener(this);
        layerStart.jsplitbutton.addActionListener(this);
        layerStart.buttonInsure.addActionListener(this);
        layerStart.buttonnotInsure.addActionListener(this);

    }

    public void changesize(){
        buttonGroup.start.setBounds((int)((double)800/1700*width),(int)((double)200/1000*height),(int)((double)200/1700*width),(int)((double)100/1000*height));
        buttonGroup.rules.setBounds((int)((double)800/1700*width),(int)((double)350/1000*height),(int)((double)200/1700*width),(int)((double)100/1000*height));
        buttonGroup.set.setBounds((int)((double)800/1700*width),(int)((double)500/1000*height),(int)((double)200/1700*width),(int)((double)100/1000*height));
        buttonGroup.about.setBounds((int)((double)800/1700*width),(int)((double)650/1000*height),(int)((double)200/1700*width),(int)((double)100/1000*height));

        layerStart.jmusic.setBounds((int)((double)1400/1700*width),(int)((double)20/1000*height),(int)((double)100/1700*width),(int)((double)100/1000*height));layerStart.jgoback.setBounds((int)((double)1535/1700*width),(int)((double)20/1000*height),(int)((double)100/1700*width),(int)((double)100/1000*height));
        layerStart.jdealbutton.setBounds((int)((double)810/1700*width),(int)((double)910/1000*height),(int)((double)155/1700*width),(int)((double)65/1000*height));
        layerStart.jhitbutton.setBounds((int)((double)970/1700*width),(int)((double)910/1000*height),(int)((double)155/1700*width),(int)((double)65/1000*height));
        layerStart.jstandbutton.setBounds((int)((double)1130/1700*width),(int)((double)910/1000*height),(int)((double)170/1700*width),(int)((double)65/1000*height));
        layerStart.jdoublebutton.setBounds((int)((double)1303/1700*width),(int)((double)910/1000*height),(int)((double)193/1700*width),(int)((double)65/1000*height));
        layerStart.jsplitbutton.setBounds((int)((double)1507/1700*width),(int)((double)910/1000*height),(int)((double)155/1700*width),(int)((double)65/1000*height));
        layerStart.jspinners[0].setBounds((int)((double)120/1700*width),(int)((double)380/1000*height),80,30);
        layerStart.jspinners[1].setBounds((int)((double)520/1700*width),(int)((double)580/1000*height),80,30);
        layerStart.jspinners[2].setBounds((int)((double)1020/1700*width),(int)((double)580/1000*height),80,30);
        layerStart.jspinners[3].setBounds((int)((double)1320/1700*width),(int)((double)380/1000*height),80,30);
        layerStart.jmoney[0].setBounds((int)((double)230/1700*width),(int)((double)360/1000*height),80,30);
        layerStart.jmoney[1].setBounds((int)((double)630/1700*width),(int)((double)560/1000*height),80,30);
        layerStart.jmoney[2].setBounds((int)((double)1130/1700*width),(int)((double)560/1000*height),80,30);
        layerStart.jmoney[3].setBounds((int)((double)1430/1700*width),(int)((double)360/1000*height),80,30);
        layerStart.jsums[0].setBounds((int)((double)0/1700*width),(int)((double)420/1000*height),90,30);
        layerStart.jsums[1].setBounds((int)((double)410/1700*width),(int)((double)620/1000*height),90,30);
        layerStart.jsums[2].setBounds((int)((double)920/1700*width),(int)((double)620/1000*height),90,30);
        layerStart.jsums[3].setBounds((int)((double)1220/1700*width),(int)((double)420/1000*height),90,30);
        layerStart.jsums[4].setBounds((int)((double)680/1700*width),(int)((double)160/1000*height),90,30);
        layerStart.jsecondsums[0].setBounds((int)((double)230/1700*width),(int)((double)420/1000*height),90,30);
        layerStart.jsecondsums[1].setBounds((int)((double)630/1700*width),(int)((double)620/1000*height),90,30);
        layerStart.jsecondsums[2].setBounds((int)((double)1130/1700*width),(int)((double)620/1000*height),90,30);
        layerStart.jsecondsums[3].setBounds((int)((double)1430/1700*width),(int)((double)420/1000*height),90,30);
        layerStart.topleftpoints[4].setLocation((int)((double)800/1700*InitialFrame.width),(int)((double)220/1000*InitialFrame.height));
        layerStart.topleftpoints[0].setLocation((int)((double)120/1700*InitialFrame.width),(int)((double)480/1000*InitialFrame.height));
        layerStart.topleftpoints[1].setLocation((int)((double)520/1700*InitialFrame.width),(int)((double)680/1000*InitialFrame.height));
        layerStart.topleftpoints[2].setLocation((int)((double)1020/1700*InitialFrame.width),(int)((double)680/1000*InitialFrame.height));
        layerStart.topleftpoints[3].setLocation((int)((double)1320/1700*InitialFrame.width),(int)((double)480/1000*InitialFrame.height));
        layerStart.cardboxpoint.setLocation((int)((double)1400/1700*InitialFrame.width),(int)((double)220/1000*InitialFrame.height));
        layerStart.labelbacktype.setBounds((int)((double)10/1700*width),(int)((double)900/1000*height),140,30);
        layerStart.jbacktype.setBounds((int)((double)180/1700*width),(int)((double)900/1000*height),100,30);
        layerStart.buttonInsure.setBounds((int)((double)550/1700*width),(int)((double)450/1000*height),200,40);
        layerStart.buttonnotInsure.setBounds((int)((double)850/1700*width),(int)((double)450/1000*height),200,40);

        layerRules.textabout.setBounds((int)((double)600/1700*width),(int)((double)150/1000*height),(int)((double)600/1700*width),(int)((double)600/1000*height));
        layerRules.goback.setBounds((int)((double)850/1700*width),(int)((double)800/1000*height),(int)((double)160/1700*width),(int)((double)65/1000*height));

        layerSet.label1.setBounds((int)((double)640/1700*width),(int)((double)300/1000*height),180,30);
        layerSet.label2.setBounds((int)((double)590/1700*width),(int)((double)400/1000*height),180,30);
        layerSet.label3.setBounds((int)((double)640/1700*width),(int)((double)500/1000*height),180,30);
        layerSet.decksspinner.setBounds((int)((double)800/1700*width),(int)((double)300/1000*height),100,30);
        layerSet.playersspinner.setBounds((int)((double)800/1700*width),(int)((double)400/1000*height),100,30);
        layerSet.delayspinner.setBounds((int)((double)800/1700*width),(int)((double)500/1000*height),100,30);
        layerSet.goback.setBounds((int)((double)770/1700*width),(int)((double)600/1000*height),160,65);

        layerAbout.textabout.setBounds((int)((double)700/1700*width),(int)((double)150/1000*height),(int)((double)300/1700*width),(int)((double)350/1000*height));
        layerAbout.goback.setBounds((int)((double)800/1700*width),(int)((double)600/1000*height),(int)((double)160/1700*width),(int)((double)65/1000*height));

        if(layerStart.ismusic) layerStart.setIcon("images/Sound_on.png",layerStart.jmusic);
        else layerStart.setIcon("images/Sound_off.png",layerStart.jmusic);
        layerStart.setIcon("images/homepage.png",layerStart.jgoback);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonGroup.about){
            card.show(backgroundPanel,"about panel");

        }
        else if(e.getSource()==buttonGroup.set){
            card.show(backgroundPanel,"set panel");
        }
        else if(e.getSource()==buttonGroup.rules){
            card.show(backgroundPanel,"rules panel");
        }
        else if(e.getSource()==buttonGroup.start){
            layerStart.InitialPlayers((int)layerSet.playersspinner.getValue());
            backgroundPanel.img=new ImageIcon("images/bg.png").getImage();
            layerStart.numberOfDecks=(int)layerSet.decksspinner.getValue();
           layerStart.speed=(int)layerSet.delayspinner.getValue();
            for(int i=0;i<(int)layerSet.playersspinner.getValue();i++) {
                layerStart.jsums[i].setVisible(true);
                layerStart.jspinners[i].setVisible(true);
                layerStart.jmoney[i].setVisible(true);
            }
            layerStart.shoe.SetNumberOfDecks((int)layerSet.decksspinner.getValue());
            layerStart.shoe.Init();
            layerStart.shoe.Shuffle();
            card.show(backgroundPanel,"start panel");
        }
        else if(e.getSource()==layerAbout.goback){
            card.show(backgroundPanel,"buttons panel");

        }
        else if(e.getSource()==layerRules.goback){
            card.show(backgroundPanel,"buttons panel");
        }
        else if(e.getSource()==layerSet.goback){    //确认设置后
            card.show(backgroundPanel,"buttons panel");
            if((int)layerSet.playersspinner.getValue()==0)    //如果设置的时候玩家数为0，则默认设置为一位玩家
            layerSet.playersspinner.setValue(1);
        }
        else if(e.getSource()==layerStart.jmusic){
            if(layerStart.ismusic){layerStart.ismusic=false;layerStart.setIcon("images/Sound_off.png",layerStart.jmusic);}
            else {layerStart.ismusic=true;layerStart.setIcon("images/Sound_on.png",layerStart.jmusic);}
        }
        else if(e.getSource()==layerStart.jgoback){
            backgroundPanel.img=new ImageIcon("images/initial.png").getImage();
            card.show(backgroundPanel,"buttons panel");
        }
        else if(e.getSource()==layerStart.jdealbutton){           //执行deal操作
            layerStart.isstarted=true;
            layerStart.DealClick();
            layerStart.setBanks();
        }
        else if(e.getSource()==layerStart.jhitbutton) {
            layerStart.isdealRound=false;
            try {
                layerStart.flyCard(layerStart.CurrentPlayer().getLocation(),layerStart.speed);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            layerStart.HitClick();
        }
        else if(e.getSource()==layerStart.jstandbutton) {
            layerStart.isdealRound=false;
            layerStart.StandClick();
        }
        else if(e.getSource()==layerStart.jdoublebutton) {
            layerStart.isdealRound=false;
            layerStart.DoubleClick();
            layerStart.setBanks();
        }
        else if(e.getSource()==layerStart.jsplitbutton) {
            layerStart.isdealRound=false;
            layerStart.SpiltClick();
            layerStart.setBanks();
        }
        else if(e.getSource()==layerStart.buttonInsure) {
            layerStart.InsuranceClick();
            layerStart.setBanks();
        }
        else if(e.getSource()==layerStart.buttonnotInsure) {
            layerStart.NoInsuranceClick();
            layerStart.setBanks();
        }

    }
}

class BackgroundPanel extends JPanel {
    Image img;

    public BackgroundPanel(Image img) {
        this.img = img;
        this.setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);

    }
}

class ButtonGroup extends JPanel {
    JButton start,rules,set,about;

    public ButtonGroup() {
        this.setLayout(null);

        start = new JButton("START");
        rules = new JButton("RULES");
        set = new JButton("MYSET");
        about = new JButton("ABOUT");

      //  add(Box.createVerticalStrut(80));

        this.add(start);
        this.add(rules);
        this.add(set);
        this.add(about);
        this.setOpaque(false);

    }
}

class CardLayerStart extends JPanel{        //游戏界面层
    boolean ismusic=true;
    JButton jmusic,jgoback;
    JButton jdealbutton,jhitbutton,jstandbutton,jdoublebutton,jsplitbutton;

    JTextArea jmessage;
    JSpinner[] jspinners=new JSpinner[4];
    JTextField[] jmoney=new JTextField[4];
    JTextField[] jsecondsums=new JTextField[4];
    JTextField[] jsums=new JTextField[5];
    Point[] topleftpoints=new Point[5];
    Point cardboxpoint;
    String[] kabei=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    JSpinner jbacktype=new JSpinner(new SpinnerListModel(kabei));
    JLabel labelbacktype=new JLabel("BACKTYPE");
    JLabel cardback=new JLabel(new ImageIcon("card/Jan.jpg"));
    ImageIcon cardbackImage=new ImageIcon("card/Jan.jpg");
    JButton buttonInsure,buttonnotInsure;
    boolean isflying=false;
    boolean isstarted=false;
    int speed=4;
    boolean isdealRound=false;


    ///////////////////////////////////////**************************************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public Shoe shoe=new Shoe();
    private Player[] players;
    private Dealer dealer = new Dealer();
    private boolean showDealerDownCard;
    private boolean insurance = false;
    public int currentPlayer = -1;
    private Player.LabelType labelType = Player.LabelType.BothHands;
    private int playerNumber;
    private boolean endOfShoe;
    public int numberOfDecks;
    ///////////////////////////////////////**************************************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public CardLayerStart(){
        setOpaque(false);
        setLayout(null);

        jmusic=new JButton();jgoback=new JButton();
        jdealbutton=new JButton("DEAL");jhitbutton=new JButton("HIT");jstandbutton=new JButton("STAND");
        jdoublebutton=new JButton("DOUBLE");jsplitbutton=new JButton("SPLIT");
        buttonInsure=new JButton("INSURANCE");buttonnotInsure=new JButton("NO THANKS");

         for(int i=0;i<4;i++) jspinners[i]=new JSpinner();
        for(int i=0;i<4;i++) jmoney[i]=new JTextField();

        jmessage=new JTextArea();
        for(int i=0;i<4;i++)jsecondsums[i]=new JTextField("second"+i);
        topleftpoints[4]=new Point((int)((double)800/1700*InitialFrame.width),(int)((double)200/1000*InitialFrame.height));
        topleftpoints[0]=new Point((int)((double)120/1700*InitialFrame.width),(int)((double)460/1000*InitialFrame.height));
        topleftpoints[1]=new Point((int)((double)520/1700*InitialFrame.width),(int)((double)660/1000*InitialFrame.height));
        topleftpoints[2]=new Point((int)((double)1020/1700*InitialFrame.width),(int)((double)660/1000*InitialFrame.height));
        topleftpoints[3]=new Point((int)((double)1320/1700*InitialFrame.width),(int)((double)460/1000*InitialFrame.height));
        cardboxpoint=new Point((int)((double)1400/1700*InitialFrame.width),(int)((double)250/1000*InitialFrame.height));

        jmessage.setBackground(new Color(0x1CAE6D));jmessage.setFont(new Font("方正兰亭超细黑简体",Font.BOLD,30));

      for(int i=0;i<4;i++)jmoney[i].setEditable(false);
        for(int i=0;i<4;i++)jmoney[i].setText("2000");

        for(int i=0;i<5;i++){
            jsums[i]=new JTextField();
            jsums[i].setFont(new SetFont().getFont("fonts/FZLTCXHJW.TTF",15.0F));
            jsums[i].setEditable(false);
        }
        for(int i=0;i<4;i++){
            jsecondsums[i].setFont(new SetFont().getFont("fonts/FZLTCXHJW.TTF",20.0F));
            jsecondsums[i].setEditable(false);
        }
        for(int i=0;i<4;i++){
            jmoney[i].setFont(new SetFont().getFont("fonts/FZLTCXHJW.TTF",20.0F));
            jmoney[i].setEditable(false);
        }
        labelbacktype.setForeground(new Color(255,255,255));
        labelbacktype.setFont(new SetFont().getFont("fonts/FZLTCXHJW.TTF",20.0F));

        String[] wagers=new String[]{"10","20","30","40","50"};

     for(int i=0;i<4;i++)jspinners[i].setModel(new SpinnerListModel(wagers));

        //如果赌注大于余额，则降低赌注

        jspinners[0].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if(Integer.parseInt((String)jspinners[0].getValue())>Integer.parseInt(jmoney[0].getText()))
                        jspinners[0].setValue(jspinners[0].getPreviousValue());
                }
            });
        jspinners[1].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(Integer.parseInt((String)jspinners[1].getValue())>Integer.parseInt(jmoney[1].getText()))
                    jspinners[1].setValue(jspinners[1].getPreviousValue());
            }
        });
        jspinners[2].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(Integer.parseInt((String)jspinners[2].getValue())>Integer.parseInt(jmoney[2].getText()))
                    jspinners[2].setValue(jspinners[2].getPreviousValue());
            }
        });
        jspinners[3].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(Integer.parseInt((String)jspinners[3].getValue())>Integer.parseInt(jmoney[3].getText()))
                    jspinners[3].setValue(jspinners[3].getPreviousValue());
            }
        });


        jbacktype.addChangeListener(new ChangeListener() {     //更改卡背图片
            @Override
            public void stateChanged(ChangeEvent e) {
                cardbackImage=new ImageIcon("card/"+(String)jbacktype.getValue()+".jpg");
                cardback.setIcon(cardbackImage);
            }
        });

        add(jmusic);add(jgoback);
        add(jdealbutton);add(jhitbutton);add(jstandbutton);add(jdoublebutton);add(jsplitbutton);
        for(int i=0;i<4;i++){
            add(jspinners[i]);
            add(jmoney[i]);
        }
        add(buttonInsure);add(buttonnotInsure);

        add(jmessage);add(labelbacktype);add(jbacktype);
        for(int i=0;i<5;i++)add(jsums[i]);
        for(int i=0;i<4;i++)add(jsecondsums[i]);


        for(int i=1;i<4;i++)jsums[i].setVisible(false);
        for(int i=1;i<4;i++)jspinners[i].setVisible(false);
        for(int i=1;i<4;i++)jmoney[i].setVisible(false);
        for(int i=1;i<4;i++)jsecondsums[i].setVisible(false);
        jsecondsums[0].setVisible(false);
        buttonInsure.setVisible(false);buttonnotInsure.setVisible(false);
    }

    ///////////////////////////////////////**************************************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //初始化玩家
    public void InitialPlayers(int numOfPlayers) {
        players = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {

            switch (i) {
                case 0:  //玩家1
                    players[i]=new Player(topleftpoints[i],2000, Player.PlayerType.Human,new BasicSingleDeck(),new CountMethod(numberOfDecks));
                    break;
                case 1:
                    players[i]=new Player(topleftpoints[i],2000, Player.PlayerType.Human,new BasicSingleDeck(),new CountMethod(numberOfDecks));
                    break;
                case 2:
                    players[i]=new Player(topleftpoints[i],2000,Player.PlayerType.Human,new BasicSingleDeck(),new CountMethod(numberOfDecks));
                    break;
                case 3:
                    players[i]=new Player(topleftpoints[i],2000, Player.PlayerType.Human,new BasicSingleDeck(),new CountMethod(numberOfDecks));
                    break;
            }
        }
        this.repaint();
    }

    //玩家点击DEAL后的操作
    public void DealClick() {
        //重置初始化所有玩家
        for (Player player:players) {
            player.Reset();
        }

        //设置玩家是否保险，第二手牌没有，玩家可点击的按钮的状态
        insurance = false;
        SetSecondsumsVisiblity();

        SetButtonState(false);
        for(int i = 0; i < players.length; i ++) {
            players[i].SetWagerAfterDeal(Integer.parseInt((String)jspinners[i].getValue()));
            players[i].GetHands()[0].SetWager(Integer.parseInt((String)jspinners[i].getValue()));
        }

        showDealerDownCard = false;   //庄家一张暗牌
        dealer.Reset();   //庄家重置初始化

        currentPlayer = 0;

        //判断牌盒是否需要洗牌
        if (shoe.EndDeck()) {
            endOfShoe = false;
            this.repaint();

            Shuffle();
        }

        //遍历玩家，获得参与游戏的玩家的赌注
        for (Player player:players) {
            if (player.Active())
                player.GetWager();
        }

        labelType = Player.LabelType.BothHands;

        Card newCard = null;
        Card dealerCard = null;

        isdealRound = true;

        //游戏开始给玩家发2张牌
        for (int i = 0; i < 2; i ++) {
            for (Player player:players) {
                if (player.Active()) {
                    newCard = shoe.Next();
                    player.GetHands()[0].Add(newCard);
                    CountCard(newCard);
                    try {
                        flyCard(player.getLocation(),speed);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("飞牌出错！！");
                    }
                }
            }

            //给庄家发牌
            dealerCard = shoe.Next();
            dealer.dealerHand.Add(dealerCard);
            if (i == 1)
                CountCard(newCard);
            try {
                flyCard(topleftpoints[4],speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<players.length;i++){
            jspinners[i].setEnabled(false);
        }

        //庄家明牌是Ace，玩家可以选择是否保险
        if (dealer.dealerHand.cards[0].FaceValue().equals(Card.CardType.Ace)) {
            insurance = true;
            SetButtonState(false);
            this.repaint();
        }
        else if (dealer.dealerHand.IsBlackjack()) {   //庄家是Blackjack
            labelType = Player.LabelType.Outcome;
            currentPlayer = players.length;

            showDealerDownCard = true;
            this.repaint();
            CountCard(dealer.dealerHand.cards[0]);

            //遍历玩家找到Blackjack的玩家和庄家平局
            for (Player player:players) {
                if (player.Active()) {
                    for (Hand hand:player.GetHands()) {
                        if (hand.IsBlackjack())
                            player.Push(hand);
                    }
                }
            }
            this.repaint();
            SetButtonState(true);
        }
        else {
            this.repaint();

            //遍历所有玩家直到找到不是21点的玩家
            int handTotal = 0;
            do {
                handTotal = CurrentPlayer().CurrentHand().Total();
                if (handTotal == 21)
                    NextPlayer();
            } while (handTotal == 21 && CurrentPlayer() != null);

            SetButtonState(false);
        }

        if (shoe.EndDeck()) {
            for (Player player:players) {
                if (player.Active())
                    player.ResetCount(numberOfDecks);
            }
        }

    }

    //设置玩家账户金额的控件的数值的赋值
    public void setBanks(){
        for(int i=0;i<players.length;i++){
            jmoney[i].setText(Integer.toString(players[i].GetBank()));
        }
    }

    //给现实玩家点数和输赢结果的控件的文本内容的赋值
    public void setLabel() {
        for(int i=0;i<players.length;i++){
            if (labelType.equals(Player.LabelType.Outcome)){
                jsums[i].setText(players[i].GetHands()[0].Outcome(dealer.GetHand(), numberOfDecks).toString());
                jsecondsums[i].setText(players[i].GetHands()[1].Outcome(dealer.GetHand(), numberOfDecks).toString());
            }
            else {
                jsums[i].setText(players[i].GetHands()[0].Label(true));
                jsecondsums[i].setText(players[i].GetHands()[1].Label(true));
            }
        }
    }

    //设置玩家分牌后第二手牌可见
    public void SetSecondsumsVisiblity(){
        for(int i=0;i<players.length;i++){
            if(players[i].GetHands()[1].Count()>0)jsecondsums[i].setVisible(true);
            else jsecondsums[i].setVisible(false);
        }
    }

    //玩家点击HIT要牌按钮的操作
    public void HitClick() {
        if (CurrentPlayer() != null && !insurance) {
            int handTotal = 0;
            //要一张牌
            NextCard();
            //遍历直到找到玩家比21点小
            do {
                handTotal = CurrentPlayer().CurrentHand().Total();
                if (handTotal >= 21) {
                    if (CurrentPlayer().LastHand()) {
                        NextPlayer();
                        if (CurrentPlayer() != null)
                            handTotal = CurrentPlayer().CurrentHand().Total();
                    }
                    else {
                        //玩家已经分牌
                        NextHand();
                        handTotal = CurrentPlayer().CurrentHand().Total();
                    }
                }
            } while (handTotal >= 21 && CurrentPlayer() != null);
        }
    }

    //玩家点击Stand停牌按钮的操作
    public void StandClick() {
        int handTotal = 0;

        if (CurrentPlayer() != null && !insurance) {
            if (CurrentPlayer().LastHand())
                NextPlayer();
            else
                //玩家分过牌
                NextHand();
            //确保我们没有跳过上一个玩家
            if (CurrentPlayer() != null) {
                //遍历直到找到小于21点的玩家
                do {
                    handTotal = CurrentPlayer().CurrentHand().Total();
                    if (handTotal >= 21) {
                        if (CurrentPlayer().LastHand()) {
                            NextPlayer();
                            if (CurrentPlayer() != null)
                                handTotal = CurrentPlayer().CurrentHand().Total();
                        }
                        else {
                            //玩家分过牌
                            NextHand();
                            handTotal = CurrentPlayer().CurrentHand().Total();
                        }
                    }
                } while (handTotal >= 21 && CurrentPlayer() != null);
            }
        }
    }

    public void DoubleClick() {
        if (CurrentPlayer() != null && !insurance) {
            if (CurrentPlayer().DoubleDown(CurrentPlayer().CurrentHand())) {
                //再要一张牌
                NextCard();

                if (CurrentPlayer().LastHand())
                    NextPlayer();
                else
                    NextHand();
            }
        }
    }

    //玩家点击Spilt分牌按钮的操作
    public void SpiltClick() {
        //确保分牌的是玩家而不是庄家,并且不是保险的回合
        if (CurrentPlayer() != null && !insurance) {
            if (CurrentPlayer().Spilt(CurrentPlayer().CurrentHand())) {
                this.repaint();
                //分牌Ace时

                if (CurrentPlayer().CurrentHand().cards[0].FaceValue() == Card.CardType.Ace) {
                    //玩家只能再拿一张牌
                    NextCard();
                    NextHand();
                    NextPlayer();
                }
                else {
                    //正常分牌，给当前这手牌增加一张牌
                    labelType = Player.LabelType.DrawToHand;
                    NextCard();
                    //如果拿到21点，自动行动到下一手牌
                    if (CurrentPlayer().CurrentHand().Total() == 21) {
                        NextCard();
                        //如果第二手牌也是21点，移动到下一手牌
                        if (CurrentPlayer().CurrentHand().Total() == 21)
                            NextPlayer();
                    }
                }
                SetSecondsumsVisiblity();
            }
        }
    }

    //玩家点击要保险按钮的操作
    public void InsuranceClick() {
        if (CurrentPlayer() != null && insurance) {
            CurrentPlayer().SetInsurance(true);
            NextPlayer();
        }
    }

    //玩家点击不要保险按钮的操作
    public void NoInsuranceClick() {
        if (CurrentPlayer() != null && insurance) {
            CurrentPlayer().SetInsurance(false);
            NextPlayer();
        }
    }

    //设置玩家可用按钮的可视和可用
    public void SetButtonState(boolean dealEnabled) {
        //在保险的时候，只有保险相关的按钮才可以看见
        if (insurance) {
            buttonInsure.setVisible(true);
            buttonnotInsure.setVisible(true);
            jdealbutton.setVisible(false);
            jstandbutton.setVisible(false);
            jhitbutton.setVisible(false);
            jsplitbutton.setVisible(false);
            jdoublebutton.setVisible(false);
        }
        else {
            buttonInsure.setVisible(false);
            buttonnotInsure.setVisible(false);
            jdealbutton.setVisible(true);
            jstandbutton.setVisible(true);
            jhitbutton.setVisible(true);
            jsplitbutton.setVisible(true);
            jdoublebutton.setVisible(true);
        }

        jdealbutton.setEnabled(dealEnabled);

        //设置游戏中玩家操作的按钮的可访问
        if (CurrentPlayer() != null) {
            if (insurance) {
                buttonInsure.setEnabled(true);
                buttonnotInsure.setEnabled(true);
            }
            else {
                jhitbutton.setEnabled(true);
                jstandbutton.setEnabled(true);
                jdoublebutton.setEnabled(CurrentPlayer().CanDouble(CurrentPlayer().CurrentHand()));
                jsplitbutton.setEnabled(CurrentPlayer().CanSpilt());
            }
        }
    }

    public void CountCard(Card newCard) {
        for (Player player:players) {
            if (player.Active())
                player.CountCard(newCard);
        }
    }

    //获得当前操作的玩家
    public Player CurrentPlayer() {
        if (currentPlayer >= 0 && currentPlayer < players.length /*&& players[currentPlayer].Active()*/)
            return players[currentPlayer];
        else
            return null;
    }

    //移动到下一个玩家
    public void NextPlayer() {
        //当前玩家的两手牌都可以看见（如果有两手牌的话）
        labelType = Player.LabelType.BothHands;

        do {
            currentPlayer ++;
            this.repaint();

            if (CurrentPlayer() == null) {
                if (insurance && !dealer.dealerHand.IsBlackjack()) {
                    for (Player player:players) {
                        if (player.IsInsurance()) {
                            player.LostInsurance();
                            player.SetInsurance(false);
                        }
                    }
                    insurance = false;
                    currentPlayer = 0;
                    SetButtonState(false);

                    this.repaint();
                }
                else {
                    showDealerDownCard = true;
                    this.repaint();
                    CountCard(dealer.dealerHand.cards[0]);
                    //只给没有爆掉的玩家
                    boolean hitToDealer = false;
                    for (Player player:players) {
                        if (player.Active()) {
                            for (Hand hand:player.GetHands()) {
                                if (hand.Total() > 0 && hand.Total() <= 21 && !hand.IsBlackjack()) {
                                    hitToDealer = true;
                                    break;
                                }
                            }
                        }
                        if (hitToDealer) {
                            break;
                        }
                    }
                    if (hitToDealer) {
                        while (dealer.Total() < 17) {
                            Card dealerCard = shoe.Next();
                            dealer.AddCard(dealerCard);
                            this.repaint();

                            CountCard(dealerCard);
                        }
                    }

                    labelType = Player.LabelType.Outcome;

                    for (Player player:players) {
                        if (player.Active()) {
                            if (insurance && dealer.dealerHand.IsBlackjack())
                                player.WonInsurance();
                            else
                                player.LostInsurance();

                            for (Hand hand:player.GetHands()) {
                                switch (hand.Outcome(dealer.dealerHand, player.NumberOfHands())) {
                                    case Won:
                                        player.Won(hand);
                                        break;
                                    case Lose:
                                        break;
                                    case Push:
                                        player.Push(hand);
                                        break;
                                    case Blackjack:
                                        player.Blackjack(hand);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }

                    insurance = false;
                    SetButtonState(true);

                    if (shoe.EndDeck()) {
                        for (Player player:players) {
                            if (player.Active())
                                player.ResetCount(numberOfDecks);
                        }
                    }

                    this.repaint();
                }
            }
            else
                SetButtonState(false);

        } while (CurrentPlayer() != null && CurrentPlayer().CurrentHand().Total() >= 21 && !insurance);
    }

    //玩家再给一张牌
    public void NextCard() {
        Card newCard = shoe.Next();
        CountCard(newCard);
        CurrentPlayer().CurrentHand().Add(newCard);
        this.repaint();
        SetButtonState(false);
    }

    //下一手牌
    public void NextHand() {
        CurrentPlayer().NextHand();
        NextCard();
        SetButtonState(false);
    }

    //洗牌
    public void Shuffle() {
        if (ismusic){
            Sound.PlayerAudio("shuffle");
        }
        shoe.Shuffle();
        for (Player player:players) {
            if (player.Active()){}
        }
    }

    ///////////////////////////////////////**************************************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\////////////////////////////////////////////



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D=(Graphics2D) g;
        g.setColor(Color.white);g2D.setStroke(new BasicStroke(4f));

        g.setColor(Color.white);
        g.drawOval((int) ((double) 100 / 1700 * InitialFrame.width), (int) ((double) 470 / 1000 * InitialFrame.height), 150, 150);
        g.drawOval((int) ((double) 500 / 1700 * InitialFrame.width), (int) ((double) 670 / 1000 * InitialFrame.height), 150, 150);
        g.drawOval((int) ((double) 1000 / 1700 * InitialFrame.width), (int) ((double) 670 / 1000 * InitialFrame.height), 150, 150);
        g.drawOval((int) ((double) 1300 / 1700 * InitialFrame.width), (int) ((double) 470 / 1000 * InitialFrame.height), 150, 150);


        if(isstarted) {
            if (true) {
                g.setColor(Color.green);
                if (players[0] == CurrentPlayer())
                    g.setColor(Color.red);
                g.drawOval((int) ((double) 100 / 1700 * InitialFrame.width), (int) ((double) 470 / 1000 * InitialFrame.height), 150, 150);
            }
            if (players.length > 1) {
                g.setColor(Color.green);
                if (players[1] == CurrentPlayer())
                    g.setColor(Color.red);
                g.drawOval((int) ((double) 500 / 1700 * InitialFrame.width), (int) ((double) 670 / 1000 * InitialFrame.height), 150, 150);
            }
            if (players.length > 2) {
                g.setColor(Color.green);
                if (players[2] == CurrentPlayer())
                    g.setColor(Color.red);
                g.drawOval((int) ((double) 1000 / 1700 * InitialFrame.width), (int) ((double) 670 / 1000 * InitialFrame.height), 150, 150);
            }
            if (players.length > 3) {
                g.setColor(Color.green);
                if (players[3] == CurrentPlayer())
                    g.setColor(Color.red);
                g.drawOval((int) ((double) 1300 / 1700 * InitialFrame.width), (int) ((double) 470 / 1000 * InitialFrame.height), 150, 150);
            }

            if (!isflying ) {      //如果此时牌没有在飞,刷新所有卡牌
                for (int i = 0; i < players.length; i++) {   //先刷新玩家的牌
                    for (int m = 0; m < players[i].GetHands()[0].Count(); m++) {     //绘制该玩家第一手牌
                        g.drawImage(players[i].GetHands()[0].getCards()[m].GetImage(), topleftpoints[i].x + m * 20, topleftpoints[i].y + m * 20, this);
                    }
                    for (int n = 0; n < players[i].GetHands()[1].Count(); n++) {        //绘制该玩家第二手牌
                        g.drawImage(players[i].GetHands()[1].getCards()[n].GetImage(), topleftpoints[i].x + 110 + n * 20, topleftpoints[i].y + n * 20, this);
                    }
                }
                //再刷新dealer的牌

                    if (!showDealerDownCard) {
                       // if (dealer.GetHand().Count() == 2) {
                            g.drawImage(dealer.GetHand().getCards()[0].GetImage(), topleftpoints[4].x, topleftpoints[4].y, this);
                            g.drawImage(cardbackImage.getImage(), topleftpoints[4].x + 20, topleftpoints[4].y + 20, this);

                        for(int i=0;i<players.length;i++){
                            jspinners[i].setEnabled(false);
                        }
                        jsums[4].setText("");
                    } else {
                        jsums[4].setText(dealer.GetHand().Label(true));
                        for (int i = 0; i < dealer.GetHand().Count(); i++)
                            g.drawImage(dealer.GetHand().getCards()[i].GetImage(), topleftpoints[4].x + 20 * i, topleftpoints[4].y + 20 * i, this);
                        for(int i=0;i<players.length;i++){
                            jspinners[i].setEnabled(true);
                        }
                    }
                    setBanks();
                    setLabel();
            }
        }
    }


    public void flyCard(Point target,int speed) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isflying=true;
                Point temp = new Point(cardboxpoint.x, cardboxpoint.y);
                Double slope=(target.y-(double)cardboxpoint.y)/((double)cardboxpoint.x-target.x);
                add(cardback);
                try{
                    while (temp.x>target.x) {
                        cardback.setBounds(temp.x, temp.y, 71, 96);
                        repaint();
                        Thread.sleep(50/speed);
                        temp.x-=50;
                        temp.y+=(double)50*slope;
                    }
                }catch (InterruptedException e){
                        System.out.println("Wrong!flyCard");
                }
                remove(cardback);
                isflying=false;
                repaint();
            }
        }
        ).start();


    }

    public static void setIcon(String file, JButton iconButton) {     //把图片加载到按钮上并自动调整大小
        ImageIcon icon = new ImageIcon(file);
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon = new ImageIcon(temp);
        iconButton.setIcon(icon);
    }
}



class CardLayerRules extends JPanel{
    JScrollPane textabout;
    JButton goback;
    JTextArea textaboutarea;
    public CardLayerRules(){
            setLayout(null);
            textaboutarea=new JTextArea();
            textabout=new JScrollPane(textaboutarea);
            textabout.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            textaboutarea.setFont(new SetFont().getFont("fonts/simkai.ttf",25.0F));
            textaboutarea.setLineWrap(true);
            textaboutarea.setWrapStyleWord(true);

            showFileDescribe("textfile/BlackjackRules.txt");
            textaboutarea.setBackground(new Color(0xF0FE66));
            textaboutarea.setEditable(false);
            add(textabout);

            goback=new JButton("BACK");
            add(goback);

            setOpaque(false);
    }

    //将文本数据读入到textaboutarea
    private void showFileDescribe(String filename){
        FileInputStream in= null;
        try {
            in = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputStreamReader ir=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(ir);
        try {
            String line=null;
            while ((line=br.readLine())!=null)
            textaboutarea.append(line+'\n');
        } catch (IOException e) {
           // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class CardLayerSet extends JPanel{         //设置层
    JLabel label1,label2,label3;
    JSpinner decksspinner,playersspinner,delayspinner;
    JButton goback;
    public CardLayerSet(){
         label1=new JLabel("DECKS:");label2=new JLabel("PLAYERS:");label3=new JLabel("SPEED:");
        decksspinner=new JSpinner();
        delayspinner=new JSpinner();
        playersspinner=new JSpinner();
        goback=new JButton("FINISH");
        setLayout(null);

        decksspinner.setModel(new SpinnerNumberModel(1,1,6,1));
        delayspinner.setModel(new SpinnerNumberModel(4,1,4,1));
        playersspinner.setModel(new SpinnerNumberModel(1,1,4,1));


        add(label1);add(decksspinner);
        add(label2);add(playersspinner);
        add(label3);add(delayspinner);
        add(goback);
        setOpaque(false);
    }
}

class CardLayerAbout extends JPanel{             //关于层
    JTextArea textabout;
    JButton goback;
    public CardLayerAbout(){
        setLayout(null);
        textabout=new JTextArea();
        textabout.setFont(new SetFont().getFont("fonts/simkai.ttf",20.0F));
        textabout.setLineWrap(true);
        textabout.setText("\r\n     ABOUT\r\n\nversion v1.0\r\n\nauthor：谢斌\r\n        谢海韵");
        textabout.setBackground(new Color(0xD0DB2E));
        textabout.setEditable(false);
        add(textabout);

        goback=new JButton("BACK");
        add(goback);
        setOpaque(false);

    }

}

class SetFont {
    Font font=null;
    public Font getFont(String fileroute,float size){
        try {
            font=Font.createFont(Font.TRUETYPE_FONT,new File(fileroute));
            font=font.deriveFont(size);//SIZE
            font=font.deriveFont(Font.BOLD);//Style
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            JOptionPane.showMessageDialog(null, "加载字体出错", "系统消息",JOptionPane.INFORMATION_MESSAGE);
            font=new Font("Verdana",Font.BOLD,18);
        }
        return font;
    }

}
