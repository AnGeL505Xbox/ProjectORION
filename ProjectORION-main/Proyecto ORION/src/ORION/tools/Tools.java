package ORION.tools;

import ORION.LoginScreen.Login;
import ORION.LoginScreen.Register;
import ORION.Main;
import ORION.models.Conections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Tools {
    @FXML Label txAltura, txPeso, txIMC;
    @FXML VBox vbCartilla;

    @FXML Label lbName, lbLName, lbLName2, lbAge, lbCURP, lbWeight, lbHeight, lbUser, lbPassword;
    @FXML TextField txNName, txNLName, txNLName2, txNAge, txNCURP, txNWeight, txNHeight, txNUser, txNPassword;

    Login login = new Login();
    Conections conection;

    String name,ap,am,CURP,user, pass;
    String uS, pS, nM, lN1, lN2, cR;
    int aE;
    double wT, hT;
    int age, id;
    double weight, height;

    ListView<String> listView = new ListView<>();

    @FXML public void initialize() throws SQLException {
        conection=new Conections();
        id = login.getLogCount();

        //#region Obtener datos
        ResultSet resultSet = conection.consultar("SELECT * FROM users WHERE idUser= "+id+"");
        resultSet.next();
        user = resultSet.getString("username");
        pass = resultSet.getString("password");

        resultSet = conection.consultar("SELECT * FROM pacient WHERE idPacient= "+id+"");
        resultSet.next();
        name = resultSet.getString("name");
        ap = resultSet.getString("lastname");
        am = resultSet.getString("lastname2");
        age = resultSet.getInt("age");
        CURP = resultSet.getString("CURP");
        weight = resultSet.getDouble("weight");
        height = resultSet.getDouble("height");

        //#endregion

        //#region Cartilla

        vbCartilla.getChildren().add(listView);

        listView.getItems().add("Usuario: "+user);
        listView.getItems().add("Nombre: "+name);
        listView.getItems().add("Edad: "+age);
        listView.getItems().add("CURP: "+CURP);
        listView.getItems().add("Peso: "+weight);
        listView.getItems().add("Altura: "+height);
        //#endregion

        //#region User
        lbUser.setText(user);
        lbPassword.setText(pass);
        lbName.setText(name); lbLName.setText(ap); lbLName2.setText(am);
        lbAge.setText(String.valueOf(age));
        lbCURP.setText(CURP);
        lbWeight.setText(String.valueOf(weight));
        lbHeight.setText(String.valueOf(height));
        //#endregion
    }

    public void btCalculate(ActionEvent event){
        txAltura.setText(height+" Mts");
        txPeso.setText(weight+" Kgs");
        txIMC.setText(""+(weight/Math.pow(height,2)));
    }

    public void btReturn(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../MenuScreen/menu.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void btChange(ActionEvent event) throws SQLException {

        uS=user; pS=pass;
        nM=name; lN1=ap; lN2=am;
        cR=CURP;
        aE= age;
        wT= weight; hT= height;

        if (!txNUser.getText().isEmpty() || !txNUser.getText().isBlank()) uS=txNUser.getText();
        if (!txNPassword.getText().isEmpty() || !txNPassword.getText().isBlank()) pS=txNPassword.getText();
        if (!txNName.getText().isEmpty() || !txNName.getText().isBlank()) nM=txNName.getText();
        if (!txNLName.getText().isEmpty() || !txNLName.getText().isBlank()) lN1=txNLName.getText();
        if (!txNLName2.getText().isEmpty() || !txNLName2.getText().isBlank()) lN2=txNLName2.getText();
        if (!txNAge.getText().isEmpty() || !txNAge.getText().isBlank()) aE= Integer.parseInt(txNAge.getText());
        if (!txNCURP.getText().isEmpty() || !txNCURP.getText().isBlank()) cR = txNCURP.getText();
        if (!txNWeight.getText().isEmpty() || !txNWeight.getText().isBlank()) wT = Double.parseDouble(txNWeight.getText());
        if (!txNHeight.getText().isEmpty() || !txNHeight.getText().isBlank()) hT = Double.parseDouble(txNHeight.getText());

        conection.insmodel("UPDATE users SET username='"+uS+"', password='"+pS+"' WHERE idUser="+id+""); //Usuario
        conection.insmodel("UPDATE pacient SET name='"+nM+"', lastname='"+lN1+"', lastname2='"+lN2+"', age="+aE+", CURP='"+cR+"', weight="+wT+", height="+hT+" WHERE idPacient="+id+""); //Paciente
        alert("Datos actualizados","Se han actualizado sus datos, ahora regresara al menu de inicio de sesion","INFO");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../LoginScreen/login.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }

    }

    public void btDelete(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            conection.insmodel("DELETE FROM users WHERE idUser="+id+"");
            conection.insmodel("DELETE FROM pacient WHERE idPacient="+id+"");
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../LoginScreen/login.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void alert(String a,String b, String type){

        if (type.equals("CONF")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(a);
            alert.setContentText(b);
            alert.showAndWait();
        } else if (type.equals("INFO")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(a);
            alert.setContentText(b);
            alert.showAndWait();
        }
    }
}