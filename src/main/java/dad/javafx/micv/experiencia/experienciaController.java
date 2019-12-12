package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.app.curriculum;
import dad.javafx.micv.app.rootController;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class experienciaController implements Initializable {
	private experienciaModel modelo = new experienciaModel();
	private curriculum cv;
	@FXML
	private BorderPane experienciaView;

	@FXML
	private TableView<trabajo> experienciaTableView;

	@FXML
	private TableColumn<trabajo, LocalDate> expDesdeColum;

	@FXML
	private TableColumn<trabajo, LocalDate> expHastaColum;

	@FXML
	private TableColumn<trabajo, String> expDenominacionColum;

	@FXML
	private TableColumn<trabajo, String> expOrganizadorColum;

	@FXML
	private VBox experienciaBotonera;

	@FXML
	private Button expAnadirButton;

	@FXML
	private Button formacionEliminarButton;

	public experienciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cv = rootController.getcv();

		// bindeos
		experienciaTableView.itemsProperty().bindBidirectional(cv.getExperiencia().trabajostablaProperty());
		// valores de celdas tableview
		expDesdeColum.setCellValueFactory(v -> v.getValue().desdeProperty());
		expHastaColum.setCellValueFactory(v -> v.getValue().hastaProperty());
		expDenominacionColum.setCellValueFactory(v -> v.getValue().denominacionProperty());
		expOrganizadorColum.setCellValueFactory(v -> v.getValue().empleadorProperty());

	}

	@FXML
	private void onExpAnadirButton(ActionEvent event) {
		// crear vista dialogo peronalizado
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField denomina = new TextField();
		TextField empleador = new TextField();
		DatePicker desde = new DatePicker();
		DatePicker hasta = new DatePicker();

		gp.add(new Label("Denominación"), 0, 0);
		gp.add(denomina, 1, 0);
		gp.add(new Label("Empleador"), 0, 1);
		gp.add(empleador, 1, 1);
		gp.add(new Label("Desde"), 0, 2);
		gp.add(desde, 1, 2);
		gp.add(new Label("Hasta"), 0, 3);
		gp.add(hasta, 1, 3);

		Dialog<trabajo> dial = new Dialog<trabajo>();
		dial.setTitle("Nuevo título");
		dial.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dial.getDialogPane().setContent(gp);
		dial.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return new trabajo(desde.getValue(), hasta.getValue(), denomina.getText(), empleador.getText());
			}
			return null;
		});
		// añadir resultado a la lista
		Optional<trabajo> res = dial.showAndWait();
		if (res.isPresent()) {
			ArrayList<trabajo> aux = new ArrayList<trabajo>();

			if (cv.getExperiencia().trabajostablaProperty() != null) {
				aux.addAll(cv.getExperiencia().trabajostablaProperty());
			}
			aux.add(res.get());

			cv.getExperiencia().setTrabajostabla(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	@FXML
	private void onExpEliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Alerta!");
		alert.setHeaderText("Borrado de datos");
		alert.setContentText("¿Seguro que quieres borrar los datos seleccionados?");
		Optional<ButtonType> res = alert.showAndWait();
		if (res.get() == ButtonType.OK) {
			int eliminar = experienciaTableView.getSelectionModel().getSelectedIndex();

			cv.getExperiencia().trabajostablaProperty().remove(eliminar);
		} else {

		}
	}

	public BorderPane getExperienciaView() {
		return this.experienciaView;
	}

}
