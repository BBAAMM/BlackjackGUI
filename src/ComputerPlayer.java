public class ComputerPlayer extends CardPlayer {

    public ComputerPlayer(int max_cards, PlayerGUI _gui) {
        super(max_cards, _gui);
    }

    public boolean wantsACard() {
    	return totalScore()<=16;
    }

}