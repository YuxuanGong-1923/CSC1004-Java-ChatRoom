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

    public clientChoice(JFrame parent) {
        //super(parent);
        setTitle("Menu");
        setContentPane(DashboardPanel);
        setMinimumSize(new Dimension(550,600));
        //setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clickHereToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm myForm = new RegistrationForm(null);
                User user = myForm.user;
                setVisible(false);
                //if(user != null){
                //    System.out.println("Successful registration of + :" + user.name);
                //}
                //else{
                //    System.out.println("Registration canceled");
                //    setVisible(true);
                //}
            }
        });
        clickHereToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm(null);
                User user = loginForm.user;
                setVisible(false);
                //if(user != null){
                //    System.out.println("Successful login of + :" + user.name);
                //
                //}
                //else{
                //    System.out.println("Login canceled");
                //    setVisible(true);
                //}
            }
        });
        setVisible(true);
    }
}
