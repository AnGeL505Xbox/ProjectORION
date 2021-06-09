package ORION.LoginScreen;

import ORION.Main;
import ORION.Others.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void btFinish(ActionEvent event){
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