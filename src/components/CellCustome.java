package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.ProductosDAO;
import views.frmProducto;

import java.util.Optional;

public class CellCustome extends TableCell<ProductosDAO, String> {
    private Button btnCelda;
    private ProductosDAO objPDAO;

    public CellCustome(int opc) {

        if (opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objPDAO = CellCustome.this.getTableView().getItems().get(CellCustome.this.getIndex());
                new frmProducto(CellCustome.this.getTableView(), objPDAO);
            });
        } else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema :)");
                alerta.setHeaderText("Confirmación de la acción");
                alerta.setContentText("¿Realmente deseas borrar el registro?");
                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    objPDAO = CellCustome.this.getTableView().getItems().get(CellCustome.this.getIndex());
                    objPDAO.delete();

                    CellCustome.this.getTableView().setItems(objPDAO.SELECT());
                    CellCustome.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)
            setGraphic(btnCelda);
    }
}
