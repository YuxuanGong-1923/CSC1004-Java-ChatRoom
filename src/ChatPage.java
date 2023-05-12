
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatPage extends JFrame{
    public JPanel chatPanel = new JPanel();
    private JLabel chatRoomLabel;
    public JPanel chatPage;
    private JScrollPane chatScrollPane;
    private JButton sendButton;
    private JTextField messageText;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;


    public ChatPage(JFrame parent, Socket socket, String username) {

        //GUI
        setTitle("Menu");
        setContentPane(chatPage);
        setMinimumSize(new Dimension(550,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        chatScrollPane.getViewport().add(chatPanel);
        chatPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        chatPanel.setLayout(new BoxLayout(this.chatPanel, BoxLayout.Y_AXIS));

        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            listenForMessage();
        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                        String messageToSend = messageText.getText();
                        bufferedWriter.write(username + ": " + messageToSend);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        messageText.setText("");
                        System.err.println("Sent one message");

                } catch (IOException ex) {
                    ex.printStackTrace();
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        });
    }


    public void listenForMessage() {
        new Thread(() -> {
        String msgFromGroupChat, username;
    while (true) {
        try {
            username = bufferedReader.readLine();
            msgFromGroupChat = bufferedReader.readLine();

            System.err.println("Received one message.");

            chatPanel.add(new JLabel(" User: " + username));
            chatPanel.add(new JLabel(msgFromGroupChat));
            chatPanel.add(new JLabel(""));
            chatPanel.revalidate();
            System.out.println(msgFromGroupChat);

        } catch (IOException e) {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
}).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






