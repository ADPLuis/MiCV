package dad.javafx.micv.conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import dad.javafx.micv.app.curriculum;
import dad.javafx.micv.app.rootController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class conocimientosController implements Initializable {
	private conocimientosModel modelo = new conocimientosModel();
	private curriculum cv;
	@FXML
	private BorderPane conocimientoView;

	@FXML
	private TableView<habilidad> conocimientoTableView;

	@FXML
	private TableColumn<habilidad, String> conocimientoDenominacion;

	@FXML
	private TableColumn<habilidad, Nivel> conocimientoNivel;

	@FXML
	private VBox conocimientoBotonera;

	@FXML
	private Button conocimientoAnadir;

	@FXML
	private Button anadirIdioma;

	@FXML
	private Button conocimientoEliminar;

	public conocimientosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cv = rootController.getcv();

		// bindeos
		conocimientoTableView.itemsProperty().bindBidirectional(cv.getConocimientos().conocimientoslistProperty());

		// valores celdas tableview
		conocimientoDenominacion.setCellValueFactory(v -> v.getValue().denominacionProperty());
		conocimientoNivel.setCellValueFactory(v -> v.getValue().gradoProperty());

	}

	@FXML
	private void onConocimientoAnadirButton(ActionEvent event) {
		// crear vista dialogo peronalizado
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField denomina = new TextField();
		ComboBox<Nivel> comboniv = new ComboBox<Nivel>();
		ArrayList<Nivel> niv = new ArrayList<Nivel>();
		niv.add(Nivel.BASICO);
		niv.add(Nivel.MEDIO);
		niv.add(Nivel.AVANZADO);
		comboniv.setItems(FXCollections.observableArrayList(niv));

		Button reset = new Button("X");
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				comboniv.getSelectionModel().clearSelection();
			}
		});

		gp.add(new Label("Denominación"), 0, 0);
		gp.add(denomina, 1, 0);
		gp.add(new Label("Nivel"), 0, 1);
		gp.add(comboniv, 1, 1);
		gp.add(reset, 3, 1);

		Dialog<habilidad> dial = new Dialog<habilidad>();
		dial.setTitle("Nuevo conocimiento");

		dial.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dial.getDialogPane().setContent(gp);
		dial.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				habilidad nueva = new habilidad();
				nueva.setDenominacion(denomina.getText());
				nueva.setGrado(comboniv.getValue());
				return nueva;
			}
			return null;
		});
		Optional<habilidad> res = dial.showAndWait();
		// añadir a la lista
		if (res.isPresent()) {
			ArrayList<habilidad> aux = new ArrayList<habilidad>();

			if (cv.getConocimientos().conocimientoslistProperty() != null) {
				aux.addAll(cv.getConocimientos().conocimientoslistProperty());
			}
			aux.add(res.get());

			cv.getConocimientos().setConocimientoslist(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	@FXML
	private void onConocimientoEliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Alerta!");
		alert.setHeaderText("Borrado de datos");
		alert.setContentText("¿Seguro que quieres borrar los datos seleccionados?");
		Optional<ButtonType> res = alert.showAndWait();
		if (res.get() == ButtonType.OK) {
			int eliminar = conocimientoTableView.getSelectionModel().getSelectedIndex();

			cv.getConocimientos().conocimientoslistProperty().remove(eliminar);
		} else {

		}
	}

	@FXML
	private void onConocimientoIdiomaAnadirButton(ActionEvent event) {
		// crear vista dialogo peronalizado
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField denomina = new TextField();
		ComboBox<Nivel> comboniv = new ComboBox<Nivel>();
		ArrayList<Nivel> niv = new ArrayList<Nivel>();
		niv.add(Nivel.BASICO);
		niv.add(Nivel.MEDIO);
		niv.add(Nivel.AVANZADO);
		comboniv.setItems(FXCollections.observableArrayList(niv));

		Button reset = new Button("X");
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				comboniv.getSelectionModel().clearSelection();
			}
		});
		TextField certif = new TextField();

		gp.add(new Label("Denominación"), 0, 0);
		gp.add(denomina, 1, 0);
		gp.add(new Label("Nivel"), 0, 1);
		gp.add(comboniv, 1, 1);
		gp.add(reset, 3, 1);
		gp.add(new Label("Certificacion"), 0, 2);
		gp.add(certif, 1, 2);

		Dialog<habilidad> dial = new Dialog<habilidad>();
		dial.setTitle("Nuevo conocimiento");

		dial.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dial.getDialogPane().setContent(gp);
		dial.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				habilidad nueva = new habilidad();
				nueva.setDenominacion(denomina.getText());
				nueva.setGrado(comboniv.getValue());
				return nueva;
			}
			return null;
		});
		Optional<habilidad> res = dial.showAndWait();
		// añadir a la lista
		if (res.isPresent()) {
			ArrayList<habilidad> aux = new ArrayList<habilidad>();

			if (cv.getConocimientos().conocimientoslistProperty() != null) {
				aux.addAll(cv.getConocimientos().conocimientoslistProperty());
			}
			aux.add(res.get());

			cv.getConocimientos().setConocimientoslist(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	public BorderPane getConocimientosView() {
		return this.conocimientoView;
	}

}
