package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    @FXML
    public AnchorPane anchorpaneServer;

    @FXML
    public TextArea txtAreaServer;

    @FXML
    private TextField txtMsg;

    ServerSocket serverSocket;

    DataOutputStream dataOutputStream;
    Socket localSocket;

    String message = "";

    public void initialize(){
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(3003);

                localSocket = serverSocket.accept();
                DataInputStream dataInputStream =
                        new DataInputStream(localSocket.getInputStream());
                dataOutputStream =
                        new DataOutputStream(localSocket.getOutputStream());

                while (!message.equals("end")) {
                    message = dataInputStream.readUTF();
                    txtAreaServer.appendText("\nClient : "+message);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        try {
            dataOutputStream.writeUTF(txtMsg.getText().trim());
            dataOutputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
