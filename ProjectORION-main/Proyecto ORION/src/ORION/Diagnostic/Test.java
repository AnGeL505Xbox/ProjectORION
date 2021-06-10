package ORION.Diagnostic;

import ORION.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;


import java.io.IOException;

public class Test{
    @FXML CheckBox chkBo1, chkBo2, chkBo3, chkBo4, chkBo5, chkBo6, chkBo7, chkBo8, chkBo9, chkBo10, chkBo11, chkBo12, chkBo13;

    static String enfermedad;

    CheckBox[] chkBox= new CheckBox[13];
    int suma=0;

    @FXML public void initialize(){
        for (int x=0;x<5;x++){
           chkBox[0]=chkBo1;chkBox[1]=chkBo2;
           chkBox[2]=chkBo3;chkBox[3]=chkBo4;
           chkBox[4]=chkBo5;chkBox[5]=chkBo6;
           chkBox[6]=chkBo7;chkBox[7]=chkBo8;
           chkBox[8]=chkBo9;chkBox[9]=chkBo10;
           chkBox[10]=chkBo11;chkBox[11]=chkBo12;
           chkBox[12]=chkBo13;
        }
    }
    public void btnResult(ActionEvent event){
        for (int i=0;i<13;i++){
            if (chkBox[i].isSelected()) suma=suma+i;
        }
        if (suma<16 & suma>1) enfermedad="Abrasion Corneal";
        if (suma<80 & suma >16) enfermedad="Bulimia";

        System.out.println(enfermedad);
        System.out.println(suma);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("diagnostico.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    //#region Getters & Setters
    public String getEnfermedad() { return enfermedad; }
    public void setEnfermedad(String enfermedad) { this.enfermedad = enfermedad; }
    //#endregion
}
