
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.*;

public class LoginForm extends JFrame{
    public User user;
    private JPanel loginPanel;
    private JButton btnLogin;
    private JButton btnExit;
    private JTextField tfEmail;
    private JPasswordField pfPassword;

    public LoginForm(JFrame parent) {

        //super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(550,600));
        //setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                try {
                    Socket socket = new Socket("127.0.0.1",1234);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedWriter.write(email + "\n");
                    bufferedWriter.write(password + "\n");
                    bufferedWriter.flush();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String state = bufferedReader.readLine();

                    if (state.equals("ACCEPTED")) {
                        dispose();
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "Successfully Login.",
                                "Congratulations!",
                                JOptionPane.INFORMATION_MESSAGE);
                        ChatPage myForm = new ChatPage(null,socket,email);
                        setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "Email or password invalid.",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}




