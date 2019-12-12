package dad.javafx.micv.conocimientos;

import javax.xml.bind.annotation.XmlElement;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
	
public class conocimientosModel {
	private ListProperty<habilidad> conocimientoslist = new SimpleListProperty<habilidad>();

	public final ListProperty<habilidad> conocimientoslistProperty() {
		return this.conocimientoslist;
	}
	
	@XmlElement
	public final ObservableList<habilidad> getConocimientoslist() {
		return this.conocimientoslistProperty().get();
	}
	

	public final void setConocimientoslist(final ObservableList<habilidad> conocimientoslist) {
		this.conocimientoslistProperty().set(conocimientoslist);
	}
	
	
	
}
