package ORION.LoginScreen;

import ORION.Main;
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

public class Register{

    @FXML TextField txName, txAge, txAP, txAM, txPeso, txAltura, txCURP;
    @FXML CheckBox checkPrivacy;

    static Login login = new Login();

    Conections conection;
    @FXML protected void initialize(){
        conection=new Conections();
    }

    public void btFinish(ActionEvent event){
        int count = login.getCount();
        if (!txName.getText().trim().equals("") && !txAge.getText().trim().equals("")&& !txAP.getText().trim().equals("") && !txAM.getText().trim().equals("")
                && !txPeso.getText().trim().equals("")&& !txAltura.getText().trim().equals("")&& !txCURP.getText().trim().equals("")){
            String Nm=txName.getText();
            String Ag=txAge.getText();
            String Ap=txAP.getText();
            String Am=txAM.getText();
            String Pso=txPeso.getText();
            String Alt=txAltura.getText();
            String Curp=txCURP.getText();
            conection.insmodel("UPDATE pacient SET name='"+Nm+"' ,lastname= '"+Ap+"' ,lastname2= '"+Am+"' ,age= '"+Ag+"' ,CURP= '"+Curp+"' ,weight= '"+Pso+"', height= '"+Alt+"' WHERE idPacient = '"+count+"'");

            txName.setText("");
            txAge.setText("");
            txAP.setText("");
            txAM.setText("");
            txPeso.setText("");
            txAltura.setText("");
            txCURP.setText("");

            alert("Datos actualizados","Se han llenado los datos en tu perfil","INFO");
        }else{
            alert("Error en los datos","Favor de llenar todos los datos","ERROR");
        }
        if(!checkPrivacy.isSelected()) {
            alert("Error","Favor de ver las politicas de privacidad","ERROR");
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../LoginScreen/login.fxml"));
                Scene scene=new Scene(root);
                Main.stage.setScene(scene);
                Main.stage.setMaximized(false);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
    public void alert(String a,String b, String type){

        if (type.equals("ERROR")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(a);
            alert.setContentText(b);
            alert.show();
        } else if (type.equals("INFO")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(a);
            alert.setContentText(b);
            alert.show();
        }
    }
}