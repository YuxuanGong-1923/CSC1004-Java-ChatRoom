import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    // GUI
    private JPanel menuPanel;
    private JButton serverButton;
    private JButton clientButton;

    public Menu(JFrame parent) {

        // GUI
        setTitle("Menu");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(550,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        serverButton.addActionListener(new ActionListener() {
            // CLICK THE BUTTON TO START THE SERVER
            @Override
            public void actionPerformed(ActionEvent e) {
                Server myForm = new Server((JFrame) null);
                setVisible(false);
            }
        });

        clientButton.addActionListener(new ActionListener() {
            //CLICK THIS BUTTON TO START THE LOGIN AND REGISTRATION OF CLIENT
            @Override
            public void actionPerformed(ActionEvent e) {

                clientChoice myForm = new clientChoice(null);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        Menu myForm = new Menu(null);
    }
}