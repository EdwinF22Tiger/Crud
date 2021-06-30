package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductosDAO {
    private int id_producto;
    private String nombre;
    private int precio;
    private int existencia;
    private String disponible;
    private String imagen;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private File file;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    Connection con;

    public void insert() {
        try {

            String query = "insert into tbl_productosbd (nombre,precio,existencia,disponible,imagen)" +
                    "values ('" + nombre + "'," + precio + "," + existencia + " ,'" + disponible + "','" + imagen + "')";

            //  con =Conexion.conexion;

            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void update() {
        try {
            String query = "update tbl_productosbd  set nombre= '" + nombre + "', precio = " + precio + "," +
                    "existencia= " + existencia + ",disponible = '" + disponible + "',imagen = '" + imagen + "' where id_producto = " + id_producto;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            String query = "delete from tbl_productosbd where id_producto =" + id_producto + "";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ProductosDAO> SELECT() {
        ObservableList<ProductosDAO> listaP = FXCollections.observableArrayList();
        try {
            ProductosDAO objC;
            String query = "SELECT * FROM tbl_productosbd ";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objC = new ProductosDAO();
                objC.id_producto = res.getInt("id_producto");
                objC.nombre = res.getString("NOMBRE");
                objC.precio = res.getInt("precio");
                objC.existencia = res.getInt("existencia");
                objC.disponible = res.getString("disponible");
                objC.imagen = res.getString("imagen");
                listaP.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaP;
    } // trae datos
}

