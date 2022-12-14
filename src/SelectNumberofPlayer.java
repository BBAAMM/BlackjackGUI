import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectNumberofPlayer extends JFrame implements ActionListener {

    JPanel panel;
    JLabelVanishing welcomeText;
    InstallFont fontFamily;
    JButton[] playerNumArray;
    Blackjack callback;
    public SelectNumberofPlayer(Blackjack _callback){
        callback=_callback;
        initialGUI();
        fontFamily = new InstallFont();
        panel = new JPanel(null);
        panel.setBounds(0,0,400,600);
        welcomeScreen();
        selectPlayerNum();
    }
    public void initialGUI() {
        setTitle("Welcome to Blackjack world");
        getRootPane().setPreferredSize(new Dimension(400, 600));
        pack();
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("Thread : sleep error");
        }
    }

    public void welcomeScreen(){
        welcomeText = new JLabelVanishing("환영합니다.", panel, false,2);
        welcomeText.setBounds(50, 275, 300, 50);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setFont(fontFamily.boldFont(40f));
        panel.add(welcomeText);
        add(panel);
        new Thread(welcomeText).run();
        panel.removeAll();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread : sleep error");
        }
    }
    public void selectPlayerNum(){
        JLabelVanishing playerNumText1 = new JLabelVanishing("몇 명", panel, true,1);
        playerNumText1.setBounds(30,130, 200, 60);
        playerNumText1.setFont(fontFamily.boldFont(60f));
        panel.add(playerNumText1);
        new Thread(playerNumText1).run();

        JLabelVanishing playerNumText2 = new JLabelVanishing("플레이 하십니까?", panel, true, 1);
        playerNumText2.setBounds(30,200, 350, 40);
        playerNumText2.setFont(fontFamily.mediumFont(40f));
        panel.add(playerNumText2);
        new Thread(playerNumText2).run();
        playerNumArray = new JButton[3];
        for(int i=0; i<3; ++i){
            playerNumArray[i]= new JButton(Integer.toString(i+1));
            playerNumArray[i].setFont(fontFamily.boldFont(20f));
            playerNumArray[i].setBackground(new Color(111, 111, 111));
            playerNumArray[i].setBounds(40+120*i,400,80,60);
            playerNumArray[i].setOpaque(false);
            playerNumArray[i].setBorder(new RoundedBorder(30));
            playerNumArray[i].addActionListener(this);
            panel.add(playerNumArray[i]);
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<3; ++i){
            if(e.getSource().equals(playerNumArray[i])){
                dispose();
                callback.startController(i+1);
            }
        }
    }
}
