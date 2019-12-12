package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.app.curriculum;
import dad.javafx.micv.app.rootController;
import dad.javafx.micv.contacto.telefono;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class formacionController implements Initializable {
	private formacionModel modelo = new formacionModel();
	private curriculum cv;
	@FXML
	private BorderPane formacionView;

	@FXML
	private TableView<estudios> formacionTableView;

	@FXML
	private TableColumn<estudios, LocalDate> formDesdeColum;

	@FXML
	private TableColumn<estudios, LocalDate> formHastaColum;

	@FXML
	private TableColumn<estudios, String> formDenominacionColum;

	@FXML
	private TableColumn<estudios, String> formOrganizadorColum;

	@FXML
	private VBox formacionBotonera;

	@FXML
	private Button formacionAnadirButton;

	@FXML
	private Button formacionEliminarButton;

	public formacionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cv = rootController.getcv();
		// bindeos
		formacionTableView.itemsProperty().bindBidirectional(cv.getFormacion().estudiostablaProperty());
		// configuracion de valores para celdas de tableview
		formDesdeColum.setCellValueFactory(v -> v.getValue().desdeProperty());
		formHastaColum.setCellValueFactory(v -> v.getValue().hastaProperty());
		formDenominacionColum.setCellValueFactory(v -> v.getValue().denominacionProperty());
		formOrganizadorColum.setCellValueFactory(v -> v.getValue().organizadorProperty());
		

	}

	@FXML
	void onFormacionAnadirButton(ActionEvent event) {
		// crear vista dialogo peronalizado
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField denomina = new TextField();
		TextField organiza = new TextField();
		DatePicker desde = new DatePicker();
		DatePicker hasta = new DatePicker();

		gp.add(new Label("Denominación"), 0, 0);
		gp.add(denomina, 1, 0);
		gp.add(new Label("Organizador"), 0, 1);
		gp.add(organiza, 1, 1);
		gp.add(new Label("Desde"), 0, 2);
		gp.add(desde, 1, 2);
		gp.add(new Label("Hasta"), 0, 3);
		gp.add(hasta, 1, 3);

		Dialog<estudios> dial = new Dialog<estudios>();
		dial.setTitle("Nuevo título");
		dial.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dial.getDialogPane().setContent(gp);
		dial.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return new estudios(desde.getValue(), hasta.getValue(), denomina.getText(), organiza.getText());
			}
			return null;
		});
		//añadir resultado a la lista
		Optional<estudios> res = dial.showAndWait();
		if (res.isPresent()) {
			ArrayList<estudios> aux = new ArrayList<estudios>();

			if (cv.getFormacion().estudiostablaProperty() != null) {
				aux.addAll(cv.getFormacion().estudiostablaProperty());
			}
			aux.add(res.get());

			cv.getFormacion().setEstudiostabla(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	@FXML
	void onFormacionEliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Alerta!");
		alert.setHeaderText("Borrado de datos");
		alert.setContentText("¿Seguro que quieres borrar los datos seleccionados?");
		Optional<ButtonType> res = alert.showAndWait();
		if (res.get() == ButtonType.OK) {
			int eliminar = formacionTableView.getSelectionModel().getSelectedIndex();

			cv.getFormacion().estudiostablaProperty().remove(eliminar);
		} else {

		}
	}

	public BorderPane getFormacionView() {
		return this.formacionView;
	}

}
