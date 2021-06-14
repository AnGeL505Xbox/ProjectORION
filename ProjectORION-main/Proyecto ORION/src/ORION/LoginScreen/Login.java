package ORION.LoginScreen;

import ORION.Main;
import ORION.models.Conections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login{
    static String userReg, passReg, userLog, passLog;
    static int idPacient=0;
    static int regCount, logCount;
    private Conections conection;

    @FXML TextField txtNumberControl ,txtPassword, txUserName, txPassWord;
    @FXML ImageView imgLogo;
    @FXML AnchorPane paneLogin, paneRegister;
    @FXML public void initialize() {
        conection = new Conections();
        //#region Login events
        txtNumberControl.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        log();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        txtPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        log();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        txUserName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) reg();
            }
        });
        txPassWord.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) reg();
            }
        });
        //#endregion
    }

    //#region Eventos del boton de Acceder
    public void login(ActionEvent event) throws SQLException { log(); }

    public void log() throws SQLException {
        userLog =txtNumberControl.getText();
        passLog =txtPassword.getText();

        ResultSet resultSet = conection.consultar("select * from users where username = '"+userLog+"' and password = '"+passLog+"'");
        System.out.println("select * from users where username = '"+userLog+"' and password = '"+passLog+"'");
        if (resultSet!=null){
            int timer=0;
            while (resultSet.next()){
                logCount = resultSet.getInt("idUser");
                System.out.println(logCount);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Bienvenido "+userLog);
                alert.showAndWait();
                timer++;
            }
            if (timer==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Credenciales no validas");
                alert.showAndWait();
            } else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../MenuScreen/menu.fxml"));
                    Scene scene = new Scene(root);
                    Main.stage.setScene(scene);
                    Main.stage.setMaximized(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //#endregion
    //#region Eventos del boton registro
    public void registro(ActionEvent event){
        paneLogin.setVisible(false);
        paneRegister.setVisible(true);
    }

    public void btRegistrar(ActionEvent event) throws SQLException {
        if (!txUserName.getText().trim().equals("")&& !txPassWord.getText().trim().equals("")){
            String UsN=txUserName.getText();
            String Psd=txPassWord.getText();

            ResultSet resultSet = conection.consultar("SELECT * FROM users ORDER BY idUser DESC");

            if(resultSet.next()) {
                regCount = resultSet.getInt("idUser") + 1;
                conection.insmodel("INSERT INTO pacient(idPacient,name,lastname,lastname2,age,CURP,weight,height,idSick) VALUES ('"+ regCount +"','Nul','Nul','Null',10,'Null',0,0,1)");
                conection.insmodel("INSERT INTO users(idUser,username, password, idPacient) VALUES ('"+ regCount +"','"+UsN+"','"+Psd+"','"+ regCount +"')");
            }

            System.out.println("INSERT INTO users(username, password) VALUES ('"+UsN+"','"+Psd+"','"+ regCount +"')");

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setContentText("Datos llenados correctamente");
            alert.show();
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los datos");
            alert.setContentText("Favor de llenar todos los datos");
            alert.show();
        }
        reg();
    }

    public void reg(){
        this.userReg = txUserName.getText();
        this.passReg = txPassWord.getText();
        if(txUserName.getText().isEmpty() || txPassWord.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos vacios");
            alert.setContentText("Usted no ha llenado todos los cuadros de texto, porfavor llenelos y prosiga con su registro");
            alert.show();
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../LoginScreen/register.fxml"));
                Scene scene = new Scene(root);
                Main.stage.setScene(scene);
                Main.stage.setMaximized(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btCancel(ActionEvent event){
        paneLogin.setVisible(true);
        paneRegister.setVisible(false);
    }

    //#endregion
    //#region Getters & Setters
    public int getCount() { return regCount; }
    public int getLogCount() { return logCount; }
    //#endregion
}