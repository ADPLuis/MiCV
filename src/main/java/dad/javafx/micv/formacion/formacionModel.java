package dad.javafx.micv.formacion;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

public class formacionModel {
	private ListProperty<estudios> estudiostabla = new SimpleListProperty();

	public final ListProperty<estudios> estudiostablaProperty() {
		return this.estudiostabla;
	}
	
	@XmlElement
	public final ObservableList<estudios> getEstudiostabla() {
		return this.estudiostablaProperty().get();
	}
	

	public final void setEstudiostabla(final ObservableList<estudios> estudiostabla) {
		this.estudiostablaProperty().set(estudiostabla);
	}
	
	
}
