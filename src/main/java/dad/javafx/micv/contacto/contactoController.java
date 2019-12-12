package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class contactoController implements Initializable {
	private contactoModel modelo = new contactoModel();
	private curriculum cv;
	@FXML
	private SplitPane contactoView;

	@FXML
	private BorderPane telefonoBP;

	@FXML
	private TableView<telefono> telefonoTableView;

	@FXML
	private TableColumn<telefono, String> telefonoNumero;

	@FXML
	private TableColumn<telefono, TipoTelefono> telefonoTipo;

	@FXML
	private VBox telefonoBotonera;

	@FXML
	private Button telefonoAnadirButton;

	@FXML
	private Button telefonoEliminarButton;

	@FXML
	private TitledPane telefonoTitle;

	@FXML
	private BorderPane correosBP;

	@FXML
	private TableView<email> correosTableView;

	@FXML
	private TableColumn<email, String> correosEmail;

	@FXML
	private TitledPane correosTitle;

	@FXML
	private VBox correosBotonera;

	@FXML
	private Button correosAnadirButton;

	@FXML
	private Button correosEliminarButton;

	@FXML
	private BorderPane webBP;

	@FXML
	private TableView<web> webTableView;

	@FXML
	private TableColumn<web, String> webURL;

	@FXML
	private TitledPane webTitle;

	@FXML
	private VBox webBotonera;

	@FXML
	private Button webAnadirButton;

	@FXML
	private Button webEliminarButton;

	public contactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cv = rootController.getcv();
		// bindings
		correosTableView.itemsProperty().bindBidirectional(cv.getContacto().emailslistProperty());
		webTableView.itemsProperty().bindBidirectional(cv.getContacto().weblistProperty());
		telefonoTableView.itemsProperty().bindBidirectional(cv.getContacto().telefonolistProperty());

		// configuracion de valores para celdas de tableview
		telefonoNumero.setCellValueFactory(v -> v.getValue().numeroProperty());
		telefonoTipo.setCellValueFactory(v -> v.getValue().tipoProperty());
		correosEmail.setCellValueFactory(v -> v.getValue().direccionProperty());
		webURL.setCellValueFactory(v -> v.getValue().urlProperty());

	}

	@FXML
	private void onCorreosAnadirButton(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo e-mail");
		dialog.setTitle("Nuevo e-mail");
		dialog.setHeaderText("Crear una nueva direccion de correo");
		dialog.setContentText("E-mail: ");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			email nuevo = new email(result.get());
			ArrayList<email> aux = new ArrayList<email>();

			if (cv.getContacto().getEmailslist() != null) {
				aux.addAll(cv.getContacto().getEmailslist());
			}

			aux.add(nuevo);

			cv.getContacto().setEmailslist(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	@FXML
	private void onCorreosEliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Alerta!");
		alert.setHeaderText("Borrado de datos");
		alert.setContentText("¿Seguro que quieres borrar los datos seleccionados?");
		Optional<ButtonType> res = alert.showAndWait();
		if (res.get() == ButtonType.OK) {
			int eliminar = correosTableView.getSelectionModel().getSelectedIndex();

			cv.getContacto().emailslistProperty().remove(eliminar);
		} else {

		}
	}

	@FXML
	private void onTelefonoAnadirButton(ActionEvent event) {
		// crear vista del alert personalizado
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField num = new TextField();
		num.setPromptText("Número de telefono");
		ComboBox<TipoTelefono> tipos = new ComboBox<TipoTelefono>();
		tipos.setPromptText("Seleccione un tipo");
		ArrayList<TipoTelefono> tiposdetelefono = new ArrayList<TipoTelefono>();
		tiposdetelefono.add(TipoTelefono.MOVIL);
		tiposdetelefono.add(TipoTelefono.DOMICILIO);
		tipos.setItems(FXCollections.observableArrayList(tiposdetelefono));
		gp.add(new Label("Número:"), 0, 0);
		gp.add(num, 1, 0);
		gp.add(new Label("Tipo"), 0, 1);
		gp.add(tipos, 1, 1);

		Dialog<telefono> dial = new Dialog<>();
		dial.setTitle("Nuevo teléfono");
		dial.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dial.getDialogPane().setContent(gp);
		dial.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return new telefono(num.getText(), tipos.getSelectionModel().getSelectedItem());
			}
			return null;
		});
		Optional<telefono> res = dial.showAndWait();
		if (res.isPresent()) {
			ArrayList<telefono> aux = new ArrayList<telefono>();

			if (cv.getContacto().getTelefonolist() != null) {
				aux.addAll(cv.getContacto().getTelefonolist());
			}
			aux.add(res.get());

			cv.getContacto().setTelefonolist(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	@FXML
	private void onTelefonoEliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Alerta!");
		alert.setHeaderText("Borrado de datos");
		alert.setContentText("¿Seguro que quieres borrar los datos seleccionados?");
		Optional<ButtonType> res = alert.showAndWait();
		if (res.get() == ButtonType.OK) {
			int eliminar = telefonoTableView.getSelectionModel().getSelectedIndex();

			cv.getContacto().telefonolistProperty().remove(eliminar);
		} else {

		}
	}

	@FXML
	private void onWebAnadirButton(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog("http://");
		dialog.setTitle("Nueva web");
		dialog.setHeaderText("Crear una nueva direccion web");
		dialog.setContentText("URL: ");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			web nuevo = new web(result.get());
			ArrayList<web> aux = new ArrayList<web>();

			if (cv.getContacto().weblistProperty() != null) {
				aux.addAll(cv.getContacto().weblistProperty());
			}
			aux.add(nuevo);

			cv.getContacto().setWeblist(FXCollections.observableArrayList(aux));
		} else {

		}
	}

	@FXML
	private void onWebEliminarButton(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("¡Alerta!");
		alert.setHeaderText("Borrado de datos");
		alert.setContentText("¿Seguro que quieres borrar los datos seleccionados?");
		Optional<ButtonType> res = alert.showAndWait();
		if (res.get() == ButtonType.OK) {
			int eliminar = webTableView.getSelectionModel().getSelectedIndex();

			cv.getContacto().weblistProperty().remove(eliminar);
		} else {

		}
	}

	public SplitPane getContactoView() {
		return this.contactoView;
	}

}
