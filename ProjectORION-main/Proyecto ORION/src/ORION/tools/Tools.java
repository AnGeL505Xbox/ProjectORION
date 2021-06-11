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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tools {
    @FXML Label txAltura, txPeso, txIMC;
    @FXML VBox vbCartilla;

    Login login = new Login();
    Conections conection;

    String name,ap,am,CURP,user;
    int age, id;
    double weight, height;

    ListView<String> listView = new ListView<>();

    @FXML public void initialize() throws SQLException {
        conection=new Conections();
        id = login.getLogCount();


        ResultSet resultSet = conection.consultar("SELECT * FROM users WHERE idUser= "+id+"");
        resultSet.next();
        user = resultSet.getString("username");

        resultSet = conection.consultar("SELECT * FROM pacient WHERE idPacient= "+id+"");
        resultSet.next();
        name = resultSet.getString("name");
        ap = resultSet.getString("lastname");
        am = resultSet.getString("lastname2");
        age = resultSet.getInt("age");
        CURP = resultSet.getString("CURP");
        weight = resultSet.getDouble("weight");
        height = resultSet.getDouble("height");

        vbCartilla.getChildren().add(listView);

        listView.getItems().add("Usuario: "+user);
        listView.getItems().add("Nombre: "+name);
        listView.getItems().add("Edad: "+age);
        listView.getItems().add("CURP: "+CURP);
        listView.getItems().add("Peso: "+weight);
        listView.getItems().add("Altura: "+height);
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
}