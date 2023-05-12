
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

        //GUI
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(550,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Get input from login page

                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                try {

                    //create a socket

                    Socket socket = new Socket("127.0.0.1",1234);

                    //send the user information to the server
                    // and get the feedback from server

                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedWriter.write(email + "\n");
                    bufferedWriter.write(password + "\n");
                    bufferedWriter.flush();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String state = bufferedReader.readLine();

                    //GUI
                    //if receive "Accepted" them successfully login

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

        //click exit to close

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}




