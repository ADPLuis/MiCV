package dad.javafx.micv.experiencia;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

public class experienciaModel {
	private ListProperty<trabajo> trabajostabla = new SimpleListProperty();

	public final ListProperty<trabajo> trabajostablaProperty() {
		return this.trabajostabla;
	}
	
	@XmlElement
	public final ObservableList<trabajo> getTrabajostabla() {
		return this.trabajostablaProperty().get();
	}
	

	public final void setTrabajostabla(final ObservableList<trabajo> trabajostabla) {
		this.trabajostablaProperty().set(trabajostabla);
	}
	
	
}
