import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server extends JFrame{

    private ServerSocket serverSocket;
    private JPanel serverPanel;
    private JButton clickToStartServerButton;

    public Server(JFrame parent) {
        // GUI
        setTitle("Menu");
        setContentPane(serverPanel);
        setMinimumSize(new Dimension(550,600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       //Create a new serverSocket

        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        clickToStartServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new Thread(() -> {startServer();})).start();
                clickToStartServerButton.setText("Server is running...");
            }
        });
        setVisible(true);
    }

        public void startServer(){
            try{
                while(!serverSocket.isClosed()){

        // Get input from the client about the login

                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String email = bufferedReader.readLine();
                    String password = bufferedReader.readLine();

        //Check whether the username and password is connected to the database

                    user = getAuthenticatedUser(email,password);

                    if(user != null){
                        bufferedWriter.write("ACCEPTED");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println("A new client has connected to the server……");
                        ClientHandler clientHandler = new ClientHandler(socket,email);
                        Thread thread = new Thread(clientHandler);
                        thread.start();
                    }
                    else{
                        System.err.println("Invalid user");
                        bufferedWriter.write("INVALID");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    public User user;
    private User getAuthenticatedUser(String email,String password){
        User user = null;

        //Connect to the database

        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
