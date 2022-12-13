import javax.swing.JPanel;
import java.awt.*;

public class PlayerLeftGUI extends PlayerGUI implements PlayerPanel{
    public PlayerLeftGUI(String _name){
        super(_name);
        initialize();
    }
    @Override
    public void initialize() {
        setBounds(30, 25, 550, 550);
        getCardPanel().setBounds(0, 460, 550, 90);
        getNameLabel().setBounds(300, 430, 50, 30);
        getNameLabel().setForeground(Color.WHITE);
        this.add(getNameLabel());
        this.add(getCardPanel());
    }

    @Override
    public void setCardGUI() {


    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w2 = getWidth() / 2;
        int h2 = getHeight() / 2;
        g2d.rotate(Math.PI/2, w2, h2);
        super.paintComponent(g);
    }
}
