package dad.javafx.micv.conocimientos;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class habilidad {
	private StringProperty denominacion;
	private ObjectProperty<Nivel> grado;
	
	public habilidad () {
		denominacion = new SimpleStringProperty();
		grado = new SimpleObjectProperty<Nivel>();
	}
	

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}
	
	@XmlAttribute
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}
	

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	

	public final ObjectProperty<Nivel> gradoProperty() {
		return this.grado;
	}
	
	@XmlAttribute
	public final Nivel getGrado() {
		return this.gradoProperty().get();
	}
	

	public final void setGrado(final Nivel grado) {
		this.gradoProperty().set(grado);
	}
	
	
	
}
