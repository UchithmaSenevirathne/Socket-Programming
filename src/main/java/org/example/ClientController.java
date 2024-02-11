package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.Socket;

public class ClientController {
    @FXML
    public AnchorPane anchorpaneClient;
    @FXML
    public TextArea txtAreaClient;

    @FXML
    private TextField txtMsg;

    Socket remotesocket;

    DataOutputStream dataOutputStream;

    String message = "";

    public void initialize(){
        new Thread(()->{
            try {
                remotesocket = new Socket("localhost", 3003);

                dataOutputStream =
                        new DataOutputStream(remotesocket.getOutputStream());
                DataInputStream dataInputStream =
                        new DataInputStream(remotesocket.getInputStream());
                while (!message.equals("end")) {
                    message = dataInputStream.readUTF();
                    txtAreaClient.appendText("\nServer : "+message);
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
    } catch (
    IOException e) {
        throw new RuntimeException(e);
    }
    }
}
