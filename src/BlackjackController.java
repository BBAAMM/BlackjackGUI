import javax.swing.*;

import java.awt.*;
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
	private JLabelVanishing finishLabel;
	public BlackjackController(Dealer d, int _player_num) {
		finishLabel = new JLabelVanishing("감사합니다", backPanel, true, 1);
		mainScreen();
		dealer = d;
		// 플레이어 수 받아옴.
		player_num = _player_num;
		name = new String[player_num];
		hand_player = new HumanPlayer[player_num];
		gui_dealer = new DealerGUI();

		getPlayerNames();
		hand_dealer = new ComputerPlayer(8, gui_dealer);
		
		//GUI setting
		ImageIcon background = new ImageIcon("image/background/casino_table_4k.jpg");
		backPanel = new JPanel(null);
		backPanel.setBounds(0,0,1000,600);
		
		JLabel backLabel = new JLabel(background);
		backLabel.setBounds(0,0, 1000, 600);
		add(gui_dealer);
		backPanel.add(backLabel);
		add(backPanel);
		setVisible(true);
	}
	
	public void manageBlackjack() {

		// play blackjack
		while(true) {
			if(cnt == player_num){
				break;
			}

			dealer.dealOneTo(hand_dealer);
			dealer.dealOneTo(hand_dealer);


			for(int i = 0; i<player_num; i++) {
				if(hand_player[i] != null) {
					dealer.dealOneTo(hand_player[i]);
					dealer.dealOneTo(hand_player[i]);
				}
			}

			for(int i = 0; i < player_num; i++) {
				dealer.dealTo(hand_player[i]);
			}
			dealer.dealTo(hand_dealer);

			for(int i=0; i<player_num; ++i){
				if(hand_player[i]!=null){
					int player_score = hand_player[i].totalScore();
					int dealer_score = hand_dealer.totalScore();
					if(hand_player[i].getCard_count()==2 && player_score==21) hand_player[i].setState(0);
					if(player_score<=21 && (dealer_score>21 || player_score>dealer_score)) hand_player[i].setState(1);
					else if(player_score<dealer_score || player_score>21) hand_player[i].setState(2);
					else hand_player[i].setState(3);
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

			for(int i = 0; i < player_num; i++){
				if(hand_player[i] != null) {
					gui_player[i].setChipLabel(hand_player[i].Chips());
				}
			}

			oneMoreTime();
		}
		backPanel.removeAll();
		getContentPane().removeAll();
		JLabelVanishing finishLabel = new JLabelVanishing("감사합니다", backPanel, true,2);
		finishLabel.setFont(new InstallFont().boldFont(60f));
		finishLabel.setHorizontalAlignment(JLabel.CENTER);
		finishLabel.setBounds(300, 250, 400, 100);
		backPanel.add(finishLabel);
		add(backPanel);
		new Thread(finishLabel).start();
	}
	public void getPlayerNames(){
		// 플레이어의 이름을 각각 받음.
		for(int i = 0; i < player_num; i++) {
			name[i] = JOptionPane.showInputDialog((i+1) + "번째 사람의 이름을 입력해 주세요.");
			if(name[i]==null) name[i]=Integer.toString(i+1);

			if (i == 0){
				gui_player[i] = new PlayerLeftGUI(name[i]);
			}
			else if (i == 1){
				gui_player[i] = new PlayerMiddleGUI(name[i]);
			}
			else if (i == 2){
				gui_player[i] = new PlayerRightGUI(name[i]);
			}
			hand_player[i] = new HumanPlayer(8, name[i], gui_player[i]);
			add(gui_player[i]);
		}
	}

	private void oneMoreTime() {
		for(int i = 0; i < player_num; i++) {
			if(hand_player[i] != null) {
				int response = JOptionPane.showConfirmDialog(null, name[i] + "님 한 번 더 게임 하시겠습니까?");


				if(!(response==JOptionPane.OK_OPTION)) {
					hand_player[i].reset();
					gui_player[i].reset();
					hand_player[i] = null;
					name[i] = null;
					cnt++;
				}
			}
		}
		for(int i = 0; i < player_num; i++) {
			if(hand_player[i] != null) {
				hand_player[i].reset();
				gui_player[i].reset();
			}
		}
		hand_dealer.reset();
		gui_dealer.reset();
	}
	
	public void mainScreen() {
		setTitle("BlackJack");
		getRootPane().setPreferredSize(new Dimension(1000, 600));
		pack();
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
