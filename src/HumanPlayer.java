import javax.swing.*;
import java.awt.*;

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
        chips = 10;
        name = n;
    }
    public String getName() { return name;}

    public boolean wantsACard() {
    	if(totalScore()>21) return false;
        int response = JOptionPane.showConfirmDialog(null, name+ "님, 한장 더 드릴까요?", "Do you want a Card?", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }
    public void youWinBlackjack() {
        getGui().getStateLabel().setForeground(new Color(246, 190, 0));
        getGui().getStateLabel().setText("BLACKJACK");
        chips+=2;
    }
    public void youWin() {
        getGui().getStateLabel().setForeground(Color.white);
        getGui().getStateLabel().setText("WIN");
        chips+=1;
    }
    
    public void youLose() {
        getGui().getStateLabel().setForeground(new Color(120, 0, 0));
        getGui().getStateLabel().setText("LOSE");
        chips-=1;
    }
    
    public void youDraw() {
        getGui().getStateLabel().setForeground(Color.gray);
        getGui().getStateLabel().setText("DRAW");
    }
    
    public int Chips() { return chips; }
}