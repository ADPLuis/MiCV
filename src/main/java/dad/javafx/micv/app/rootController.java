package dad.javafx.micv.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import dad.javafx.micv.conocimientos.conocimientosController;
import dad.javafx.micv.contacto.contactoController;
import dad.javafx.micv.experiencia.experienciaController;
import dad.javafx.micv.formacion.formacionController;
import dad.javafx.micv.personal.personalController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class rootController implements Initializable{
	private conocimientosController conoControl = new conocimientosController();
	private contactoController contactoControl = new contactoController();
	private experienciaController expControl = new experienciaController();
	private formacionController formControl = new formacionController();
	private personalController personControl = new personalController();
	
	
	private static curriculum cv = new curriculum();
	private static File fich = null;
    @FXML
    private BorderPane view;

    @FXML
    private MenuBar menuBarMain;

    @FXML
    private Menu archivoMenu;

    @FXML
    private MenuItem nuevoItem;

    @FXML
    private MenuItem abrirItem;

    @FXML
    private MenuItem guardarItem;

    @FXML
    private MenuItem guardarComoItem;

    @FXML
    private MenuItem salirItem;

    @FXML
    private Menu ayudaMenu;

    @FXML
    private MenuItem acercadeItem;

    @FXML
    private TabPane tabpanemain;

    @FXML
    private Tab personalTab;

    @FXML
    private Tab contactoTab;

    @FXML
    private Tab formacionTab;

    @FXML
    private Tab experienciaTab;

    @FXML
    private Tab conocimientosTab;
    
    public rootController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}
    @Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
    	personalTab.setContent(personControl.getPersonalView());
    	contactoTab.setContent(contactoControl.getContactoView());
    	formacionTab.setContent(formControl.getFormacionView());
    	experienciaTab.setContent(expControl.getExperienciaView());
    	conocimientosTab.setContent(conoControl.getConocimientosView());
	}

    @FXML
    private void onAbrirItemButton(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Abrir");
		dialog.setHeaderText("");
		dialog.setContentText("Introduce la direccion del fichero .cv para cargarlo");
		Optional<String> result = dialog.showAndWait();
		fich = new File(result.get());
			
		try {
			curriculum cargarcv = JAXButility.load(curriculum.class, fich);
			//personal
			cv.getPersonal().setDniproperty(cargarcv.getPersonal().getDniproperty());
			cv.getPersonal().setNombreproperty(cargarcv.getPersonal().getNombreproperty());
			cv.getPersonal().setApellidosproperty(cargarcv.getPersonal().getApellidosproperty());
			cv.getPersonal().setCodigopostalproperty(cargarcv.getPersonal().getCodigopostalproperty());
			cv.getPersonal().setLocalidadproperty(cargarcv.getPersonal().getLocalidadproperty());
			cv.getPersonal().setFechaproperty(cargarcv.getPersonal().getFechaproperty());
			cv.getPersonal().setDireccionproperty(cargarcv.getPersonal().getDireccionproperty());
			cv.getPersonal().setPaisproperty(cargarcv.getPersonal().getPaisproperty());
			
			//contacto
			cv.getContacto().setEmailslist(cargarcv.getContacto().getEmailslist());
			cv.getContacto().setWeblist(cargarcv.getContacto().getWeblist());
			cv.getContacto().setTelefonolist(cargarcv.getContacto().getTelefonolist());
			//conocimiento
			cv.getConocimientos().setConocimientoslist(cargarcv.getConocimientos().getConocimientoslist());
			//experiencia
			cv.getExperiencia().setTrabajostabla(cargarcv.getExperiencia().getTrabajostabla());
			//formacion
			cv.getFormacion().setEstudiostabla(cargarcv.getFormacion().getEstudiostabla());
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    @FXML
    private void onGuardarComoItemButton(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Guardar Como");
		dialog.setHeaderText("");
		dialog.setContentText("Introduce la direccion del fichero para guardar el cv");
		Optional<String> result = dialog.showAndWait();
		fich = new File(result.get());
		try {
			JAXButility.save(cv, fich);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void onGuardarItemButton(ActionEvent event) {
    	try {
			JAXButility.save(cv, new File("defaultcvsave"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void onNuevoItemButton(ActionEvent event) {
    	//personal
		cv.getPersonal().setDniproperty(new String());
		cv.getPersonal().setNombreproperty(new String());
		cv.getPersonal().setApellidosproperty(new String());
		cv.getPersonal().setCodigopostalproperty(new String());
		cv.getPersonal().setLocalidadproperty(new String());
		cv.getPersonal().setFechaproperty(null);
		cv.getPersonal().setDireccionproperty(new String());
		cv.getPersonal().setPaisproperty("Seleccione un pais");
		
		//contacto
		cv.getContacto().getEmailslist().clear();
		cv.getContacto().getWeblist().clear();
		cv.getContacto().getTelefonolist().clear();
		//conocimiento
		cv.getConocimientos().getConocimientoslist().clear();
		//experiencia
		cv.getExperiencia().getTrabajostabla().clear();
		//formacion
		cv.getFormacion().getEstudiostabla().clear();
    }

    @FXML
    private void onSalirItemButton(ActionEvent event) {
    	Platform.exit();
    }
    public BorderPane getRootView() {
    	return this.view;
    }
    public static curriculum getcv() {
		return cv;
	}
	
}

