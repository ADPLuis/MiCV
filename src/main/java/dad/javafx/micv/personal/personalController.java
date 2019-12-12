package dad.javafx.micv.personal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.app.curriculum;
import dad.javafx.micv.app.rootController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class personalController implements Initializable {
	private personalModel modelo = new personalModel();
	private curriculum cv;
	@FXML
	private BorderPane personalView;

	@FXML
	private GridPane personalGrid;

	@FXML
	private Label dniLabel;

	@FXML
	private Label nombreLabel;

	@FXML
	private Label apellidosLabel;

	@FXML
	private Label nacimientoLabel;

	@FXML
	private Label direccionLabel;

	@FXML
	private Label codigopostalLabel;

	@FXML
	private Label localidadLabel;

	@FXML
	private Label paisLabel;

	@FXML
	private ListView<String> nacionalidadesList;

	@FXML
	private Label nacionalidadLabel;

	@FXML
	private VBox personalBotonera;

	@FXML
	private Button agregarButton;

	@FXML
	private Button quitarButton;

	@FXML
	private TextField dniTextfield;

	@FXML
	private TextField apellidosTextfield;

	@FXML
	private DatePicker fechaPicker;

	@FXML
	private TextField localidadTextfield;

	@FXML
	private TextField direccionTextfield;

	@FXML
	private TextField codigopostalTextfield;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private TextField nombreTextfield;

	public personalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cv = rootController.getcv();
		// binds
		dniTextfield.textProperty().bindBidirectional(cv.getPersonal().dnipropertyProperty());
		nombreTextfield.textProperty().bindBidirectional(cv.getPersonal().nombrepropertyProperty());
		apellidosTextfield.textProperty().bindBidirectional(cv.getPersonal().apellidospropertyProperty());
		fechaPicker.valueProperty().bindBidirectional(cv.getPersonal().fechapropertyProperty());
		direccionTextfield.textProperty().bindBidirectional(cv.getPersonal().direccionpropertyProperty());
		codigopostalTextfield.textProperty().bindBidirectional(cv.getPersonal().codigopostalpropertyProperty());
		localidadTextfield.textProperty().bindBidirectional(cv.getPersonal().localidadpropertyProperty());
		paisCombo.valueProperty().bindBidirectional(cv.getPersonal().paispropertyProperty());
		nacionalidadesList.itemsProperty().bindBidirectional(cv.getPersonal().nacionalidadpropertyProperty());
		

		// leer paises del .csv
		try {
			File paises = new File("src/main/resources/paises.csv");
			FileReader fr = new FileReader(paises);
			BufferedReader br = new BufferedReader(fr);

			StringBuilder pais = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				pais.append(line + "-");
			}
			String[] aux = pais.toString().split("-");
			ArrayList<String> temp = new ArrayList<String>();
			for (int i = 0; i < aux.length; i++) {
				temp.add(aux[i]);
			}
			modelo.setPaislistaproperty(FXCollections.observableArrayList(temp));

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		paisCombo.itemsProperty().bind(modelo.paislistapropertyProperty());
	}

	@FXML
	void onAgregarNacionalidadButton(ActionEvent event) {
		try {
			// leer nacionalidades para agregarlas al choicedialog
			File nacionalidades = new File("src/main/resources/nacionalidades.csv");
			FileReader fr = new FileReader(nacionalidades);
			BufferedReader br = new BufferedReader(fr);

			StringBuilder nacion = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				nacion.append(line + "-");
			}
			String[] aux = nacion.toString().split("-");
			ArrayList<String> temp = new ArrayList<String>();
			for (int i = 0; i < aux.length; i++) {
				temp.add(aux[i]);
			}

			// elegir nacionalidad
			ChoiceDialog<String> nacionalidad = new ChoiceDialog<String>("afgano",
					FXCollections.observableArrayList(temp));
			nacionalidad.setTitle("Nueva Nacionalidad");
			nacionalidad.setHeaderText("Añadir nacionalidad");
			Optional<String> res = nacionalidad.showAndWait();
			cv.getPersonal().getNacionalidadproperty().add(res.get());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void onQuitarNacionalidadButton(ActionEvent event) {
		cv.getPersonal().nacionalidadpropertyProperty().remove(nacionalidadesList.getSelectionModel().getSelectedItem());
	}

	public BorderPane getPersonalView() {
		return this.personalView;
	}

}
