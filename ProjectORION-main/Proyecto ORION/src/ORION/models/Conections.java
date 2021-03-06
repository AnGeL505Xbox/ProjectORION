package ORION.models;

import java.sql.*;

public class Conections {
    private String user="root";
    private String password="";
    private String bd="project orion";
    private String server="localhost";
    public Connection conexion;
    public Conections(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://"+server+":3306/"+bd+"?useUnicode=true&useJDBCCompilantTimeZoneShift=useLegacyDatetimeCode&serverTimeZone=UTC",user,password);
            System.out.println("Ya estas conectado");
        }catch (Exception e){
            System.out.println("Imposible conectar con el servidor "+e.getMessage());
        }
    }
    public ResultSet consultar (String sql){
        ResultSet resultSet = null;
        try{
            Statement consulta= conexion.createStatement();
            resultSet = consulta.executeQuery(sql);
        }catch (Exception e){
            System.out.println("ERROR en la consulta "+e.getMessage());
        }
        return resultSet;
    }
    //Insertar, modificar o eliminar
    public void insmodel (String sql){
        try {
            Statement st=conexion.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
