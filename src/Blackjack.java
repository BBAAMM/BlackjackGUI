public class Blackjack {
	public static void main(String[] args) {
		new SelectNumberofPlayer(new Blackjack());
//		new BlackjackController(new Dealer(), 1).manageBlackjack();
	}
	public void startController(int _num){
		Dealer d = new Dealer();
		new BlackjackController(d, _num).manageBlackjack();
	}
}