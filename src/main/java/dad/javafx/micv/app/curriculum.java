package dad.javafx.micv.app;

import javax.xml.bind.annotation.XmlRootElement;

import dad.javafx.micv.conocimientos.conocimientosModel;
import dad.javafx.micv.contacto.contactoModel;
import dad.javafx.micv.experiencia.experienciaModel;
import dad.javafx.micv.formacion.formacionModel;
import dad.javafx.micv.personal.personalModel;
@XmlRootElement
public class curriculum {
	private personalModel personal = new personalModel();
	private contactoModel contacto = new contactoModel();
	private conocimientosModel conocimientos = new conocimientosModel();
	private experienciaModel experiencia = new experienciaModel();
	private formacionModel formacion = new formacionModel();
	public personalModel getPersonal() {
		return personal;
	}
	public void setPersonal(personalModel personal) {
		this.personal = personal;
	}
	public contactoModel getContacto() {
		return contacto;
	}
	public void setContacto(contactoModel contacto) {
		this.contacto = contacto;
	}
	public conocimientosModel getConocimientos() {
		return conocimientos;
	}
	public void setConocimientos(conocimientosModel conocimientos) {
		this.conocimientos = conocimientos;
	}
	public experienciaModel getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(experienciaModel experiencia) {
		this.experiencia = experiencia;
	}
	public formacionModel getFormacion() {
		return formacion;
	}
	public void setFormacion(formacionModel formacion) {
		this.formacion = formacion;
	}
	
	
	
}
