package dad.javafx.micv.conocimientos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class idioma extends habilidad{
	private StringProperty certificacion;
	
	public idioma() {
		super();
		certificacion = new SimpleStringProperty();
	}

	public final StringProperty certificacionProperty() {
		return this.certificacion;
	}
	

	public final String getCertificacion() {
		return this.certificacionProperty().get();
	}
	

	public final void setCertificacion(final String certificacion) {
		this.certificacionProperty().set(certificacion);
	}
	
	
}
