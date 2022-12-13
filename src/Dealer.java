public class Dealer {

    private CardDeck deck;

    public Dealer() {
        deck = new CardDeck();
    }

    public void dealTo(CardPlayerBehavior p) {
        if(p != null) {
            while (p.wantsACard()) {
                Card c = deck.newCard();
                if(!p.receiveCard(c)) System.out.println("카드를 받지 못했습니다.");
                else System.out.println(c.suit() + " " + c.origin_rank());
            }
        }
    }
    
    public void dealOneTo(CardPlayerBehavior p) {
        if(p != null) {
            Card c = deck.newCard();
            if(!p.receiveCard(c)) System.out.println("카드를 받지 못했습니다.");
        }
    }
}