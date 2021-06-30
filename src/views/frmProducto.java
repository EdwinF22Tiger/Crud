package views;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.ProductosDAO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;

public class frmProducto extends Stage {
    private Scene scene;
    private VBox vBox;
    private TextField txtNombre, txtPrecio, txtExistencia, txtDisponible, txtMarca, txtModelo, txtcategorias, txtImagen;
    private Button btnSave , btncargar;
    private ProductosDAO objproducDAO;
    private ImageView vistadeimagen;
    private FileChooser files;
    private Image imagen;
    private File file;
    private TableView<ProductosDAO> tableVProducto;

    public frmProducto(TableView<ProductosDAO>tableVProducto, ProductosDAO objproduDAO){
        this.tableVProducto = tableVProducto;
        if (objproduDAO != null)
            this.objproducDAO = objproduDAO;
        else
            this.objproducDAO = new ProductosDAO();
        CreateUI();
        this.setTitle("Gestion de productos");
        this.setScene(scene);
        this.show();
    }

    private void CreateUI() {
        vBox= new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));

        txtNombre= new TextField();
        txtNombre.setText(objproducDAO.getNombre());
        txtNombre.setPromptText("Nombre del producto");

        txtPrecio = new TextField();
        txtPrecio.setText(String.valueOf(objproducDAO.getPrecio()));
        txtPrecio.setPromptText("Precio");

        txtExistencia = new TextField();
        txtExistencia.setText(String.valueOf(objproducDAO.getExistencia()));
        txtExistencia.setPromptText("Existencia");

        txtDisponible = new TextField();
        txtDisponible.setText(objproducDAO.getDisponible());
        txtDisponible.setPromptText("disponible");

        txtImagen = new TextField();
        btncargar = new Button("Imagen");

        files = new FileChooser();
        vistadeimagen = new ImageView();
        vistadeimagen.setFitHeight(150);
        vistadeimagen.setFitWidth(150);
        vistadeimagen.setPreserveRatio(true);

        files.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Buscar ", "*.png", "*.jpg",  "*.jpeg")
        );
        btncargar.setOnAction(event -> {
            file = files.showOpenDialog(this);
            if(files != null){
                imagen = new Image(file.toURI().toString(), 150, 150, true, true);
                vistadeimagen.setImage(imagen);
                vistadeimagen.setFitHeight(200);
                vistadeimagen.setFitWidth(200);
                vistadeimagen.setPreserveRatio(true);
            }
        });

        btnSave= new Button("Guardar");

        btnSave.setOnAction(event -> {
            objproducDAO.setNombre(txtNombre.getText());
            objproducDAO.setPrecio(Integer.parseInt(txtPrecio.getText()));
            objproducDAO.setExistencia(Integer.parseInt(txtExistencia.getText()));
            objproducDAO.setDisponible(txtDisponible.getText());

            objproducDAO.setFile(file);

            if (objproducDAO.getId_producto() > 0)
                objproducDAO.update();
            else
                objproducDAO.insert();

            tableVProducto.setItems(objproducDAO.SELECT());
            tableVProducto.refresh();
            this.close();
        });
        vBox.getChildren().addAll(txtNombre, txtPrecio, txtExistencia, txtDisponible,
                vistadeimagen,txtImagen, btncargar,btnSave);
        scene = new Scene(vBox,300,700);


    }
}
