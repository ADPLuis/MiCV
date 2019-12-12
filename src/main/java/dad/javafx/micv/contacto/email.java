package dad.javafx.micv.contacto;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class email {
	private StringProperty direccion = new SimpleStringProperty();
	
	public email(String d) {
		direccion.setValue(d);
	}

	public final StringProperty direccionProperty() {
		return this.direccion;
	}
	
	@XmlAttribute
	public final String getDireccion() {
		return this.direccionProperty().get();
	}
	

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}
	
	
	
}
