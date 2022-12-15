public class Blackjack {
	public static void main(String[] args) {
		new SelectNumberofPlayer(new Blackjack());
	}
	public void startController(int _num){
		Dealer d = new Dealer();
		new BlackjackController(d, _num).manageBlackjack();
	}
}