package dad.javafx.micv.personal;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import dad.javafx.micv.app.LocalDateAdapter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class personalModel {
	private StringProperty dniproperty;
	private StringProperty nombreproperty;
	private StringProperty apellidosproperty;
	private ObjectProperty<LocalDate> fechaproperty;
	private StringProperty direccionproperty;
	private StringProperty codigopostalproperty;
	private StringProperty localidadproperty;
	private StringProperty paisproperty;
	private ListProperty<String> paislistaproperty;
	private ListProperty<String> nacionalidadproperty;
	
	public personalModel() {
		dniproperty = new SimpleStringProperty();
		nombreproperty = new SimpleStringProperty();
		apellidosproperty = new SimpleStringProperty();
		fechaproperty = new SimpleObjectProperty<LocalDate>();
		direccionproperty = new SimpleStringProperty();
		codigopostalproperty = new SimpleStringProperty();
		localidadproperty = new SimpleStringProperty();
		paisproperty = new SimpleStringProperty();
		nacionalidadproperty = new SimpleListProperty<String>(FXCollections.observableArrayList());
		paislistaproperty = new SimpleListProperty<String>(FXCollections.observableArrayList());
	}
	
	
	public final StringProperty dnipropertyProperty() {
		return this.dniproperty;
	}
	
	public final String getDniproperty() {
		return this.dnipropertyProperty().get();
	}
	
	public final void setDniproperty(final String dniproperty) {
		this.dnipropertyProperty().set(dniproperty);
	}
	
	public final StringProperty nombrepropertyProperty() {
		return this.nombreproperty;
	}
	@XmlElement
	public final String getNombreproperty() {
		return this.nombrepropertyProperty().get();
	}
	
	public final void setNombreproperty(final String nombreproperty) {
		this.nombrepropertyProperty().set(nombreproperty);
	}
	
	public final StringProperty apellidospropertyProperty() {
		return this.apellidosproperty;
	}
	@XmlElement
	public final String getApellidosproperty() {
		return this.apellidospropertyProperty().get();
	}
	
	public final void setApellidosproperty(final String apellidosproperty) {
		this.apellidospropertyProperty().set(apellidosproperty);
	}
	
	public final ObjectProperty<LocalDate> fechapropertyProperty() {
		return this.fechaproperty;
	}
	@XmlElement
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public final LocalDate getFechaproperty() {
		return this.fechapropertyProperty().get();
	}
	
	public final void setFechaproperty(final LocalDate fechaproperty) {
		this.fechapropertyProperty().set(fechaproperty);
	}
	
	public final StringProperty direccionpropertyProperty() {
		return this.direccionproperty;
	}
	@XmlElement
	public final String getDireccionproperty() {
		return this.direccionpropertyProperty().get();
	}
	
	public final void setDireccionproperty(final String direccionproperty) {
		this.direccionpropertyProperty().set(direccionproperty);
	}
	
	public final StringProperty codigopostalpropertyProperty() {
		return this.codigopostalproperty;
	}
	@XmlElement
	public final String getCodigopostalproperty() {
		return this.codigopostalpropertyProperty().get();
	}
	
	public final void setCodigopostalproperty(final String codigopostalproperty) {
		this.codigopostalpropertyProperty().set(codigopostalproperty);
	}
	
	public final StringProperty localidadpropertyProperty() {
		return this.localidadproperty;
	}
	@XmlElement
	public final String getLocalidadproperty() {
		return this.localidadpropertyProperty().get();
	}
	
	public final void setLocalidadproperty(final String localidadproperty) {
		this.localidadpropertyProperty().set(localidadproperty);
	}
	
	public final StringProperty paispropertyProperty() {
		return this.paisproperty;
	}
	@XmlElement
	public final String getPaisproperty() {
		return this.paispropertyProperty().get();
	}
	
	public final void setPaisproperty(final String paisproperty) {
		this.paispropertyProperty().set(paisproperty);
	}

	public final ListProperty<String> nacionalidadpropertyProperty() {
		return this.nacionalidadproperty;
	}
	
	@XmlElement
	public final ObservableList<String> getNacionalidadproperty() {
		return this.nacionalidadpropertyProperty().get();
	}
	

	public final void setNacionalidadproperty(final ObservableList<String> nacionalidadproperty) {
		this.nacionalidadpropertyProperty().set(nacionalidadproperty);
	}

	public final ListProperty<String> paislistapropertyProperty() {
		return this.paislistaproperty;
	}
	

	public final ObservableList<String> getPaislistaproperty() {
		return this.paislistapropertyProperty().get();
	}
	

	public final void setPaislistaproperty(final ObservableList<String> paislistaproperty) {
		this.paislistapropertyProperty().set(paislistaproperty);
	}
	
	
	
	
	
	
	
}
