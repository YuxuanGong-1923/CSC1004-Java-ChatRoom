import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

        // A clientHandler which can handle multi-clients
public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;

    //Read messages and data sent from client

    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket, String username) {

        //Connect a new user

        try {
            this.socket = socket;
            clientUsername = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientHandlers.add(this);
            broadcastMessage("Server " + clientUsername + " has entered the Chat");

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //Get the message from the chatPage

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                e.printStackTrace();
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    //Broadcast the message to multi-clients
    public void broadcastMessage(String messageToSend) {

        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.bufferedWriter.write(clientUsername);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.write(messageToSend);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

   // Remove the client if it left the chat

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("Server:" + clientUsername + "has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
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


