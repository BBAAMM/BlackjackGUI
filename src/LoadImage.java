import javax.swing.ImageIcon;

public class LoadImage {
    private String [] arr_rank = {"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
    private String [] arr_suit = {"clubs","spades","hearts","diamonds"};
    private String path;
    
    public ImageIcon getCardImage(String rank, String suit) {
    	String card_path = "/image/card_deck" + rank + "_of_" + suit + ".png";
    	return new ImageIcon(card_path);
    }
}