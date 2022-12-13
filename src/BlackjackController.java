import javax.swing.*;

import java.awt.Dimension;
import java.awt.Image;
import java.util.*;

public class BlackjackController extends JFrame{
	private Dealer dealer;
	private HumanPlayer[] hand_player;
	private ComputerPlayer hand_dealer;
	
	private int player_num;
	private String name[];
	private int cnt = 0;
	//GUI
	private JPanel backPanel;
	private PlayerGUI gui_dealer;
	private PlayerGUI[] gui_player = new PlayerGUI[3];
	
	public BlackjackController(Dealer d) {
		dealer = d;
		// 플레이어 수 받아옴.
		player_num = Integer.parseInt(JOptionPane.showInputDialog("How mamy user?"));
		name = new String[player_num];
		hand_player = new HumanPlayer[player_num];
		gui_dealer = new DealerGUI();
		
		// 플레이어의 이름을 각각 받음.
		for(int i = 0; i < player_num; i++) {
			name[i] = JOptionPane.showInputDialog("What's your name Player_"+(i+1) +" ?");

			gui_player[i] = new PlayerMiddleGUI(name[i]);
			hand_player[i] = new HumanPlayer(8, name[i], gui_player[i]);
		}
		hand_dealer = new ComputerPlayer(8, gui_dealer);
		
		//GUI setting
		mainScreen();
		ImageIcon background = new ImageIcon("image/background/casino_table_4k.jpg");
		backPanel = new JPanel();
		backPanel.setBounds(0,0,1000,800);
		backPanel.setLayout(null);
		
		JLabel backLabel = new JLabel(background);
		backLabel.setBounds(0,0, 1000, 600);
		add(gui_player[0]);
		add(gui_dealer);
		backPanel.add(backLabel);
		add(backPanel);
		setVisible(true);
		setResizable(false);
	}
	
	public void manageBlackjack() {
		
		// play blackjack
		while(true) {
			if(cnt == player_num)
				break;

			dealer.dealOneTo(hand_dealer);
			dealer.dealOneTo(hand_dealer);
			

			for(int i = 0; i<player_num; i++) {
				if(hand_player[i] != null) {
					dealer.dealOneTo(hand_player[i]);
					dealer.dealOneTo(hand_player[i]);
				}
			}

			for(int i = 0; i < player_num; i++) {
				if(hand_player[i] != null) {
					if(hand_player[i].totalScore()==21)
						hand_player[i].setState(0);
					else {
						dealer.dealTo(hand_player[i]);

						dealer.dealTo(hand_dealer);

						int player_score = hand_player[i].totalScore();
						int dealer_score = hand_dealer.totalScore();

						if(player_score<=21 && (dealer_score>21 || player_score>dealer_score)) hand_player[i].setState(1);
						else if(player_score<dealer_score || player_score>21) hand_player[i].setState(2);
						else hand_player[i].setState(3);
					}
				}
			}
			
			gui_dealer.revealCardImg(0);
			for(int i = 0; i < player_num; i++) {
				if(hand_player[i] != null) {
					if(hand_player[i].getState() == 0) hand_player[i].youWinBlackjack(name[i]);
					else if(hand_player[i].getState() == 1) hand_player[i].youWin(name[i]);
					else if(hand_player[i].getState() == 2) hand_player[i].youLose(name[i]);
					else if(hand_player[i].getState() == 3) hand_player[i].youDraw(name[i]);
				}
			}

			System.out.println("---------------------");
			for(int i = 0; i < player_num; i++){
				if(hand_player[i] != null) {
					System.out.println(name[i]+"'s Chips : " + hand_player[i].Chips());
				}
			}
			System.out.println("---------------------");

			for(int i = 0; i < player_num; i++) {
				if(hand_player[i] != null) {
					hand_player[i].reset();
					gui_player[i].reset();
				}
			}

			hand_dealer.reset();
			gui_dealer.reset();

			oneMoreTime();
		}
	}

	private void oneMoreTime() {
		for(int i = 0; i < player_num; i++) {
			if(hand_player[i] != null) {
				int response = JOptionPane.showConfirmDialog(null, name[i] + "님 한 번 더 게임 하시겠습니까?");
				
				if(!(response==JOptionPane.OK_OPTION)) {
					hand_player[i] = null;
					name[i] = null;
					cnt++;
				}
			}
		}
	}
	
	public void mainScreen() {
		setTitle("BlackJack");
		getRootPane().setPreferredSize(new Dimension(1000, 600));
		pack();
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
    public ImageIcon getCardImage(String rank, String suit) {
    	String card_path = "image/card_deck/" + rank + "_of_" + suit + ".png";
    	ImageIcon icon = new ImageIcon(card_path);
    	Image img = icon.getImage();
    	img = img.getScaledInstance(60, 90, Image.SCALE_SMOOTH);
    	return new ImageIcon(img);
    }
    
    public void cardMove(JComponent comp, int x2, int y2) {
    	try {
    		double distance = Math.sqrt(Math.pow(x2-comp.getX(),2)+Math.pow(y2-comp.getY(),2));
    		double sinX = (y2-comp.getY())/distance;
    		double cosX = (x2-comp.getX())/distance;
    		double x = comp.getX();
    		double y = comp.getY();
    		long beforeTime = System.currentTimeMillis();
    		
    		while(true) {
        		Thread.sleep(17);
        		if(System.currentTimeMillis()-beforeTime>=10/2.75*distance) {
        			comp.setLocation(x2, y2);
        			break;
        		}
        		else {
        			x+=5*cosX;
        			y+=5*sinX;
        		}
        		comp.setLocation((int)x, (int)y);
        		repaint();
    		}
    	}
    	catch (Exception e){
    		System.out.println("error");
    	}
    }

}
