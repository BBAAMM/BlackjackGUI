import javax.swing.*;

public class MovingCardThread implements Runnable{

    private JPanel cardPanel;
    private JComponent comp;
    private int x2;
    private int y2;
    public MovingCardThread(JPanel _panel){
        cardPanel = _panel;
    }
    public void setCardObject(JComponent _comp, int _x2, int _y2){
        comp = _comp;
        x2 = _x2;
        y2 = _y2;
    }
    @Override
    public void run() {
        try {
            double distance = Math.sqrt(Math.pow(x2-comp.getX(),2)+Math.pow(y2-comp.getY(),2));
            double sinX = (y2-comp.getY())/distance;
            double cosX = (x2-comp.getX())/distance;
            double x = comp.getX();
            double y = comp.getY();
            long beforeTime = System.currentTimeMillis();

            while(true) {
                Thread.sleep(17);
                if(System.currentTimeMillis()-beforeTime>=10/2.75*distance) {
                    comp.setLocation(x2, y2);
                    break;
                }
                else {
                    x+=5*cosX;
                    y+=5*sinX;
                }
                comp.setLocation((int)x, (int)y);
                cardPanel.repaint();
            }
        }
        catch (Exception e){
            System.out.println("error");
        }
    }
}
