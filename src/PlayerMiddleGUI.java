import java.awt.Color;

public class PlayerMiddleGUI extends PlayerGUI implements PlayerPanel{
		
	public PlayerMiddleGUI(String _name){
		super(_name);
		initialize();
	}

	@Override
	public void initialize() {
		setBounds(225, 450, 550, 150);
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
