public class Blackjack {
	public static void main(String[] args) {
		Dealer d = new Dealer();
		new BlackjackController(d).manageBlackjack();
	}
}