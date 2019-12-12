package dad.javafx.micv.contacto;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

public class contactoModel {
	private ListProperty<email> emailslist = new SimpleListProperty<email>();
	private ListProperty<telefono> telefonolist = new SimpleListProperty<telefono>();
	private ListProperty<web> weblist = new SimpleListProperty<web>();
	
	
	public final ListProperty<email> emailslistProperty() {
		return this.emailslist;
	}
	
	public final ObservableList<email> getEmailslist() {
		return this.emailslistProperty().get();
	}
	
	public final void setEmailslist(final ObservableList<email> emailslist) {
		this.emailslistProperty().set(emailslist);
	}
	
	public final ListProperty<telefono> telefonolistProperty() {
		return this.telefonolist;
	}
	
	public final ObservableList<telefono> getTelefonolist() {
		return this.telefonolistProperty().get();
	}
	
	public final void setTelefonolist(final ObservableList<telefono> telefonolist) {
		this.telefonolistProperty().set(telefonolist);
	}
	
	public final ListProperty<web> weblistProperty() {
		return this.weblist;
	}
	
	public final ObservableList<web> getWeblist() {
		return this.weblistProperty().get();
	}
	
	public final void setWeblist(final ObservableList<web> weblist) {
		this.weblistProperty().set(weblist);
	}
	
	
}
