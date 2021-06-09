package ORION.Diagnostic;

import ORION.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
public class  Diagnostico{


    public void initialize(){

    }
    @FXML ListView clmEnfe=new ListView();
    @FXML Label lblTra=new Label();


    Test test = new Test();
    String enfe=test.getEnfermedad();

    public void btnTest(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ORION/Diagnostic/test.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void btnBack(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../MenuScreen/menu.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void mostrar(ActionEvent event){
      clmEnfe.getItems().addAll(test.getEnfermedad());
      lblTra.setText(test.getEnfermedad()+" se cura con el siguiente tratamiento");

    }

}
