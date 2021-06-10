package ORION.tools;

import ORION.LoginScreen.Register;
import ORION.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Tools {
    @FXML Label txAltura, txPeso, txIMC;
    @FXML VBox vbCartilla;

    int us = 0, id = 0;
    ListView<String> listView = new ListView<>();
    @FXML public void initialize(){
        vbCartilla.getChildren().add(listView);

        listView.getItems().add("Usuario: "+Register.getListUser().get(us).getUsername());
        listView.getItems().add("Nombre: "+Register.getListUser().get(us).getName());
        listView.getItems().add("Edad: "+Register.getListUser().get(us).getAge());
        listView.getItems().add("CURP: "+Register.getListUser().get(us).getCURP());
        listView.getItems().add("Peso: "+Register.getListUser().get(us).getWeigth());
        listView.getItems().add("Altura: "+Register.getListUser().get(us).getHeight());
    }

    public void btCalculate(ActionEvent event){
        txAltura.setText(Register.getListUser().get(us).getHeight()+" Mts");
        txPeso.setText(Register.getListUser().get(us).getWeigth()+" Kgs");
        txIMC.setText(""+Register.getListUser().get(us).getImc());
    }

    public void btReturn(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../MenuScreen/menu.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
