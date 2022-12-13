import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DealerGUI extends PlayerGUI implements PlayerPanel{
	
	public DealerGUI(){
		super("Dealer");
		initialize();
	}

	@Override
	public void initialize() {
		setBounds(225, 100, 550, 150);
		getCardPanel().setBounds(0, 30, 550, 90);
		getNameLabel().setBounds(0, 0, 50, 30);
		getNameLabel().setForeground(Color.WHITE);
		add(getNameLabel());
		add(getCardPanel());
	}

	@Override
	public void setCardGUI() {
		
		
	}
}
