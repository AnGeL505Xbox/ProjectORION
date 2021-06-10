package ORION.LoginScreen;

import ORION.Main;
import ORION.Others.User;
import ORION.models.Conections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.LinkedList;

public class Register{

    @FXML TextField txName, txAge, txAP, txAM, txPeso, txAltura, txCURP;
    @FXML CheckBox checkPrivacy;

    static Login login = new Login();
    static LinkedList<User> listUser= new LinkedList<>();

    User user = new User("","", login.getPassWord(), login.getUserName(),0,0,0,null);
    Conections conection;
    @FXML protected void initialize(){
        conection=new Conections();
    }
    public void btFinish(ActionEvent event){
        if (!txName.getText().trim().equals("") && !txAge.getText().trim().equals("")&& !txAP.getText().trim().equals("") && !txAM.getText().trim().equals("")
                && !txPeso.getText().trim().equals("")&& !txAltura.getText().trim().equals("")&& !txCURP.getText().trim().equals("")){
            String Nm=txName.getText();
            String Ag=txAge.getText();
            String Ap=txAP.getText();
            String Am=txAM.getText();
            String Pso=txPeso.getText();
            String Alt=txAltura.getText();
            String Curp=txCURP.getText();
            conection.insmodel("INSERT INTO pacient (name, lastname, lastname2, age, CURP, weight, height)VALUES (´"+Nm+"´,´"+Ap+"´,´"+Am+"´,´"+Ag+"´,´"+Ap+
                    "´,´"+Curp+"´,´"+Pso+"´,´"+Alt+"´)");

             txName.setText("");
             txAge.setText("");
             txAP.setText("");
             txAM.setText("");
             txPeso.setText("");
             txAltura.setText("");
             txCURP.setText("");

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setContentText("Registro insertado correctamente");
            alert.show();

        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los datos");
            alert.setContentText("Favor de llenar todos los datos");
            alert.show();
        }
        if(checkPrivacy.isSelected()) {
            this.user.setName(txName.getText() + " " + txAP.getText() + " " + txAM.getText());
            this.user.setCURP(txCURP.getText());
            this.user.setAge(Integer.parseInt(txAge.getText()));
            this.user.setHeight(Double.parseDouble(txAltura.getText()));
            this.user.setWeigth(Double.parseDouble(txPeso.getText()));
            listUser.clear(); //Esto sirve para eliminar el admin y posteriormente agregarlo
            listUser.add(this.user);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("../LoginScreen/login.fxml"));
                Scene scene=new Scene(root);
                Main.stage.setScene(scene);
                Main.stage.setMaximized(false);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
    public static LinkedList<User> getListUser() { return listUser; }
}