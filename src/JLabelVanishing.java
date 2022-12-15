import javax.swing.*;
import java.awt.*;

public class JLabelVanishing extends JLabel implements Runnable {


    private JPanel panel;
    private Boolean isAppear;
    private float speed;
    private float alpha;
    public JLabelVanishing(JPanel _parent, Boolean _isAppear, float _speed) {
        panel = _parent;
        isAppear = _isAppear;
        speed = _speed;
    }
    public JLabelVanishing(String s, JPanel _parent, Boolean _isAppear, float _speed) {
        super(s);
        panel = _parent;
        isAppear = _isAppear;
        speed = _speed;
    }

    public void setAppear(boolean _isAppear){
        isAppear=_isAppear;
    }

    @Override
    public void run() {
        try {
            Color currentColor = getForeground();
            if(isAppear){
                alpha=0;
                setVisible(true);
                while(alpha<255){
                    Thread.sleep(17);
                    alpha+=(float)255/60/speed;
                    setForeground(new Color(currentColor.getRed(), currentColor.getBlue(), currentColor.getGreen(), (int)alpha));
                    panel.repaint();
                }
            }
            else{
                alpha=255;
                setVisible(true);
                while(alpha>0){
                    Thread.sleep(17);
                    alpha-=(float)255/60/speed;
                    setForeground(new Color(currentColor.getRed(), currentColor.getBlue(), currentColor.getGreen(), (int)alpha));
                    panel.repaint();
                }
                setVisible(false);
            }
        } catch (InterruptedException e) {
            System.out.println("JLabelVanishing : Thread error");
        }

    }
}
