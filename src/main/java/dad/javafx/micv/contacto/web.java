package dad.javafx.micv.contacto;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class web {
	private StringProperty url = new SimpleStringProperty();
	
	public web (String d) {
		url.setValue(d);
	}

	public final StringProperty urlProperty() {
		return this.url;
	}
	
	@XmlAttribute
	public final String getUrl() {
		return this.urlProperty().get();
	}
	

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	
	
}
