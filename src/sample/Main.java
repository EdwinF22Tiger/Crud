package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Conexion;
import views.frmProductos;


public class Main extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompetencia1, menCerrar;
    private MenuItem  mitTBCanciones,  mitSalir;
    private Scene escena;

    @Override
    public void start(Stage primaryStage) throws Exception {

        CrearMenu();
        primaryStage.setTitle("Base de datos Productos_Topicos");
        primaryStage.setScene(escena);
        primaryStage.setMaximized(true);
        primaryStage.show();

        // se abre la conexion
        Conexion.getConexion();

    }

    private void CrearMenu() {
        vBox = new VBox();

        mnbPrincipal = new MenuBar();
        menCompetencia1 = new Menu("Crud");
        menCerrar = new Menu("Cerrar");
        mnbPrincipal.getMenus().addAll(menCompetencia1, menCerrar);
        mitTBCanciones = new MenuItem("Inventario");
        mitTBCanciones.setOnAction(event -> opcionesMenu(1));
        menCompetencia1.getItems().addAll(mitTBCanciones);
        mitSalir = new MenuItem("Salir");
        mitSalir.setOnAction(event -> {
            System.exit(0);
        });
        menCerrar.getItems().add(mitSalir);

        vBox.getChildren().add(mnbPrincipal);

        escena = new Scene(vBox, 300, 70);

    }

    private void opcionesMenu(int opc) {
        switch (opc) { case 1: new frmProductos();break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}