import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clientChoice extends JFrame {
    private JPanel panel1;
    private JPanel DashboardPanel;
    private JButton clickHereToStartButton;
    private JButton clickHereToLoginButton;
    private JButton clickHereToRegisterButton;

    //GUI

    public clientChoice(JFrame parent) {
        setTitle("Menu");
        setContentPane(DashboardPanel);
        setMinimumSize(new Dimension(550,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Choose either register or login

        clickHereToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm myForm = new RegistrationForm(null);
                User user = myForm.user;
                setVisible(false);
            }
        });

        clickHereToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm(null);
                User user = loginForm.user;
                setVisible(false);
            }
        });
        setVisible(true);
    }
}
