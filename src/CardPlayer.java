import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class CardPlayer implements CardPlayerBehavior {

    private Card[] hand; // 갖고 있는 카드
    private int card_count; // 갖고 있는 카드의 장 수
    
    private PlayerGUI gui;

    /** Constructor CardPlayer - max_cards 카드를 수용가능한 Card 배열 객체를 만들어 CardPlayer 생성
     * @param max_cards - 들고 있을 수 있는 카드의 최대 장수 */
    public CardPlayer(int max_cards, PlayerGUI _gui) {
        hand = new Card[max_cards];
        gui = _gui;
        card_count = 0;
    }

    /** wantsACard - 카드 한 장을 받겠는지 답한다.
     * @return 카드를 받고 싶으면 true, 아니면 false */
    public abstract boolean wantsACard();

    /** receiveCard - 카드를 한장 받는다. 한도(배열 hand의 크기)를 초과하면 받을 수 없다.
     * @param c - 카드
     * @return 성공적으로 받았으면 true, 그렇지 않으면 false */
    public boolean receiveCard(Card c) {
        if (card_count < hand.length) {
            hand[card_count] = c;
            gui.putCardImg(getCardImage(c.rank(), c.suit()));
            if(gui.getNameLabel().getText().equals("Dealer") && card_count==0) {
            	gui.hideCardImg(0);
            }
            gui.sortCardPanel();
            card_count += 1;
            return true;
        }
        else
            return false;
    }
    
    public ImageIcon getCardImage(int rank, String suit) {
    	String card_path = "image/card_deck/" + rank + "_of_" + suit + ".png";
    	ImageIcon icon = new ImageIcon(card_path);
    	Image img = icon.getImage();
    	img = img.getScaledInstance(60, 90, Image.SCALE_SMOOTH);
    	return new ImageIcon(img);
    }
    
    /** hand - 들고 있는 카드를 내준다.
     * @return 들고 있는 카드 전체  */
    public Card[] hand() {
    	Card[] cards = new Card[card_count];
    	for(int i=0; i<card_count; ++i) {
    		cards[i] = hand[i];
    	}
    	return cards;
    }
    
    public int totalScore() {
    	int score = 0, ace_num = 0, card_rank;
    	for(int i=0; i<card_count; ++i) {
    		card_rank = hand[i].rank();
    		if(card_rank==Card.ACE) {
    			ace_num+=1;
    			card_rank=11;
    		}
    		else if(card_rank>10) card_rank=10;
    		score += card_rank;
    	}
    	for(int i=0; i<ace_num; ++i) {
    		if(score>21) {
    			score-=10;
    		}
    	}
    	return score;
    }
    public int getCard_count(){
        return card_count;
    }
    public void reset() {
    	for(int i=0; i<card_count; ++i) {
    		hand[i]=null;
    	}
    	card_count=0;
    }
    public PlayerGUI getGui(){
        return gui;
    }
}