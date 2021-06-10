package ORION;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static  Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 910, 477));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}