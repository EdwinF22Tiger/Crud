package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static String server = "127.0.0.1";
    private static String user   = "root";
    private static String pwd    = "1234567812345678";
    private static String db     = "productosdb";

    public static Connection conexion;
    public static void getConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://"+server+":3306/"+db,user,pwd);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}