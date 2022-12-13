import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerGUI extends JPanel{
	private JLabel nameLabel;
	private JLabel[] cardLabels;
	private ImageIcon preIcon;
	private JLabel chipLabel;
	private JPanel cardPanel;
	private int cardCnt = 0;
	private int chips = 0;
	
	public PlayerGUI(String _name) {
		nameLabel = new JLabel(_name);
		cardLabels = new JLabel[8];
		cardPanel = new JPanel();
		chipLabel = new JLabel(Integer.toString(chips));
		cardPanel.setOpaque(false);
		setOpaque(false);
		cardPanel.setLayout(null);
		setLayout(null);
	}
	
	public void putCardImg(ImageIcon _img) {
		cardLabels[++cardCnt-1] = new JLabel(_img);
		cardPanel.add(cardLabels[cardCnt-1]);
		repaint();
	}
	
	public void sortCardPanel() {
		int start= (getWidth()-cardCnt*70+10)/2;
		for(int i=0; i<cardCnt; ++i) {
			cardLabels[i].setBounds(start, 0, 60, 90);
			start+=70;
		}
		repaint();
	}
	
	public void hideCardImg(int _idx) {
		preIcon = (ImageIcon) cardLabels[_idx].getIcon();
		cardLabels[_idx].setIcon(loadHideCardImage("back"));
		repaint();
	}
	public void revealCardImg(int _idx) {
		if(preIcon==null) return;
		cardLabels[_idx].setIcon(preIcon);
		repaint();
	}
	
    public ImageIcon loadHideCardImage(String _name) {
    	String card_path = "image/card_deck/"+ _name + ".png";
    	ImageIcon icon = new ImageIcon(card_path);
    	Image img = icon.getImage();
    	img = img.getScaledInstance(60, 90, Image.SCALE_SMOOTH);
    	return new ImageIcon(img);
    }
    
    public void reset() {
    	for(int i=0; i<cardCnt; ++i) {
    		cardPanel.remove(cardLabels[i]);
    		cardLabels[i]=null;
    	}
    	repaint();
    	cardCnt=0;
    }
	
	public JLabel getNameLabel() {
		return nameLabel;
	}
	public JLabel[] getCardLabels() {
		return cardLabels;
	}
	public JLabel getChipLabel() {
		return chipLabel;
	}
	public JPanel getCardPanel() {
		return cardPanel;
	}
	public int getCardCnt() {
		return cardCnt;
	}
	public void plusCardCnt() {
		++cardCnt;
	}
}
