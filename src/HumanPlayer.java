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
    public String yourName() { return name;}

    public boolean wantsACard() {
    	if(totalScore()>21) return false;
        String response = JOptionPane.showInputDialog("한장 더 드릴까요? (Y/N)");
        return response.equals("Y")||response.equals("y");
    }
    public void youWinBlackjack(String name) {
            JOptionPane.showMessageDialog(null, name +"님은"+"블랙잭으로 이겼습니다.");
            chips+=2;
    }
    public void youWin(String name) {
            JOptionPane.showMessageDialog(null, name +"님은"+"이겼습니다.");
            chips+=1;
    }
    
    public void youLose(String name) {
            JOptionPane.showMessageDialog(null, name +"님은"+" 졌습니다.");
            chips-=1;
    }
    
    public void youDraw(String name) {
            JOptionPane.showMessageDialog(null, name +"님은"+"비겼습니다.");
    }
    
    public int Chips() { return chips; }
}