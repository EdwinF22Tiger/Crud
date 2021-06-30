package views;

import components.CellCustome;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.ProductosDAO;
import javafx.util.Callback;

public class frmProductos extends Stage {
    private TableView<ProductosDAO>tbvProductos ;
    private Scene escena ;
    private VBox vbox;
    private HBox hbox ;
    private Button btnagregar;
    private ProductosDAO objCDAO;

    public frmProductos(){
        objCDAO = new ProductosDAO();
        CrearUI ();
        this.setTitle("GESTION DE PRODUCTO ");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI() {

        vbox= new VBox();

        tbvProductos = new TableView<>();
        btnagregar = new Button("Agregar Producto");
        btnagregar.setOnAction(event -> {
            new frmProducto(tbvProductos,null);
        });
        vbox.getChildren().addAll(tbvProductos,btnagregar);
        creartabla();
        escena = new Scene(vbox,500,250);
    }
    private void creartabla() {
        TableColumn<ProductosDAO, Integer> tbcIdprodu = new TableColumn<>("ID");
        tbcIdprodu.setCellValueFactory(new PropertyValueFactory<>("id_producto"));

        TableColumn<ProductosDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));

        TableColumn<ProductosDAO, Integer> tbcprecio = new TableColumn<>("Precio");
        tbcprecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        TableColumn<ProductosDAO, Integer> tbcexistencia = new TableColumn<>("Existencia");
        tbcexistencia.setCellValueFactory(new PropertyValueFactory<>("Existencia"));

        TableColumn<ProductosDAO, Integer> tbcdisponible = new TableColumn<>("disponible");
        tbcdisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        TableColumn<ProductosDAO, TableView> tbcimagen = new TableColumn<>("Imagen");
        tbcimagen.setCellValueFactory(new PropertyValueFactory<>("Imagen"));

        TableColumn<ProductosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
                    @Override
                    public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> param) {
                        return new CellCustome(1);
                    }
                }
        );

        TableColumn<ProductosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
                    @Override
                    public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> param) {
                        return new CellCustome(2);
                    }
                }
        );


        tbvProductos.getColumns().addAll(tbcIdprodu,tbcNombre,tbcprecio,tbcexistencia,tbcdisponible,tbcimagen,tbcEditar,tbcBorrar);
        tbvProductos.setItems(objCDAO.SELECT());
    }
}