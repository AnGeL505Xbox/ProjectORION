package ORION.MenuScreen;

import ORION.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class Menu {

    public void bntOut(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ORION/LoginScreen/login.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void btDiagnostic(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ORION/Diagnostic/diagnostico.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void btTools(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ORION/tools/tools.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
