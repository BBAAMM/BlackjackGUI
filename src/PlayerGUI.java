import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerGUI extends JPanel{
	private JLabel nameLabel;
	private JLabel[] cardLabels;
	private ImageIcon preIcon;
	private JLabel chipLabel;
	private JLabelVanishing stateLabel;
	private JPanel cardPanel;
	private InstallFont fontFamily;
	private int cardCnt = 0;
	private int chips = 10;
	
	public PlayerGUI(String _name) {
		setLayout(null);
		fontFamily = new InstallFont();
		nameLabel = new JLabel(_name);
		nameLabel.setFont(fontFamily.boldFont(20f));
		cardLabels = new JLabel[8];
		cardPanel = new JPanel(null);
		chipLabel = new JLabel();
		chipLabel.setFont(fontFamily.mediumFont(15f));
		cardPanel.setOpaque(false);

		nameLabel.setForeground(Color.WHITE);
		chipLabel.setForeground(Color.WHITE);
		stateLabel = new JLabelVanishing(this, true, 1);
		stateLabel.setFont(fontFamily.boldFont(50f));
		stateLabel.setHorizontalAlignment(JLabel.CENTER);
		stateLabel.setForeground(Color.WHITE);
		setOpaque(false);

		fontFamily=new InstallFont();
	}
	
	public void putCardImg(ImageIcon _img) {
		cardLabels[++cardCnt-1] = new JLabel(_img);
		cardPanel.add(cardLabels[cardCnt-1]);
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
		getStateLabel().setAppear(false);
		new Thread(getStateLabel()).start();
    	cardCnt=0;
    }

	public void setChipLabel(int _chip){
		chipLabel.setText("Chips : " + Integer.toString(_chip));
		repaint();
	}
	public JLabel getNameLabel() {
		return nameLabel;
	}
	public JLabel[] getCardLabels() {
		return cardLabels;
	}
	public JLabelVanishing getStateLabel(){return stateLabel;}
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
	public InstallFont getFontFamily(){return fontFamily;}
}
