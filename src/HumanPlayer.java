import javax.swing.*;

public class HumanPlayer extends CardPlayer {
	
	private String name;
	private int chips;
    private int state;

    public void setState(int num) {
        state = num;
    }

    public int getState() {
        return state;
    }

    public HumanPlayer(int max_cards, String n, PlayerGUI _gui) {
        super(max_cards, _gui);
        chips = 0;
        name = n;
    }
    public String getName() { return name;}

    public boolean wantsACard() {
    	if(totalScore()>21) return false;
        int response = JOptionPane.showConfirmDialog(null, name+ "님, 한장 더 드릴까요?", "Do you want a Card?", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }
    public void youWinBlackjack(String name) {
        chips+=2;
    }
    public void youWin(String name) {
        chips+=1;
    }
    
    public void youLose(String name) {
        chips-=1;
    }
    
    public void youDraw(String name) {
    }
    
    public int Chips() { return chips; }
}